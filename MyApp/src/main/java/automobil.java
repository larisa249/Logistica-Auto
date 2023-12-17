import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The `automobil` class represents information about automobiles and provides methods for adding and deleting them from a database.
 */
public class automobil {
    int id;
    int utilizator_id;
    String tipAutomobil;
    String inaltimeMaxima;
    String cantitateMaxima;
    String verificareITP;
    String asigurare;

    /**
     * Constructor for the `automobil` class.
     *
     * @param id             The ID of the automobile
     * @param tipAutomobil   The type of the automobile
     * @param inaltimeMaxima The maximum height of the automobile
     * @param cantitateMaxima The maximum quantity supported by the automobile
     * @param asigurare      Information about insurance
     * @param verificareITP  Information about the ITP check
     * @param utilizator_id  The ID of the user associated with the automobile
     */
    public automobil(int id, String tipAutomobil, String inaltimeMaxima, String cantitateMaxima, String asigurare, String verificareITP, int utilizator_id) {
        this.id = id;
        this.tipAutomobil = tipAutomobil;
        this.inaltimeMaxima = inaltimeMaxima;
        this.cantitateMaxima = cantitateMaxima;
        this.verificareITP = verificareITP;
        this.asigurare = asigurare;
        this.utilizator_id = utilizator_id;
    }

    /**
     * Get the ID of the automobile.
     *
     * @return The ID of the automobile
     */
    public int getId() {
        return id;
    }

    /**
     * Get the ID of the user associated with the automobile.
     *
     * @return The ID of the user associated with the automobile
     */
    public int getUtilizator() {
        return utilizator_id;
    }

    /**
     * Get the type of the automobile.
     *
     * @return The type of the automobile
     */
    public String getTip() {
        return tipAutomobil;
    }

    /**
     * Get the maximum height of the automobile.
     *
     * @return The maximum height of the automobile
     */
    public String getInaltime() {
        return inaltimeMaxima;
    }

    /**
     * Get the maximum quantity supported by the automobile.
     *
     * @return The maximum quantity supported by the automobile
     */
    public String getCantitate() {
        return cantitateMaxima;
    }

    /**
     * Get information about the ITP check of the automobile.
     *
     * @return Information about the ITP check
     */
    public String getVerificare() {
        return verificareITP;
    }

    /**
     * Get information about the insurance of the automobile.
     *
     * @return Information about the insurance
     */
    public String getAsigurare() {
        return asigurare;
    }

    /**
     * Add an automobile to the database.
     *
     * @param connection      The database connection
     * @param tipAutomobil   The type of the automobile
     * @param inaltimeMaxima The maximum height of the automobile
     * @param cantitateMaxima The maximum quantity supported by the automobile
     * @param asigurare      Information about insurance
     * @param verificareITP  Information about the ITP check
     * @param utilizator_id  The ID of the user associated with the automobile
     */
    public static void adaugaAutomobil(Connection connection, String tipAutomobil, String inaltimeMaxima, String cantitateMaxima, String verificareITP, String asigurare, int utilizator_id) {
        if (connection != null) {
            String INSERT_QUERY = "INSERT INTO automobil (tipAutomobil, inaltimeMaxima, cantitateMaxima, verificareITP, asigurare, utilizator_id) VALUES (?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, tipAutomobil);
                preparedStatement.setString(2, inaltimeMaxima);
                preparedStatement.setString(3, cantitateMaxima);
                preparedStatement.setString(4, verificareITP);
                preparedStatement.setString(5, asigurare);
                preparedStatement.setInt(6, utilizator_id);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Automobilul a fost adăugat în tabela automobil!");
                }
            } catch (SQLException e) {
                System.err.println("Eroare la adăugarea automobilului: " + e.getMessage());
            }
        }
    }

    /**
     * Delete an automobile from the database.
     *
     * @param connection    The database connection
     * @param idAutomobil   The ID of the automobile to be deleted
     */
    public static void stergeAutomobil(Connection connection, int idAutomobil) {
        if (connection != null) {
            try {
                String deleteQuery = "DELETE FROM automobil WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setInt(1, idAutomobil);

                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Automobilul a fost șters din tabelul automobil!");
                } else {
                    System.out.println("Nu s-a găsit automobilul cu ID-ul specificat.");
                }
            } catch (SQLException e) {
                System.err.println("Eroare la ștergerea automobilului: " + e.getMessage());
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
