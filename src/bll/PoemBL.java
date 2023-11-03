package bll;
import dal.PoemDal;
import TransferObject.PoemTO;
import java.sql.SQLException;

public class PoemBL {

	private PoemDal poemDAO;
	public PoemBL() {
		// TODO Auto-generated constructor stub
		this.poemDAO = new PoemDal();
	}
	

    public void savePoem(PoemTO poem) throws SQLException {
        poemDAO.savePoem(poem);
    }

}
