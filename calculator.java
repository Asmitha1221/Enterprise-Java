import static java.io.FileDescriptor.out;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/calculator"})
public class calculator extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter out=response.getWriter();
            String num1= request.getParameter("num1");
            String num2= request.getParameter("num2");
            String op = request.getParameter("Operator");
            if(op.equals("Addition")){
                out.println("Addition is: "+(Integer.parseInt(num1)+Integer.parseInt(num2)));
            }
            else if(op.equals("Subtraction")){
                out.println("Subtration is: "+(Integer.parseInt(num1)-Integer.parseInt(num2)));
            }
            else if(op.equals("Multiplication")){
                out.println("Multiplication is: "+(Integer.parseInt(num1)*Integer.parseInt(num2)));
            }
            else{
                out.println("Division is: "+(Integer.parseInt(num1)/Integer.parseInt(num2)));
            }
            out.println("calculator");
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
}// </editor-fold>
}
