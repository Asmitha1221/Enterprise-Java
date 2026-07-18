package mypack;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body bgcolor='lightblue'>");
        out.println("<center>");
        out.println("<h1>Java Quiz</h1>");

        out.println("</center>");
        out.println("<form action='ShowResult'>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qadb", "root",
                    "root");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from quiz");
            int i = 1;
            while (rs.next()) {
                out.println("<hr>");
                out.println("<h3>Q" + i + ". "
                        + rs.getString("question")
                        + "</h3>");
                out.println("<input type='radio' name='" + i + "' value='1'>" + rs.getString("Op1") + "<br>");
                out.println("<input type='radio' name='" + i + "' value='2'>" + rs.getString("Op2") + "<br>");
                out.println("<input type='radio' name='" + i + "' value='3'>" + rs.getString("Op3") + "<br>");
                out.println("<input type='radio' name='" + i + "' value='4'>" + rs.getString("Op4") + "<br>");
                i++;
            }
            con.close();

        } catch (Exception e) {
            out.println(e);
        }
        out.println("<br>");
        out.println("<input type='submit' value='Submit Quiz'>");
        out.println("<input type='reset' value='Reset'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
