package bll;

import java.sql.SQLException;

import TransferObject.RootTO;
import dal.RootDal;

public class RootBL {

	public RootBL() {
		
	}
	 public void assignRootToWord(RootTO rootTO) throws SQLException {
		 RootDal.assignRootToWord(rootTO);
	 }

}
