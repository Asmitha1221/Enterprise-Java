package mypack;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ShowResult")
public class ShowResult extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body bgcolor='lightgreen'>");
        out.println("<center>");
        out.println("<h1>Quiz Result</h1>");
        out.println("</center>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/qadb",
                    "root",
                    "root");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select ans from quiz");
            int score = 0;
            int i = 1;
            while (rs.next()) {
                String correct = rs.getString("ans");
                String user = request.getParameter("" + i);
                if (correct.equals(user)) {
                    score++;
                    out.println("<h3>Q" + i + " : Correct</h3>");
                } else {
                    out.println("<h3>Q" + i + " : Incorrect</h3>");
                }
                i++;
            }
            out.println("<hr>");
            out.println("<h2>Your Score : " + score + " / " + (i - 1) + "</h2>");
            con.close();
        } catch (Exception e) {
            out.println(e);
        }
        out.println("<br><a href='index.html'>Take Quiz Again</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
