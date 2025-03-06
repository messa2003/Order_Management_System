package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory
{
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/schooldb";
    private static final String USER = "root";
    private static final String PASS = "RaduAndrei28";
    private static Connection connection;

    private static final ConnectionFactory singleInstance = new ConnectionFactory();

    public ConnectionFactory()
    {
        try
        {
            Class.forName(DRIVER);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        try
        {
            this.connection = DriverManager.getConnection(DBURL, USER, PASS);
            if (this.connection != null)
            {
                System.out.println("Conected");
            }
            else
            {
                System.out.println("Failed to connect1");
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed to connect2");
        }
    }
    public static Connection getConnection()
    {
        return connection;
    }
    public static void close(Connection connection)
    {
        if (connection != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                LOGGER.log(Level.WARNING,"An error occured while trying to close the connection");
            }
        }
    }
    public static void close(Statement statement)
    {
        if (statement != null)
        {
            try
            {
                statement.close();
            }
            catch (SQLException e)
            {
                LOGGER.log(Level.WARNING,"An error occured while trying to close the connection");
            }
        }
    }
    public static void close(ResultSet resultSet)
    {
        if (resultSet != null)
        {
            try
            {
                resultSet.close();
            }
            catch (SQLException e)
            {
                LOGGER.log(Level.WARNING,"An error occured while trying to close the connection");
            }
        }
    }
}