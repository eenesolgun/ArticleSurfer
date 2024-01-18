package ArticleSurfer;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ArticleListsFrame extends JFrame {
	
	private static final long serialVersionUID = 1L; //Default Serial Version ID
	
	//Article Lists
	private CircularDoublyLinkedList[] articleLists = new CircularDoublyLinkedList[10]; //I created this array to easily access to articleList_0, articleList_1, articleList_2, articleList_3, articleList_4 ... lists.
	
	private CircularDoublyLinkedList articleList_0 = new CircularDoublyLinkedList();
	private CircularDoublyLinkedList articleList_1 = new CircularDoublyLinkedList();
	private CircularDoublyLinkedList articleList_2 = new CircularDoublyLinkedList();
	private CircularDoublyLinkedList articleList_3 = new CircularDoublyLinkedList();
	private CircularDoublyLinkedList articleList_4 = new CircularDoublyLinkedList();
	private CircularDoublyLinkedList articleList_5 = new CircularDoublyLinkedList();
	private CircularDoublyLinkedList articleList_6 = new CircularDoublyLinkedList();
	private CircularDoublyLinkedList articleList_7 = new CircularDoublyLinkedList();
	private CircularDoublyLinkedList articleList_8 = new CircularDoublyLinkedList();
	private CircularDoublyLinkedList articleList_9 = new CircularDoublyLinkedList();
	
	//Panel
	private JPanel contentPane = new JPanel();
	
	//Scroll Pane
	private DefaultListModel<String> articleListsModel = new DefaultListModel<String>(); //List of article lists' names hold in JList component (Article list options that users will see on ArticleListsFrame).
	private JList<String> list = new JList<String>(articleListsModel);
	private JScrollPane scrollPane = new JScrollPane(list);
	
	//Label
	private JLabel lblMyArticleLists = new JLabel("My Article Lists:");
	
	//Buttons
	static JButton btnReturn = new JButton();
	static JButton btnDisplaySelectedArticleList = new JButton("Display Selected Article List");
	static JButton btnClearAllContent = new JButton("Clear All Content");
	static JButton btnAddSelectedArticleToArticleList = new JButton("Add Selected Article To Article List");
	static JButton btnDeleteSelectedArticleFromArticleList = new JButton("Delete Selected Article From Article List");
	
	//A list of articles in an article list.
	private DefaultListModel<String> articleListArticles = new DefaultListModel<String>();
	
	private String temp; //This variable is used to prevent the same article from being added to a list two or more times.
	private String key; //This variable is used to delete the selected article from the article list.
	
	static int indexValue; //This integer variable lets us know which article list we are in. We are using this variable for "Delete Selected Article From Article List" button.
	
	public static void main(String[] args) {
		ArticleListsFrame articleListsFrame = new ArticleListsFrame();
		articleListsFrame.setResizable(false); //It makes "ArticleListsFrame" not resizable.
		articleListsFrame.setLocation(725, 115); //This code sets this frame's opening location at the specified coordinates (725, 115).
		articleListsFrame.setVisible(true);
	}

	public ArticleListsFrame() {
		setGUI(); //This function makes GUI-related arrangements.
		createDatabase(); //This function adds 10 article lists whose type is circular doubly linked list to the array named "articleLists".
		setActionListener(); //This function captures an event on the buttons assigned to the frame and performs an appropriate action.
	}

	public void setGUI() {

		//*****Set "ArticleListsFrame" Interface*****
		setTitle("Article Surfer");
		setIconImage(new ImageIcon(this.getClass().getResource("ApplicationIcon.jpeg")).getImage()); //This code adds icon to the frame.
		setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		UIManager.put("Button.disabledText", new Color((int) UIManager.getColor("Button.disabledText").getRed() - 5, (int) UIManager.getColor("Button.disabledText").getGreen() - 5, (int) UIManager.getColor("Button.disabledText").getBlue() - 5)); //This code makes the color of disabled buttons' texts saturated.
		setBounds(0, 0, 490, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(contentPane);

		//*****Set "ArticleListsFrame's Components" Interface*****

		//Set "contentPane"
		contentPane.setBackground(new Color(255, 247, 243));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(lblMyArticleLists);
		contentPane.add(scrollPane);
		contentPane.add(btnReturn);
		contentPane.add(btnDisplaySelectedArticleList);
		contentPane.add(btnClearAllContent);
		contentPane.add(btnAddSelectedArticleToArticleList);
		contentPane.add(btnDeleteSelectedArticleFromArticleList);

		//Set "scrollPane" **"Scroll Pane" Interface**
		scrollPane.setBounds(35, 54, 403, 298);
		scrollPane.setViewportView(list);
		list.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14)); //"list" settings.
		list.setBackground(new Color(211, 207, 236)); //"list" settings.
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //"list" settings.

		//Set **Label's Interface**

		//Set "lblMyArticleLists"
		lblMyArticleLists.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblMyArticleLists.setBounds(185, 11, 100, 32);
		lblMyArticleLists.setForeground(new Color(0, 115, 0));

		//Set **Buttons' Interface**
		
		//Set "btnReturn"
		btnReturn.setIcon(new ImageIcon(this.getClass().getResource("ReturnIcon.png"))); //This code adds icon to the button.
		btnReturn.setBorder(new EmptyBorder(0, 0, 0, 0)); //This code completely removes border of buttons.
		btnReturn.setBounds(5, 10, 32, 32);
		btnReturn.setBackground(new Color(255, 247, 243));
		btnReturn.setEnabled(false); //Initial setting.
		
		//Set "btnDisplaySelectedArticleList"
		btnDisplaySelectedArticleList.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btnDisplaySelectedArticleList.setFocusPainted(false); //This code removes border of buttons when clicking on it.
		btnDisplaySelectedArticleList.setBounds(45, 369, 200, 32);
		btnDisplaySelectedArticleList.setBackground(new Color(51, 255, 255));
		
		//Set "btnClearAllContent"
		btnClearAllContent.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btnClearAllContent.setFocusPainted(false); //This code removes border of buttons when clicking on it.
		btnClearAllContent.setBounds(282, 369, 145, 32);
		btnClearAllContent.setBackground(new Color(51, 255, 255));

		//Set "btnAddSelectedArticleToArticleList"
		btnAddSelectedArticleToArticleList.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btnAddSelectedArticleToArticleList.setFocusPainted(false); //This code removes border of buttons when clicking on it.
		btnAddSelectedArticleToArticleList.setBounds(115, 415, 245, 32);
		btnAddSelectedArticleToArticleList.setBackground(new Color(51, 255, 255));
		
		//Set "btnDeleteSelectedArticleFromArticleList"
		btnDeleteSelectedArticleFromArticleList.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btnDeleteSelectedArticleFromArticleList.setFocusPainted(false); //This code removes border of buttons when clicking on it.
		btnDeleteSelectedArticleFromArticleList.setBounds(98, 379, 280, 32);
		btnDeleteSelectedArticleFromArticleList.setBackground(new Color(51, 255, 255));
		btnDeleteSelectedArticleFromArticleList.setVisible(false);
	}
	
	public void createDatabase() {
		articleLists[0] = articleList_0;
		articleLists[1] = articleList_1;
		articleLists[2] = articleList_2;
		articleLists[3] = articleList_3;
		articleLists[4] = articleList_4;
		articleLists[5] = articleList_5;
		articleLists[6] = articleList_6;
		articleLists[7] = articleList_7;
		articleLists[8] = articleList_8;
		articleLists[9] = articleList_9;
	}

	public void setActionListener() {
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.setModel(articleListsModel); //This code updates the image of the JList component.
				btnReturn.setEnabled(false); //This code disables the return button.
				btnDisplaySelectedArticleList.setVisible(true); //This code makes "Display Selected Article List" button visible.
				btnClearAllContent.setVisible(true);
				btnAddSelectedArticleToArticleList.setVisible(true);
				btnDeleteSelectedArticleFromArticleList.setVisible(false);
			}
		});

		btnDisplaySelectedArticleList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() == -1) JOptionPane.showMessageDialog(null, "Please select an article list!", "Error", JOptionPane.ERROR_MESSAGE);
				else { //If any article list is selected.
					btnReturn.setEnabled(true); //This code enables the return button.
					btnDisplaySelectedArticleList.setVisible(false); //This code makes "Display Selected Article List" button invisible.
					btnClearAllContent.setVisible(false); //This code makes "Clear All Content" button invisible.
					btnAddSelectedArticleToArticleList.setVisible(false); //This code makes "Add Selected Article To Article List" button invisible.
					btnDeleteSelectedArticleFromArticleList.setVisible(true); //This code makes "Delete Selected Article From Article List" button visible.
					switch(list.getSelectedIndex()) {
					case 0 : //If the first article list (at index 0) is selected.
						indexValue = 0; //This integer variable lets us know which article list we are in.
						articleListArticles.clear(); //This code clears the content of "articleListArticles" list.
						if (articleList_0.getFirst() != null) { //If the first article list is not empty.
						Node current = articleList_0.getFirst(); //Node named "current" points first element of circular doubly linked list named "articleList_0".
						while (current.getNext() != articleList_0.getFirst()) { //While loop continues until the "current" node points to the last node in the list named "articleList_0".
							articleListArticles.addElement(current.toString()); //Adds the contents of the "current" node to the list named "articleListArticles" as an element.
							current = current.getNext(); //Update the "current" node.
						}
						articleListArticles.addElement(current.toString()); //Since the while loop ends when the "current" node points to the last node of the list named "articleList_0", the last node of "articleList_0" list is not added as an element to "articleListArticles" list. This code allows us to add the last node of "articleList_0" list as an element to "articleListArticles" list.
						}
						list.setModel(articleListArticles); //This code updates the image of the JList component.
						break;
					case 1:
						indexValue = 1;
						articleListArticles.clear();
						if (articleList_1.getFirst() != null) {
							Node current = articleList_1.getFirst();
							while (current.getNext() != articleList_1.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					case 2:
						indexValue = 2;
						articleListArticles.clear();
						if (articleList_2.getFirst() != null) {
							Node current = articleList_2.getFirst();
							while (current.getNext() != articleList_2.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					case 3:
						indexValue = 3;
						articleListArticles.clear();
						if (articleList_3.getFirst() != null) {
							Node current = articleList_3.getFirst();
							while (current.getNext() != articleList_3.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					case 4:
						indexValue = 4;
						articleListArticles.clear();
						if (articleList_4.getFirst() != null) {
							Node current = articleList_4.getFirst();
							while (current.getNext() != articleList_4.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					case 5:
						indexValue = 5;
						articleListArticles.clear();
						if (articleList_5.getFirst() != null) {
							Node current = articleList_5.getFirst();
							while (current.getNext() != articleList_5.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					case 6:
						indexValue = 6;
						articleListArticles.clear();
						if (articleList_6.getFirst() != null) {
							Node current = articleList_6.getFirst();
							while (current.getNext() != articleList_6.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					case 7:
						indexValue = 7;
						articleListArticles.clear();
						if (articleList_7.getFirst() != null) {
							Node current = articleList_7.getFirst();
							while (current.getNext() != articleList_7.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					case 8:
						indexValue = 8;
						articleListArticles.clear();
						if (articleList_8.getFirst() != null) {
							Node current = articleList_8.getFirst();
							while (current.getNext() != articleList_8.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					case 9:
						indexValue = 9;
						articleListArticles.clear();
						if (articleList_9.getFirst() != null) {
							Node current = articleList_9.getFirst();
							while (current.getNext() != articleList_9.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					}
				}
			}
		});
		
		btnClearAllContent.addActionListener(new ActionListener() { //This button deletes all article lists with articles inserted to them.
			public void actionPerformed(ActionEvent e) {
		        int controller = JOptionPane.showOptionDialog(null, "Are you sure?", "Clear All Content", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		        if (controller == JOptionPane.OK_OPTION) {
		        	articleListsModel.clear();
		        	for(int i = 0; i < 10; i++) {
		        	articleLists[i].setFirst(null);
		        	articleLists[i].setName(null);
		        	articleLists[i].setSize(0);
		        	}
		        	MainFrame.btnCreateArticleList.setEnabled(true);
		        }
		    }
		});

		btnAddSelectedArticleToArticleList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(temp == null) {
					JOptionPane.showMessageDialog(null, "Please select an article!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					switch(list.getSelectedIndex()) {
					case -1: //If no article list is selected.
						JOptionPane.showMessageDialog(null, "Please select an article list!", "Error", JOptionPane.ERROR_MESSAGE);
						break;
					case 0 : //If the first article list (at index 0) is selected.
						if(articleList_0.search(temp)) JOptionPane.showMessageDialog(null, "This article is already in the article list to which you are trying to add it.", "Error", JOptionPane.ERROR_MESSAGE); //This if statement checks if the article selected by the user in the "MainFrame" is already in the article list that user wants to add and it prevents the same article from being added to a list two or more times.
						else articleList_0.insertEnd(temp.split("-")[0], temp.split("-")[1], temp.split("-")[2], temp.split("-")[3]); //The article selected by the user in "MainFrame" is added to circular doubly linked list named "articleList_0".
						break;
					case 1 :
						if(articleList_1.search(temp)) JOptionPane.showMessageDialog(null, "This article is already in the article list to which you are trying to add it.", "Error", JOptionPane.ERROR_MESSAGE);
						else articleList_1.insertEnd(temp.split("-")[0], temp.split("-")[1], temp.split("-")[2], temp.split("-")[3]);
						break;
					case 2 :
						if(articleList_2.search(temp)) JOptionPane.showMessageDialog(null, "This article is already in the article list to which you are trying to add it.", "Error", JOptionPane.ERROR_MESSAGE);
						else articleList_2.insertEnd(temp.split("-")[0], temp.split("-")[1], temp.split("-")[2], temp.split("-")[3]);
						break;
					case 3 :
						if(articleList_3.search(temp)) JOptionPane.showMessageDialog(null, "This article is already in the article list to which you are trying to add it.", "Error", JOptionPane.ERROR_MESSAGE);
						else articleList_3.insertEnd(temp.split("-")[0], temp.split("-")[1], temp.split("-")[2], temp.split("-")[3]);
						break;
					case 4 :
						if(articleList_4.search(temp)) JOptionPane.showMessageDialog(null, "This article is already in the article list to which you are trying to add it.", "Error", JOptionPane.ERROR_MESSAGE);
						else articleList_4.insertEnd(temp.split("-")[0], temp.split("-")[1], temp.split("-")[2], temp.split("-")[3]);
						break;
					case 5 :
						if(articleList_5.search(temp)) JOptionPane.showMessageDialog(null, "This article is already in the article list to which you are trying to add it.", "Error", JOptionPane.ERROR_MESSAGE);
						else articleList_5.insertEnd(temp.split("-")[0], temp.split("-")[1], temp.split("-")[2], temp.split("-")[3]);
						break;
					case 6 :
						if(articleList_6.search(temp)) JOptionPane.showMessageDialog(null, "This article is already in the article list to which you are trying to add it.", "Error", JOptionPane.ERROR_MESSAGE);
						else articleList_6.insertEnd(temp.split("-")[0], temp.split("-")[1], temp.split("-")[2], temp.split("-")[3]);
						break;
					case 7 :
						if(articleList_7.search(temp)) JOptionPane.showMessageDialog(null, "This article is already in the article list to which you are trying to add it.", "Error", JOptionPane.ERROR_MESSAGE);
						else articleList_7.insertEnd(temp.split("-")[0], temp.split("-")[1], temp.split("-")[2], temp.split("-")[3]);
						break;
					case 8 :
						if(articleList_8.search(temp)) JOptionPane.showMessageDialog(null, "This article is already in the article list to which you are trying to add it.", "Error", JOptionPane.ERROR_MESSAGE);
						else articleList_8.insertEnd(temp.split("-")[0], temp.split("-")[1], temp.split("-")[2], temp.split("-")[3]);
						break;
					case 9 :
						if(articleList_9.search(temp)) JOptionPane.showMessageDialog(null, "This article is already in the article list to which you are trying to add it.", "Error", JOptionPane.ERROR_MESSAGE);
						else articleList_9.insertEnd(temp.split("-")[0], temp.split("-")[1], temp.split("-")[2], temp.split("-")[3]);
						break;
					}
				}
			}
		});
		
		btnDeleteSelectedArticleFromArticleList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() == -1)
					JOptionPane.showMessageDialog(null, "Please select an article!", "Error", JOptionPane.ERROR_MESSAGE);
				else {
					articleListArticles.clear();
					switch (indexValue) {
					case 0: //If we are in the first article list (at index 0).
						articleList_0.deleteNode(key); //The selected article is deleted from the article list.
						if (articleList_0.getFirst() != null) {
							Node current = articleList_0.getFirst();
							while (current.getNext() != articleList_0.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles); //This code updates the image of the JList component.
						break;
					case 1:
						articleList_1.deleteNode(key);
						if (articleList_1.getFirst() != null) {
							Node current = articleList_1.getFirst();
							while (current.getNext() != articleList_1.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					case 2:
						articleList_2.deleteNode(key);
						if (articleList_2.getFirst() != null) {
							Node current = articleList_2.getFirst();
							while (current.getNext() != articleList_2.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					case 3:
						articleList_3.deleteNode(key);
						if (articleList_3.getFirst() != null) {
							Node current = articleList_3.getFirst();
							while (current.getNext() != articleList_3.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					case 4:
						articleList_4.deleteNode(key);
						if (articleList_4.getFirst() != null) {
							Node current = articleList_4.getFirst();
							while (current.getNext() != articleList_4.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					case 5:
						articleList_5.deleteNode(key);
						if (articleList_5.getFirst() != null) {
							Node current = articleList_5.getFirst();
							while (current.getNext() != articleList_5.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					case 6:
						articleList_6.deleteNode(key);
						if (articleList_6.getFirst() != null) {
							Node current = articleList_6.getFirst();
							while (current.getNext() != articleList_6.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					case 7:
						articleList_7.deleteNode(key);
						if (articleList_7.getFirst() != null) {
							Node current = articleList_7.getFirst();
							while (current.getNext() != articleList_7.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					case 8:
						articleList_8.deleteNode(key);
						if (articleList_8.getFirst() != null) {
							Node current = articleList_8.getFirst();
							while (current.getNext() != articleList_8.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					case 9:
						articleList_9.deleteNode(key);
						if (articleList_9.getFirst() != null) {
							Node current = articleList_9.getFirst();
							while (current.getNext() != articleList_9.getFirst()) {
								articleListArticles.addElement(current.toString());
								current = current.getNext();
							}
							articleListArticles.addElement(current.toString());
						}
						list.setModel(articleListArticles);
						break;
					}
				}
			}
		});
		
		list.addListSelectionListener(new ListSelectionListener() { //This listener captures all actions in JList.
            public void valueChanged(ListSelectionEvent e) {
                if (list.getSelectedIndex() != -1) {
                    key = list.getSelectedValue().split("-")[0];
                }
            }
        });
	}

	public CircularDoublyLinkedList[] getArticleLists() {
		return articleLists;
	}

	public void setArticleLists(CircularDoublyLinkedList[] articleLists) {
		this.articleLists = articleLists;
	}

	public DefaultListModel<String> getArticleListsModel() {
		return articleListsModel;
	}

	public void setArticleListsModel(DefaultListModel<String> articleListsModel) {
		this.articleListsModel = articleListsModel;
	}

	public JList<String> getList() {
		return list;
	}

	public void setList(JList<String> list) {
		this.list = list;
	}
	
	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}
}