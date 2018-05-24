package ru.ivmiit.servlets;

import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StockServlet extends HttpServlet {

   static String dbUser = "postgres";
   static String dbPassword = " ";
   static String connectionUrl = "jdbc:postgresql://localhost:5432/stocks";


    // метод для соединение с БД
    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // преобразую полученные значения в int

        int countOfLegs = Integer.parseInt(request.getParameter("countOfLegs"));
        int weight = Integer.parseInt(request.getParameter("weight"));

        // добавляю полученные значения в БД
        try {
        Connection dbConnection = getDBConnection();
        Statement statement = dbConnection.createStatement();

        String insertTableSQL = "INSERT INTO stock"
                + "(Count_Of_Legs, Weight) " + "VALUES"
                + "('"+countOfLegs+"','"+weight+"');";

        // выполнем SQL запрос
        statement.executeUpdate(insertTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String selectTableSQL = "SELECT * FROM stock";
        try {
            Connection dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
            // выбираем данные с БД
            ResultSet rs = statement.executeQuery(selectTableSQL);
            // "отрисовываем" страничку
            writer.write("<html>");
            writer.write("<head>");
            writer.write("<meta charset=\"utf-8\">");
            writer.write("<title>Stock</title>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<form method=\"POST\" action=\"/app/stock\">");

           // если из БД было что-то получено то цикл while сработает
            try {
                while (rs.next()) {
                    writer.write("<p>"+"Id = "+rs.getString("id") + "; " + "Count of Legs = " + rs.getString("count_of_legs") + "; " + "Weight = " + rs.getString("weight")+";"+"</p>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            writer.write("<p> Count of legs<br/> <input name=\"countOfLegs\"> </p>");
            writer.write("<p>  Weight<br/> <input name=\"weight\"> </p>");
            writer.write("<input type=\"submit\" value=\"OK\" />");
            writer.write("</form>");
            writer.write("</body>");
            writer.write("</html>");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
