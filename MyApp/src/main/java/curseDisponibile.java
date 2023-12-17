import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 * The `curseDisponibile` class manages operations related to available logistics routes in an application.
 */
public class curseDisponibile {
    int id;
    int utilizator_id;
    LocalDate dataPreluare;
    LocalDate dataPredare;
    String locPreluare;
    String locPredare;
    String tipIncarcatura;

    /**
     * Constructor for the `curseDisponibile` class.
     *
     * @param id             The ID of the route
     * @param utilizator_id  The ID of the user associated with the route
     * @param dataPreluare   The pickup date of the route
     * @param dataPredare    The delivery date of the route
     * @param locPreluare    The pickup location of the route
     * @param locPredare     The delivery location of the route
     * @param tipIncarcatura The cargo type of the route
     */
    public curseDisponibile(int id, int utilizator_id, LocalDate dataPreluare, LocalDate dataPredare, String locPreluare, String locPredare, String tipIncarcatura) {
        this.id = id;
        this.utilizator_id = utilizator_id;
        this.dataPreluare = dataPreluare;
        this.dataPredare = dataPredare;
        this.locPreluare = locPreluare;
        this.locPredare = locPredare;
        this.tipIncarcatura = tipIncarcatura;
    }

    /**
     * Get the ID of the route.
     *
     * @return The ID of the route
     */
    public int getId() {
        return id;
    }

    /**
     * Get the ID of the user associated with the route.
     *
     * @return The ID of the user associated with the route
     */
    public int getUtilizator() {
        return utilizator_id;
    }

    /**
     * Get the pickup date of the route.
     *
     * @return The pickup date of the route
     */
    public LocalDate getPreluare() {
        return dataPreluare;
    }

    /**
     * Get the delivery date of the route.
     *
     * @return The delivery date of the route
     */
    public LocalDate getPredare() {
        return dataPredare;
    }

    /**
     * Get the pickup location of the route.
     *
     * @return The pickup location of the route
     */
    public String getLocPreluare() {
        return locPreluare;
    }

    /**
     * Get the delivery location of the route.
     *
     * @return The delivery location of the route
     */
    public String getLocPredare() {
        return locPredare;
    }

    /**
     * Get the cargo type of the route.
     *
     * @return The cargo type of the route
     */
    public String getTip() {
        return tipIncarcatura;
    }

    /**
     * Add a route to the database.
     *
     * @param connection     The database connection
     * @param utilizator_id  The ID of the user associated with the route
     * @param dataPreluare   The pickup date of the route
     * @param dataPredare    The delivery date of the route
     * @param locPreluare    The pickup location of the route
     * @param locPredare     The delivery location of the route
     * @param tipIncarcatura The cargo type of the route
     */
    public static void adaugaCursa(Connection connection, int utilizator_id, String dataPreluare, String dataPredare, String locPreluare, String locPredare, String tipIncarcatura) {
        if (connection != null) {
            String INSERT_QUERY = "INSERT INTO cursedisponibile (utilizator_id, dataPreluare, dataPredare, locPreluare, locPredare, tipIncarcatura) VALUES (?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, utilizator_id);
                preparedStatement.setObject(2, dataPreluare);
                preparedStatement.setObject(3, dataPredare);
                preparedStatement.setString(4, locPreluare);
                preparedStatement.setString(5, locPredare);
                preparedStatement.setString(6, tipIncarcatura);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Cursa a fost adăugată în tabela CURSEDISPONIBILE!");
                }
            } catch (SQLException e) {
                System.err.println("Eroare la adăugarea cursei: " + e.getMessage());
            }
        }
    }

    /**
     * Delete a route from the database.
     *
     * @param connection The database connection
     * @param idCursa    The ID of the route to be deleted
     */
    public static void stergeCursa(Connection connection, int idCursa) {
        if (connection != null) {
            try {
                String deleteQuery = "DELETE FROM cursedisponibile WHERE utilizator_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setInt(1, idCursa);

                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Cursa a fost ștearsă din tabelul cursedisponibile!");
                } else {
                    System.out.println("Nu s-a găsit cursa cu ID-ul specificat.");
                }
            } catch (SQLException e) {
                System.err.println("Eroare la ștergerea cursei: " + e.getMessage());
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
