import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplianceDatabaseServlet extends HttpServlet {
   public ApplianceDatabaseServlet() {
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");
      Connection connection = null;
      Statement statement = null;
      PrintWriter out = response.getWriter();

      try {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/home_appliances", "root", "");
         if (connection != null) {
            out.println("<h1> No issues in Connection</h1>");
         }

         statement = connection.createStatement();
         String query = "SELECT * FROM technicians";
         ResultSet resultSet = statement.executeQuery(query);

         out.println("<html><body>");
         while(resultSet.next()) {
            String technicianID = resultSet.getString("TechnicianID");
            String name = resultSet.getString("Name");
            String applianceID = resultSet.getString("ApplianceID");
            Double experience = resultSet.getDouble("Experience");
            String certified = resultSet.getString("Certified");

            out.println("<p> Technician ID: " + technicianID + "<br>");
            out.println("Name: " + name + "<br>");
            out.println("Appliance ID: " + applianceID + "<br>");
            out.println("Experience: " + experience + "<br>");
            out.println("Certified: " + certified + "<br></p>");
         }

         out.println("</body></html>");
         resultSet.close();
         statement.close();
         connection.close();
      } catch (Exception e) {
         out.print("<p>Error connecting to database: " + e.toString() + "</p>");
      }
   }
}
