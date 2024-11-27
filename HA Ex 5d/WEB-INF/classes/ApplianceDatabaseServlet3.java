import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplianceDatabaseServlet3 extends HttpServlet {

   public ApplianceDatabaseServlet3() {
   }

   // Handles POST requests to add a new appliance
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");
      Connection connection = null;
      Statement statement = null;
      PrintWriter out = response.getWriter();

      try {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/home_appliances", "root", "");
         statement = connection.createStatement();

         // PreparedStatement for inserting data into the appliances table
         PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO appliances VALUES(?, ?, ?)");
         preparedStatement.setString(1, request.getParameter("applianceid"));
         preparedStatement.setString(2, request.getParameter("appliancename"));
         preparedStatement.setInt(3, Integer.parseInt(request.getParameter("powerconsumption")));
         preparedStatement.executeUpdate();

         out.println("<html><body><p>Database Updated</p>");
         
         // Query to display all appliances after the update
         String query = "SELECT * FROM appliances";
         preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery();

         while(resultSet.next()) {
            String applianceID = resultSet.getString("ApplianceID");
            String applianceName = resultSet.getString("ApplianceName");
            int powerConsumption = resultSet.getInt("PowerConsumption");

            out.println("<p>Appliance ID: " + applianceID + "<br>");
            out.println("Appliance Name: " + applianceName + "<br>");
            out.println("Power Consumption: " + powerConsumption + " watts<br></p>");
         }

         out.println("</body></html>");
         resultSet.close();
         statement.close();
         connection.close();
      } catch (Exception e) {
         out.println("<p>Error: " + e.toString() + "</p>");
      }
   }

   // Handles GET requests to display existing appliances
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");
      Connection connection = null;
      Statement statement = null;
      PrintWriter out = response.getWriter();

      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/home_appliances", "root", "");
         statement = connection.createStatement();

         String query = "SELECT * FROM appliances";
         PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery();

         out.println("<html><body><h2>Existing Appliances</h2>");

         while(resultSet.next()) {
            String applianceID = resultSet.getString("ApplianceID");
            String applianceName = resultSet.getString("ApplianceName");
            int powerConsumption = resultSet.getInt("PowerConsumption");

            out.println("<p>Appliance ID: " + applianceID + "<br>");
            out.println("Appliance Name: " + applianceName + "<br>");
            out.println("Power Consumption: " + powerConsumption + " watts<br></p>");
         }

         out.println("</body></html>");
         resultSet.close();
         statement.close();
         connection.close();
      } catch (Exception e) {
         out.println("<p>Error: " + e.toString() + "</p>");
      }
   }
}
