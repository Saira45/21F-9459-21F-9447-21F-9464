package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import TransferObject.RootTO;

public class RootDal {
    public static void assignRootToWord(RootTO rootTO) throws SQLException {
        try (Connection conn = null;
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO WordRoot (word, root) VALUES (?, ?)")) {
            stmt.setString(1, rootTO.getWord());
            stmt.setString(2, rootTO.getRoot());
            stmt.executeUpdate();
        }
    }
}
