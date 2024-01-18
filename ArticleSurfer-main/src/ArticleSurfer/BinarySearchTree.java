package ArticleSurfer;

public class BinarySearchTree { //Article Library

	private TreeNode root;
	private int size;

	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	public boolean insert(TreeNode e) {
		if (root == null)
			root = e;
		else {
			TreeNode parent = null;
			TreeNode current = root;
			while (current != null) {
				if (e.getName().compareTo(current.getName()) < 0) {
					parent = current;
					current = current.getLeft();
				} else if (e.getName().compareTo(current.getName()) > 0) {
					parent = current;
					current = current.getRight();
				} else
					return false;
			}
			if (e.getName().compareTo(parent.getName()) < 0)
				parent.setLeft(e);
			else
				parent.setRight(e);
		}
		size++;
		return true;
	}

	public int getSize() {
		return size;
	}

	public TreeNode getRoot() {
		return root;
	}
}