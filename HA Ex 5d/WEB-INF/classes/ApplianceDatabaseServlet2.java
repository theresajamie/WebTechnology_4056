import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplianceDatabaseServlet2 extends HttpServlet {

    public ApplianceDatabaseServlet2() {
    }

    // This method handles GET requests to retrieve appliance data based on the appliance name
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String applianceName = request.getParameter("appliance");  // Getting appliance name from the request

        if (applianceName == null || applianceName.isEmpty()) {
            out.println("<p>Error: Appliance name is required</p>");
            return;
        }

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/home_appliances", "root", "");

            // SQL query to fetch appliances with the given appliance name
            String query = "SELECT * FROM appliances WHERE ApplianceName = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, applianceName);  // Set the appliance name in the query

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if any appliances are found
            if (!resultSet.next()) {
                out.println("<p>No appliances found for the name: " + applianceName + "</p>");
            } else {
                out.println("<html><body>");
                out.println("<h2>Appliance Details for: " + applianceName + "</h2>");

                // Iterate over the results and display appliance details
                do {
                    String applianceID = resultSet.getString("ApplianceID");
                    String applianceNameResult = resultSet.getString("ApplianceName");
                    int powerConsumption = resultSet.getInt("PowerConsumption");

                    out.println("<p>Appliance ID: " + applianceID + "<br>");
                    out.println("Appliance Name: " + applianceNameResult + "<br>");
                    out.println("Power Consumption: " + powerConsumption + " watts<br></p>");

                } while (resultSet.next());

                out.println("</body></html>");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            out.println("<p>Error connecting to the database: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    }
}
