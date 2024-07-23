package app.book.dto;

public class BookDto {
	private int BookId;
	private String bookName;
	private String publisher;
	private int price;
	
	public BookDto() {}
	
	public BookDto(int bookId, String bookName, String publisher, int price) {
		super();
		BookId = bookId;
		this.bookName = bookName;
		this.publisher = publisher;
		this.price = price;
	}
	public int getBookId() {
		return BookId;
	}
	public void setBookId(int bookId) {
		BookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "BookDto [BookId=" + BookId + ", bookName=" + bookName + ", publisher=" + publisher + ", price=" + price
				+ "]";
	}
}
