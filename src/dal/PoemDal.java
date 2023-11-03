package dal;
import TransferObject.PoemTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PoemDal {

	private static final String URL = "jdbc:mysql://localhost:3306/poems ";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	public static void close(Connection connection, PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void savePoem(PoemTO poem) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = getConnection();
			String sql = "INSERT INTO poems1 (title, verse_number, misra1) VALUES (?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, poem.getTitle());
			preparedStatement.setInt(2, poem.getVerseNumber());
			preparedStatement.setString(3, poem.getMisra1());
			
			preparedStatement.executeUpdate();
		} finally {
			close(connection, preparedStatement);
		}
	}

}
