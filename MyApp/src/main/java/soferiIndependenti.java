import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The `soferiIndependenti` class represents information about independent drivers and provides methods for adding and deleting them from a database.
 */
public class soferiIndependenti {
    int id;
    int utilizator_id;
    String nrTelefon;
    String CNP;
    String numeSofer;
    String domiciliu;
    char categoriePermis;
    String disponibilitate;
    char atestat;

    /**
     * Constructor for the `soferiIndependenti` class.
     *
     * @param id               The ID of the independent driver
     * @param utilizator_id    The ID of the user associated with the independent driver
     * @param nrTelefon        The phone number of the independent driver
     * @param CNP              The Personal Numeric Code of the independent driver
     * @param numeSofer        The name of the independent driver
     * @param domiciliu        The residence of the independent driver
     * @param categoriePermis  The driver's license category of the independent driver
     * @param disponibilitate  The availability of the independent driver
     * @param atestat          The certificate of the independent driver
     */
    public soferiIndependenti(int id, int utilizator_id, String nrTelefon, String CNP, String numeSofer, String domiciliu, char categoriePermis, String disponibilitate, char atestat) {
        this.id = id;
        this.utilizator_id = utilizator_id;
        this.nrTelefon = nrTelefon;
        this.CNP = CNP;
        this.numeSofer = numeSofer;
        this.domiciliu = domiciliu;
        this.categoriePermis = categoriePermis;
        this.disponibilitate = disponibilitate;
        this.atestat = atestat;
    }

    /**
     * Get the ID of the independent driver.
     *
     * @return The ID of the independent driver
     */
    public int getId() {
        return id;
    }

    /**
     * Get the ID of the user associated with the independent driver.
     *
     * @return The ID of the user associated with the independent driver
     */
    public int getUtilizator() {
        return utilizator_id;
    }

    /**
     * Get the Personal Numeric Code (CNP) of the independent driver.
     *
     * @return The Personal Numeric Code (CNP) of the independent driver
     */
    public String getCNP() {
        return CNP;
    }

    /**
     * Get the phone number of the independent driver.
     *
     * @return The phone number of the independent driver
     */
    public String getTelefon() {
        return nrTelefon;
    }

    /**
     * Get the name of the independent driver.
     *
     * @return The name of the independent driver
     */
    public String getNume() {
        return numeSofer;
    }

    /**
     * Get the residence of the independent driver.
     *
     * @return The residence of the independent driver
     */
    public String getDomiciliu() {
        return domiciliu;
    }

    /**
     * Get the driver's license category of the independent driver.
     *
     * @return The driver's license category of the independent driver
     */
    public char getCategorie() {
        return categoriePermis;
    }

    /**
     * Get the availability of the independent driver.
     *
     * @return The availability of the independent driver
     */
    public String getDisponibilitate() {
        return disponibilitate;
    }

    /**
     * Get the certificate of the independent driver.
     *
     * @return The certificate of the independent driver
     */
    public char getAtestat() {
        return atestat;
    }

    /**
     * Add an independent driver to the database.
     *
     * @param connection      The database connection
     * @param cnp             The Personal Numeric Code (CNP) of the independent driver
     * @param numeSofer       The name of the independent driver
     * @param domiciliu       The residence of the independent driver
     * @param nrTelefon       The phone number of the independent driver
     * @param categoriePermis The driver's license category of the independent driver
     * @param disponibilitate The availability of the independent driver
     * @param atestat         The certificate of the independent driver
     * @param utilizatorId    The ID of the user associated with the independent driver
     */
    public static void adaugaSofer(Connection connection, String cnp, String numeSofer, String domiciliu, String nrTelefon, String categoriePermis, String disponibilitate, String atestat, int utilizatorId) {
        if (connection != null) {
            String INSERT_QUERY = "INSERT INTO SOFERI (cnp, numeSofer, domiciliu, nrTelefon, categoriePermis, disponibilitate, atestat, utilizator_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, cnp);
                preparedStatement.setString(2, numeSofer);
                preparedStatement.setString(3, domiciliu);
                preparedStatement.setString(4, nrTelefon);
                preparedStatement.setString(5, categoriePermis);
                preparedStatement.setString(6, disponibilitate);
                preparedStatement.setString(7, atestat);
                preparedStatement.setInt(8, utilizatorId);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("The driver has been added to the SOFERI table!");
                }
            } catch (SQLException e) {
                System.err.println("Error adding driver: " + e.getMessage());
            }
        }
    }

    /**
     * Delete an independent driver from the database.
     *
     * @param connection The database connection
     * @param idSofer    The ID of the independent driver to be deleted
     */
    public static void stergeSofer(Connection connection, int idSofer) {
        if (connection != null) {
            try {
                String deleteQuery = "DELETE FROM soferi WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setInt(1, idSofer);

                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("The driver has been deleted from the SOFERI table!");
                } else {
                    System.out.println("No driver with the specified ID was found.");
                }
            } catch (SQLException e) {
                System.err.println("Error deleting driver: " + e.getMessage());
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
