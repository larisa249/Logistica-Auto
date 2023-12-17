import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The `distribuitori` class represents information about distributors and provides methods for adding and deleting them from a database.
 */
public class distribuitori {
    int id;
    int utilizator_id;
    String CUI;
    String numeDistribuitor;
    String adresaSediu;
    String nrTelefon;
    String tipMarfaDistribuita;

    /**
     * Constructor for the `distribuitori` class.
     *
     * @param id                  The distributor's ID
     * @param utilizator_id       The ID of the user associated with the distributor
     * @param CUI                 The Unique Registration Code of the distributor
     * @param numeDistribuitor    The distributor's name
     * @param adresaSediu         The distributor's headquarters address
     * @param nrTelefon           The distributor's phone number
     * @param tipMarfaDistribuita The type of merchandise distributed by the distributor
     */
    public distribuitori(int id, int utilizator_id, String CUI, String numeDistribuitor, String adresaSediu, String nrTelefon, String tipMarfaDistribuita) {
        this.id = id;
        this.adresaSediu = adresaSediu;
        this.CUI = CUI;
        this.nrTelefon = nrTelefon;
        this.numeDistribuitor = numeDistribuitor;
        this.tipMarfaDistribuita = tipMarfaDistribuita;
        this.utilizator_id = utilizator_id;
    }

    /**
     * Get the distributor's ID.
     *
     * @return The distributor's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Get the ID of the user associated with the distributor.
     *
     * @return The ID of the user associated with the distributor
     */
    public int getUtilizator() {
        return utilizator_id;
    }

    /**
     * Get the Unique Registration Code of the distributor.
     *
     * @return The Unique Registration Code of the distributor
     */
    public String getCUI() {
        return CUI;
    }

    /**
     * Get the distributor's name.
     *
     * @return The distributor's name
     */
    public String getNumeDistribuitor() {
        return numeDistribuitor;
    }

    /**
     * Get the distributor's headquarters address.
     *
     * @return The distributor's headquarters address
     */
    public String getAdresa() {
        return adresaSediu;
    }

    /**
     * Get the distributor's phone number.
     *
     * @return The distributor's phone number
     */
    public String getTelefon() {
        return nrTelefon;
    }

    /**
     * Get the type of merchandise distributed by the distributor.
     *
     * @return The type of merchandise distributed by the distributor
     */
    public String getMarfa() {
        return tipMarfaDistribuita;
    }

    /**
     * Add a distributor to the database.
     *
     * @param connection         The database connection
     * @param cui                The Unique Registration Code of the distributor
     * @param numeDistribuitor   The distributor's name
     * @param adresaSediu        The distributor's headquarters address
     * @param nrTelefon          The distributor's phone number
     * @param tipMarfaDistribuita The type of merchandise distributed by the distributor
     * @param utilizatorId       The ID of the user associated with the distributor
     */
    public static void adaugaDistribuitor(Connection connection, String cui, String numeDistribuitor, String adresaSediu, String nrTelefon, String tipMarfaDistribuita, int utilizatorId) {
        if (connection != null) {
            String INSERT_QUERY = "INSERT INTO DISTRIBUITORI (cui, numeDistribuitor, adresaSediu, nrTelefon, tipMarfaDistribuita, utilizator_id) VALUES (?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
                preparedStatement.setString(1, cui);
                preparedStatement.setString(2, numeDistribuitor);
                preparedStatement.setString(3, adresaSediu);
                preparedStatement.setString(4, nrTelefon);
                preparedStatement.setString(5, tipMarfaDistribuita);
                preparedStatement.setInt(6, utilizatorId);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("The distributor has been added to the DISTRIBUITORI table!");
                }
            } catch (SQLException e) {
                System.err.println("Error adding distributor: " + e.getMessage());
            }
        }
    }

    /**
     * Delete a distributor from the database.
     *
     * @param connection      The database connection
     * @param idDistribuitor The ID of the distributor to be deleted
     */
    public static void stergeDistribuitor(Connection connection, int idDistribuitor) {
        if (connection != null) {
            try {
                String deleteQuery = "DELETE FROM distribuitori WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setInt(1, idDistribuitor);

                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("The distributor has been deleted from the distribuitori table!");
                } else {
                    System.out.println("No distributor with the specified ID was found.");
                }
            } catch (SQLException e) {
                System.err.println("Error deleting distributor: " + e.getMessage());
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
