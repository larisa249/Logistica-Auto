import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class parteneriate {
	String coordonator;
	String transportator;
	String dataPreluare;
	String dataPredare;
	String locPreluare;
	String locPredare;
	String tipIncarcatura;
	
	public parteneriate(String coordonator, String transportator, String dataPreluare, String dataPredare, String locPreluare, String locPredare, String tipIncarcatura) {
		this.coordonator=coordonator;
		this.tipIncarcatura=tipIncarcatura;
		this.transportator=transportator;
		this.dataPreluare=dataPreluare;
		this.dataPredare=dataPredare;
		this.locPreluare=locPreluare;
		this.locPredare=locPredare;
	}
	
	public static int adaugaParteneriat(Connection connection, String coordonator, String transportator, String dataPreluare, String dataPredare, String locPreluare, String locPredare, String tipIncarcatura) {
        int utilizatorId = -1;
        if (connection != null) {
            String INSERT_QUERY = "INSERT INTO parteneriate (coordonator,transportator,dataPreluare,dataPredare,locPreluare,locPredare,tipIncarcatura) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, coordonator);
                preparedStatement.setString(2, transportator);
                preparedStatement.setString(3, dataPreluare);
                preparedStatement.setString(4, dataPredare);
                preparedStatement.setString(5, locPreluare);
                preparedStatement.setString(6, locPredare);
                preparedStatement.setString(7, tipIncarcatura);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    ResultSet rs = preparedStatement.getGeneratedKeys();
                    if (rs.next()) {
                        utilizatorId = rs.getInt(1);
                    }
                }
            } catch (SQLException e) {
                System.err.println( e.getMessage());
            }
        }
        return utilizatorId;
    }
}
