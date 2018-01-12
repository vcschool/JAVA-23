package lt.vcs.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:/home/audrius/test6";
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "sa";

    public static void main(String[] args) {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS car (name VARCHAR, smthing VARCHAR)")
        ) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try (
                Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM car")
        ) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Pavyzdys kaip paruosti dinamine uzklausa
    private PreparedStatement createPreparedStatement(Connection con, int userId) throws SQLException {
        String sql = "SELECT id, username FROM users WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        return ps;
    }
}
