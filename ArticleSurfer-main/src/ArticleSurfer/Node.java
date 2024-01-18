package ArticleSurfer;

public class Node { //Article

	private String name;
	private String author;
	private String type;
	private String year;
	private Node prev;
	private Node next;

	public Node() {
		prev = null;
		next = null;
		name = null;
	}

	public Node(String name) {
		prev = null;
		next = null;
		this.name = name;
	}

	public Node(String name, String author, String type, String year) {
		super();
		this.name = name;
		this.author = author;
		this.type = type;
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public String toString() {
		return name + "-" + author + "-" + type + "-" + year;
	}
}