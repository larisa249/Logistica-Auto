import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The `utilizatori` class represents user information and provides methods for adding and deleting users from a database.
 */
public class utilizatori {
    int id;
    String username;
    private String parola;

    /**
     * Constructor for the `utilizatori` class.
     *
     * @param id       The user's ID
     * @param username The username of the user
     * @param parola   The user's password
     */
    public utilizatori(int id, String username, String parola) {
        this.id = id;
        this.username = username;
        this.parola = parola;
    }

    /**
     * Get the user's ID.
     *
     * @return The user's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Get the username of the user.
     *
     * @return The username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the user's password.
     *
     * @return The user's password
     */
    public String getParola() {
        return parola;
    }

    /**
     * Add a user to the database.
     *
     * @param connection The database connection
     * @param username   The username of the user
     * @param parola     The user's password
     * @return The ID of the added user or -1 in case of an error
     */
    public static int adaugaUtilizator(Connection connection, String username, String parola) {
        int utilizatorId = -1;
        if (connection != null) {
            String INSERT_QUERY = "INSERT INTO utilizatori (username, parola) VALUES (?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, parola);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("The user has been added to the utilizatori table!");
                    ResultSet rs = preparedStatement.getGeneratedKeys();
                    if (rs.next()) {
                        utilizatorId = rs.getInt(1);
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error adding user: " + e.getMessage());
            }
        }
        return utilizatorId;
    }

    /**
     * Delete a user from the database.
     *
     * @param connection   The database connection
     * @param idUtilizator The ID of the user to be deleted
     */
    public static void stergeUtilizator(Connection connection, String username) {
        if (connection != null) {
            try {
                String deleteQuery = "DELETE FROM utilizatori WHERE username = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setString(1, username);

                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("The user has been deleted from the utilizatori table!");
                } else {
                    System.out.println("No user with the specified ID was found.");
                }
            } catch (SQLException e) {
                System.err.println("Error deleting user: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
