package db;

import exceptions.NoUserExistsException;
import models.Product;
import models.User;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;

//SELECT setval('students_id_seq', (SELECT MAX(id) FROM students));
public class DBConnector {
    private static Connection connection;
    private final static String url = "jdbc:postgresql://localhost:5432/JavaEESprintTask431";
    private final static String login = "postgres";
    private final static String password = System.getenv("JAKARTA_EE_POSTGRES_PASSWORD");

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Product methods
    public static ArrayList<Product> getProducts() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM products ORDER BY id ASC");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setPrice(resultSet.getDouble("price"));
            products.add(product);
        }
        return products;
    }

    // User methods
    @NotNull
    public static  User getAsUser(String email, String password) throws SQLException, NoUserExistsException {
        User user = new User();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            user.setId(resultSet.getLong("id"));
            user.setEmail(email);
            user.setPassword(password);
            user.setFullName(resultSet.getString("fullName"));
        } else {
            throw new NoUserExistsException(email, password);
        }
        return user;
    }

    @NotNull
    public static User getUserById(long id) throws SQLException, NoUserExistsException {
        User user = new User();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            user.setId(resultSet.getLong("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setFullName(resultSet.getString("fullName"));
        } else {
            throw new NoUserExistsException(id);
        }
        return user;
    }

    public static boolean checkUserExists(String email) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    public static void createUser(String email, String password, String fullName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO users (email, password, fullName) VALUES (?, ?, ?)");
        statement.setString(1, email);
        statement.setString(2, password);
        statement.setString(3, fullName);
        statement.executeUpdate();
        statement.close();
    }

    public static void deleteUser(long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
        statement.setLong(1, id);
        statement.executeUpdate();
        statement.close();
    }
}
