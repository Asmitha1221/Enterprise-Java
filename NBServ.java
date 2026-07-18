import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/NBServ"})
public class NBServ extends HttpServlet {
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
String filepath = "/WEB-INF/Intro.txt";
ServletContext cnt = getServletContext();
InputStream iStream = cnt.getResourceAsStream(filepath);
try (PrintWriter out = response.getWriter()) {
String path = "http://" + request.getServerName() + ":" + request.getServerPort() +
request.getContextPath() + "/ReadNBServ";
out.println("<html>");
out.println("<head>");
out.println("<title>Non-Blocking I/O Servlet</title>");
out.println("</head>");
out.println("<body>");
out.println("<h1>Reading Introduction my from file</h1>");
out.flush();

URL url = new URL(path);
HttpURLConnection mycon = (HttpURLConnection) url.openConnection();
mycon.setChunkedStreamingMode(2);
mycon.setDoOutput(true);
mycon.connect();
if (iStream != null) {
InputStreamReader isr = new InputStreamReader(iStream);
BufferedReader br = new BufferedReader(isr);
String readText;
try (BufferedWriter bw = new BufferedWriter(new
OutputStreamWriter(mycon.getOutputStream()))) {
out.println("<div style='width=100%;height:450px;overflow:scroll;'>");
while ((readText = br.readLine()) != null) {
out.println("<div style='background-color:khaki;width=100%;'>");
out.println(readText);
out.println("</div><br/>");
out.flush();
bw.write(readText);
Thread.sleep(1000);
out.flush();
}
out.println("</div>");
bw.flush();
}
}
out.println("</body>");
out.println("</html>");
} catch (InterruptedException | IOException ex) {
Logger.getLogger(NBServ.class.getName()).log(Level.SEVERE, null, ex);
}
}
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
processRequest(request, response);
}
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
processRequest(request, response);
}
@Override
public String getServletInfo() {
return "Short description";
}
}