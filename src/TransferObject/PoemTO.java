package TransferObject;

public class PoemTO {

	private int id;
    private int bookId; 
    private String title;
    private int verseNumber;
    private String misra1;
    private String misra2;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getVerseNumber() {
		return verseNumber;
	}
	public void setVerseNumber(int verseNumber) {
		this.verseNumber = verseNumber;
	}
	public String getMisra1() {
		return misra1;
	}
	public void setMisra1(String misra1) {
		this.misra1 = misra1;
	}
	public String getMisra2() {
		return misra2;
	}
	public void setMisra2(String misra2) {
		this.misra2 = misra2;
	}

	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

}
