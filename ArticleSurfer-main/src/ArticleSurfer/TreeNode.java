package ArticleSurfer;

public class TreeNode { //Article

	private String name;
	private String author;
	private String type;
	private String year;
	private TreeNode left;
	private TreeNode right;

	public TreeNode(String name) {
		this.name = name;
		left = null;
		right = null;
	}

	public TreeNode(String name, String author, String type, String year) {
		this.name = name;
		this.author = author;
		this.type = type;
		this.year = year;
		left = null;
		right = null;
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

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}
}