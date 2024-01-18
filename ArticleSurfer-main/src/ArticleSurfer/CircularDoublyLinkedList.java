package ArticleSurfer;

public class CircularDoublyLinkedList { //Article List

	private String name;
	private Node first;
	private int size;

	public CircularDoublyLinkedList() {
		first = null;
		name = null;
		size = 0;
	}

	public void insertEnd(String name, String author, String type, String year) {
		if (first == null) {
			Node newNode = new Node(name, author, type, year);
			newNode.setNext(newNode);
			newNode.setPrev(newNode);
			first = newNode;
			size++;
			return;
		}
		Node last = first.getPrev();
		Node newNode = new Node(name, author, type, year);
		newNode.setNext(first);
		first.setPrev(newNode);
		newNode.setPrev(last);
		last.setNext(newNode);
		size++;
	}

	public boolean search(String e) {
		if (first == null)
			return false;
		else if (this.getSize() == 1)
			return (first.toString().equals(e));
		else {
			Node current = first;
			while (current.getNext() != first) {
				if (current.toString().equals(e))
					return true;
				current = current.getNext();
			}
			return current.toString().equals(e);
		}
	}
	
	public void deleteNode(String key) {
		if (first == null)
			return;

		Node current = first;
		Node prev = null;

		while (!current.getName().equals(key)) {
			if (current.getNext() == first)
				return;
			prev = current;
			current = current.getNext();
		}
		if (current.getNext() == first && prev == null) {
			first = null;
			size--;
			return;
		}
		if (current == first) {
			prev = first.getPrev();
			first = first.getNext();
			prev.setNext(first);
			first.setPrev(prev);
			size--;
		} else if (current.getNext() == first) {
			prev.setNext(first);
			first.setPrev(prev);
			size--;
		} else {
			Node temp = current.getNext();
			prev.setNext(temp);
			temp.setPrev(prev);
			size--;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}