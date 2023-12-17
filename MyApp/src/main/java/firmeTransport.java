import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The `firmeTransport` class represents information about transport companies and provides methods for adding and deleting them from a database.
 */
public class firmeTransport {
    int id;
    int utilizator_id;
    String CUI;
    String nrTelefon;
    String adresaSediu;
    String denumireCompanie;
    String tipCamioane;
    String tipMarfaPosibila;
    String actDoveditor;

    /**
     * Constructor for the `firmeTransport` class.
     *
     * @param id                  The ID of the transport company
     * @param utilizator_id       The ID of the user associated with the transport company
     * @param CUI                 The Unique Registration Code of the transport company
     * @param nrTelefon           The phone number of the transport company
     * @param adresaSediu         The headquarters address of the transport company
     * @param denumireCompanie    The name of the transport company
     * @param tipCamioane         The type of trucks used by the company
     * @param tipMarfaPosibila    The type of merchandise that the company can transport
     * @param actDoveditor        The proof of the transport company
     */
    public firmeTransport(int id, int utilizator_id, String CUI, String nrTelefon, String adresaSediu, String denumireCompanie, String tipCamioane, String tipMarfaPosibila, String actDoveditor) {
        this.id = id;
        this.adresaSediu = adresaSediu;
        this.CUI = CUI;
        this.nrTelefon = nrTelefon;
        this.denumireCompanie = denumireCompanie;
        this.tipCamioane = tipCamioane;
        this.tipMarfaPosibila = tipMarfaPosibila;
        this.actDoveditor = actDoveditor;
        this.utilizator_id = utilizator_id;
    }

    /**
     * Get the name of the transport company.
     *
     * @return The name of the transport company
     */
    public String getDenumire() {
        return denumireCompanie;
    }

    /**
     * Get the type of trucks used by the company.
     *
     * @return The type of trucks used by the company
     */
    public String getTipCamioane() {
        return tipCamioane;
    }

    /**
     * Get the type of merchandise that the company can transport.
     *
     * @return The type of merchandise that the company can transport
     */
    public String getTipMarfa() {
        return tipMarfaPosibila;
    }

    /**
     * Get the proof of the transport company.
     *
     * @return The proof of the transport company
     */
    public String getAct() {
        return actDoveditor;
    }

    /**
     * Get the ID of the transport company.
     *
     * @return The ID of the transport company
     */
    public int getId() {
        return id;
    }

    /**
     * Get the ID of the user associated with the transport company.
     *
     * @return The ID of the user associated with the transport company
     */
    public int getUtilizator() {
        return utilizator_id;
    }

    /**
     * Get the Unique Registration Code of the transport company.
     *
     * @return The Unique Registration Code of the transport company
     */
    public String getCUI() {
        return CUI;
    }

    /**
     * Get the headquarters address of the transport company.
     *
     * @return The headquarters address of the transport company
     */
    public String getAdresa() {
        return adresaSediu;
    }

    /**
     * Get the phone number of the transport company.
     *
     * @return The phone number of the transport company
     */
    public String getTelefon() {
        return nrTelefon;
    }

    /**
     * Add a transport company to the database.
     *
     * @param connection      The database connection
     * @param cui             The Unique Registration Code of the transport company
     * @param denumireCompanie The name of the transport company
     * @param adresaSediu     The headquarters address of the transport company
     * @param nrTelefon       The phone number of the transport company
     * @param tipCamioane     The type of trucks used by the company
     * @param tipMarfaPosibila The type of merchandise that the company can transport
     * @param actDoveditor    The proof of the transport company
     * @param utilizatorId    The ID of the user associated with the transport company
     */
    public static void adaugaFirma(Connection connection, String cui, String denumireCompanie, String adresaSediu, String nrTelefon, String tipCamioane, String tipMarfaPosibila, String actDoveditor, int utilizatorId) {
        if (connection != null) {
            String INSERT_QUERY = "INSERT INTO FIRMETRANSPORT (CUI, denumireCompanie , adresaSediu, nrTelefon, tipCamioane, tipMarfaPosibila, actDoveditor, utilizator_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);

                preparedStatement.setString(1, cui);
                preparedStatement.setString(2, denumireCompanie);
                preparedStatement.setString(3, adresaSediu);
                preparedStatement.setString(4, nrTelefon);
                preparedStatement.setString(5, tipCamioane);
                preparedStatement.setString(6, tipMarfaPosibila);
                preparedStatement.setString(7, actDoveditor);
                preparedStatement.setInt(8, utilizatorId);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("The company has been added to the FIRMETRANSPORT table!");
                }
            } catch (SQLException e) {
                System.err.println("Error adding company: " + e.getMessage());
            }
        }
    }

    /**
     * Delete a transport company from the database.
     *
     * @param connection The database connection
     * @param idFirma    The ID of the transport company to be deleted
     */
    public static void stergeFirma(Connection connection, int idFirma) {
        if (connection != null) {
            try {
                String deleteQuery = "DELETE FROM firmetransport WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setInt(1, idFirma);

                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("The company has been deleted from the FIRMETRANSPORT table!");
                } else {
                    System.out.println("No company with the specified ID was found.");
                }
            } catch (SQLException e) {
                System.err.println("Error deleting company: " + e.getMessage());
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
