package ArticleSurfer;

import static ArticleSurfer.ArticleListsFrame.btnReturn;
import static ArticleSurfer.ArticleListsFrame.btnDisplaySelectedArticleList;
import static ArticleSurfer.ArticleListsFrame.btnClearAllContent;
import static ArticleSurfer.ArticleListsFrame.btnAddSelectedArticleToArticleList;
import static ArticleSurfer.ArticleListsFrame.btnDeleteSelectedArticleFromArticleList;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Random;
import java.util.Stack;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L; //Default Serial Version ID

	//Article Library
	private BinarySearchTree articleLibrary = new BinarySearchTree();

	private TreeNode article1 = new TreeNode("Power of Habit", "Charles Duhigg", "Psychology", "2012");
	private TreeNode article2 = new TreeNode("Tipping Point: How Little Things Can Make a Big Difference", "Malcolm Gladwell", "Sociology", "2000");
	private TreeNode article3 = new TreeNode("Long Tail", "Chris Anderson", "Economics", "2004");
	private TreeNode article4 = new TreeNode("Power of Introverts", "Susan Cain", "Psychology", "2012");
	private TreeNode article5 = new TreeNode("Four Hour Workweek", "Timothy Ferriss", "Business", "2007");
	private TreeNode article6 = new TreeNode("Immortal Life of Henrietta Lacks", "Rebecca Skloot", "Science", "2010");
	private TreeNode article7 = new TreeNode("Origin of Species", "Charles Darwin", "Science", "1859");
	private TreeNode article8 = new TreeNode("Feminine Mystique", "Betty Friedan", "Sociology", "1963");
	private TreeNode article9 = new TreeNode("Wealth of Nations", "Adam Smith", "Economics", "1776");
	private TreeNode article10 = new TreeNode("A Brief History of Time", "Stephen Hawking", "Science", "1988");
	private TreeNode article11 = new TreeNode("Declaration of Independence", "Thomas Jefferson et al.", "Politics", "1776");
	private TreeNode article12 = new TreeNode("Interpretation of Dreams", "Sigmund Freud", "Psychology", "1899");
	private TreeNode article13 = new TreeNode("Catcher in the Rye", "J.D. Salinger", "Fiction", "1951");
	private TreeNode article14 = new TreeNode("Scarlet Letter", "Nathaniel Hawthorne", "Fiction", "1850");
	private TreeNode article15 = new TreeNode("Adventures of Huckleberry Finn", "Mark Twain", "Fiction", "1884");
	private TreeNode article16 = new TreeNode("Pride and Prejudice", "Jane Austen", "Fiction", "1813");
	private TreeNode article17 = new TreeNode("Frankenstein", "Mary Shelley", "Fiction", "1818");
	private TreeNode article18 = new TreeNode("Collapse of the Roman Empire", "Peter Heather", "History", "2005");
	private TreeNode article19 = new TreeNode("French Revolution: Causes and Consequences", "R.R. Palmer", "History", "1969");
	private TreeNode article20 = new TreeNode("Civil Rights Movement in the United States", "Steven F. Lawson", "History", "1995");
	private TreeNode article21 = new TreeNode("Rise and Fall of Ancient Egypt", "Toby Wilkinson", "History", "2010");
	private TreeNode article22 = new TreeNode("Cold War: A Global Perspective", "Odd Arne Westad", "History", "2005");
	private TreeNode article23 = new TreeNode("Romeo and Juliet", "Jane Austen", "Romantic", "1811");
	private TreeNode article24 = new TreeNode("Hunchback of Notre Dame", "Victor Hugo", "Romantic", "1831");
	private TreeNode article25 = new TreeNode("Pride and Prejudice", " Jane Austen", "Romantic", "1813");

	//Panels
	private JPanel contentPane = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
	private final JPanel panel_3 = new JPanel();
	
	//Scroll Pane
	private DefaultListModel<String> searchedArticles = new DefaultListModel<String>();
	private JList<String> list = new JList<String>(searchedArticles);
	private JScrollPane scrollPane = new JScrollPane(list);
	
	//Labels
	private JLabel lblLogo = new JLabel(new ImageIcon(this.getClass().getResource("Logo.png")));
	private JLabel lblArticleSearch = new JLabel("Article Search:");
	private JLabel lblArticleName = new JLabel("Article Name:");
	private JLabel lblAuthorName = new JLabel("Author Name:");
	private JLabel lblType = new JLabel("Type:");
	private JLabel lblYear = new JLabel("Year:");
	private final JLabel lblCreateArticleList = new JLabel("Create Article List (Maximum 10):");
	private final JLabel lblArticleListName = new JLabel("Article List Name:");
	private JLabel lblSearchResults = new JLabel("Search Results:");
	
	//Text Fields
	private JTextField textFieldArticleName = new JTextField();
	private JTextField textFieldAuthorName = new JTextField();
	private JTextField textFieldType = new JTextField();
	private JTextField textFieldYear = new JTextField();
	private JTextField textFieldArticleListName = new JTextField();
	
	//Buttons
	private JButton btnSearch = new JButton("Search");
	protected static JButton btnCreateArticleList = new JButton("Create Article List");
	private JButton btnDisplayAllArticleLists = new JButton("Display All Article Lists");
	private JButton btnDisplayAllArticles = new JButton("Display All Articles");
	private JButton btnMix = new JButton("Mix");
	private JButton btnSort = new JButton("Sort");

	//Radio Buttons
	private ButtonGroup searchFilters = new ButtonGroup();
	private JRadioButton rdbtnArticleName = new JRadioButton("Article Name");
	private JRadioButton rdbtnAuthorName = new JRadioButton("Author Name");
	private JRadioButton rdbtnType = new JRadioButton("Type");
	private JRadioButton rdbtnYear = new JRadioButton("Year");

	//ComboBox
	private String[] sortingAttributes = {"Sort By", "Article Name", "Author Name", "Type", "Year"};
	DefaultComboBoxModel<String> sortByModel = new DefaultComboBoxModel<String>(sortingAttributes);
	private JComboBox<String> sortBy = new JComboBox<String>(sortByModel);
	
	//Instance of "ArticleListsFrame"
	private ArticleListsFrame articleListsFrame = new ArticleListsFrame();

	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setResizable(false); //It makes "MainFrame" not resizable.
		mainFrame.setLocation(175, 15); //This code sets this frame's opening location at the specified coordinates (175, 15).
		mainFrame.setVisible(true); //This code opens "MainFrame".
	}

	public MainFrame() {
		setGUI(); //This function makes GUI-related arrangements.
		createDatabase(); //This function inserts tree nodes containing articles' data to the BST named "articleLibrary".
		setActionListener(); //This function captures an event on the buttons and radio buttons assigned to the frame and performs an appropriate action.
	}
	
	public void setGUI() {
		
		//*****Set "MainFrame" Interface*****
		setTitle("Article Surfer");
		setIconImage(new ImageIcon(this.getClass().getResource("ApplicationIcon.jpeg")).getImage()); //This code adds icon to the frame.
		setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		UIManager.put("Label.disabledForeground", new Color((int) UIManager.getColor("Label.disabledForeground").getRed() + 85, UIManager.getColor("Label.disabledForeground").getGreen(),UIManager.getColor("Label.disabledForeground").getBlue())); //This code makes the color of disabled labels pale red.
		UIManager.put("Button.disabledText", new Color((int) UIManager.getColor("Button.disabledText").getRed() - 5, (int) UIManager.getColor("Button.disabledText").getGreen() - 5, (int) UIManager.getColor("Button.disabledText").getBlue() - 5)); //This code makes the color of disabled buttons' texts saturated.
		setBounds(0, 0, 510, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPane);

		//*****Set "MainFrame's Components" Interface*****

		//Set "contentPane"
		contentPane.setBackground(new Color(255, 247, 243));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(lblLogo); //Logo
		contentPane.add(panel_1); //"Article Search" Interface
		contentPane.add(panel_2); //"Scroll Pane" Interface
		contentPane.add(panel_3); //"Create Article List" Interface

		//Set "panel_1" **"Article Search" Interface**
		panel_1.setOpaque(false); //This code makes the background of panel_1 transparent.
		panel_1.setLayout(null); //This code allows the positions and dimensions of the panel's components to be determined directly.
		panel_1.setBounds(0, 11, 510, 193);
		panel_1.add(lblArticleSearch);
		panel_1.add(lblArticleName);
		panel_1.add(lblAuthorName);
		panel_1.add(lblType);
		panel_1.add(lblYear);
		panel_1.add(textFieldArticleName);
		panel_1.add(textFieldAuthorName);
		panel_1.add(textFieldType);
		panel_1.add(textFieldYear);
		panel_1.add(btnSearch);
		panel_1.add(rdbtnArticleName);
		panel_1.add(rdbtnAuthorName);
		panel_1.add(rdbtnType);
		panel_1.add(rdbtnYear);
		
		//Set "panel_2" **"Scroll Pane" Interface**
		panel_2.setOpaque(false); //This code makes the background of panel_2 transparent.
		panel_2.setLayout(null); //This code allows the positions and dimensions of the panel's components to be determined directly.
		panel_2.setBounds(0, 215, 510, 330);
		panel_2.add(scrollPane);
		panel_2.add(btnDisplayAllArticles);
		panel_2.add(btnMix);
		panel_2.add(btnSort);
		panel_2.add(sortBy);
		
		//Set "panel_3" **"Create Article List" Interface**
		panel_3.setOpaque(false); //This code makes the background of panel_3 transparent.
		panel_3.setLayout(null); //This code allows the positions and dimensions of the panel's components to be determined directly.
		panel_3.setBounds(0, 545, 510, 97);
		panel_3.add(lblCreateArticleList);
		panel_3.add(lblArticleListName);
		panel_3.add(textFieldArticleListName);
		panel_3.add(btnCreateArticleList);
		panel_3.add(btnDisplayAllArticleLists);

		//Set **Labels' Interface**
		
		//Set "lblLogo"
		lblLogo.setBounds(382, 0, 108, 50);

		//Set "lblArticleSearch"
		lblArticleSearch.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblArticleSearch.setForeground(new Color(0, 115, 0));
		lblArticleSearch.setBounds(28, 11, 90, 24);

		//Set "lblArticleName"
		lblArticleName.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblArticleName.setForeground(new Color(255, 0, 0));
		lblArticleName.setBounds(28, 58, 88, 24);
		lblArticleName.setEnabled(false); //Initial setting.

		//Set "lblAuthorName"
		lblAuthorName.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblAuthorName.setForeground(new Color(255, 0, 0));
		lblAuthorName.setBounds(28, 114, 97, 24);
		lblAuthorName.setEnabled(false); //Initial setting.

		//Set "lblType"
		lblType.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblType.setForeground(new Color(255, 0, 0));
		lblType.setBounds(310, 58, 50, 25);
		lblType.setEnabled(false); //Initial setting.
		
		//Set "lblYear"
		lblYear.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblYear.setForeground(new Color(255, 0, 0));
		lblYear.setBounds(310, 114, 50, 25);
		lblYear.setEnabled(false); //Initial setting.

		//Set "lblCreateArticleList"
		lblCreateArticleList.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblCreateArticleList.setForeground(new Color(0, 0, 115));
		lblCreateArticleList.setBounds(150, 0, 209, 26);

		//Set "lblArticleListName"
		lblArticleListName.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblArticleListName.setForeground(new Color(115, 0, 115));
		lblArticleListName.setBounds(54, 30, 111, 24);

		//Set **Text Fields' Interface**

		//Set "textFieldArticleName"
		textFieldArticleName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		textFieldArticleName.setBounds(125, 62, 160, 21);
		textFieldArticleName.setColumns(10);
		textFieldArticleName.setEnabled(false); //Initial setting.

		//Set "textFieldAuthorName"
		textFieldAuthorName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		textFieldAuthorName.setBounds(125, 119, 160, 21);
		textFieldAuthorName.setColumns(10);
		textFieldAuthorName.setEnabled(false); //Initial setting.

		//Set "textFieldType"
		textFieldType.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		textFieldType.setBounds(350, 62, 120, 21);
		textFieldType.setColumns(10);
		textFieldType.setEnabled(false); //Initial setting.
		
		//Set "textFieldYear"
		textFieldYear.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		textFieldYear.setBounds(350, 119, 120, 21);
		textFieldYear.setColumns(10);
		textFieldYear.setEnabled(false); //Initial setting.

		//Set "textFieldArticleListName"
		textFieldArticleListName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		textFieldArticleListName.setBounds(180, 33, 264, 21);
		textFieldArticleListName.setColumns(10);

		//Set **Buttons' Interface**

		//Set "btnSearch"
		btnSearch.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btnSearch.setFocusPainted(false); //This code removes border of buttons when clicking on it.
		btnSearch.setBounds(200, 155, 100, 38);
		btnSearch.setEnabled(false); //This is the initial value for btnSearch. This will be activated after clicking any of the radio buttons (Article name, author name, type, or year).
		btnSearch.setBackground(new Color(51, 255, 255));

		//Set "btnCreateArticleList"
		btnCreateArticleList.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btnCreateArticleList.setFocusPainted(false); //This code removes border of buttons when clicking on it.
		btnCreateArticleList.setBounds(54, 65, 167, 32);
		btnCreateArticleList.setBackground(new Color(51, 255, 255));

		//Set "btnDisplayAllArticleLists"
		btnDisplayAllArticleLists.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btnDisplayAllArticleLists.setFocusPainted(false); //This code removes border of buttons when clicking on it.
		btnDisplayAllArticleLists.setBounds(248, 65, 195, 32);
		btnDisplayAllArticleLists.setBackground(new Color(51, 255, 255));

		//Set "btnDisplayAllArticles"
		btnDisplayAllArticles.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btnDisplayAllArticles.setFocusPainted(false); //This code removes border of buttons when clicking on it.
		btnDisplayAllArticles.setBounds(93, 250, 164, 32);
		btnDisplayAllArticles.setBackground(new Color(51, 255, 255));

		//Set "btnMix"
		btnMix.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btnMix.setFocusPainted(false); //This code removes border of buttons when clicking on it.
		btnMix.setBounds(129, 290, 92, 30);
		btnMix.setBackground(new Color(51, 255, 255));

		//Set "btnSort"
		btnSort.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btnSort.setFocusPainted(false); //This code removes border of buttons when clicking on it.
		btnSort.setBounds(308, 290, 77, 30);
		btnSort.setBackground(new Color(51, 255, 255));

		//Set **Radio Buttons' Interface**

		//Set "rdbtnArticleName"
		rdbtnArticleName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		rdbtnArticleName.setFocusPainted(false); //This code removes border of radio buttons when clicking on it.
		rdbtnArticleName.setOpaque(false);
		rdbtnArticleName.setBounds(145, 40, 95, 23);
		searchFilters.add(rdbtnArticleName); //Add "rdbtnArticleName" to the ButtonGroup named "searchFilters".

		//Set "rdbtnAuthorName"
		rdbtnAuthorName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		rdbtnAuthorName.setFocusPainted(false); //This code removes border of radio buttons when clicking on it.
		rdbtnAuthorName.setOpaque(false);
		rdbtnAuthorName.setBounds(145, 95, 91, 23);
		searchFilters.add(rdbtnAuthorName); //Add "rdbtnAuthorName" to the ButtonGroup named "searchFilters".

		//Set "rdbtnType"
		rdbtnType.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		rdbtnType.setFocusPainted(false); //This code removes border of radio buttons when clicking on it.
		rdbtnType.setOpaque(false);
		rdbtnType.setBounds(380, 40, 55, 23);
		searchFilters.add(rdbtnType); //Add "rdbtnType" to the ButtonGroup named "searchFilters".
		
		//Set "rdbtnYear"
		rdbtnYear.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		rdbtnYear.setFocusPainted(false); //This code removes border of radio buttons when clicking on it.
		rdbtnYear.setOpaque(false);
		rdbtnYear.setBounds(380, 95, 55, 23);
		searchFilters.add(rdbtnYear); //Add "rdbtnYear" to the ButtonGroup named "searchFilters".

		//Set **ComboBox's Interface**

		//Set "sortBy"
		sortBy.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		sortBy.setBackground(new Color(51, 255, 255));
		sortBy.setBounds(290, 250, 113, 30);
		((JLabel) sortBy.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER); //This code horizontally aligns what is written in the combobox named "sortBy", that is, brings it to the middle.
		
		//Set **Scroll Pane's Interface**
		
		//Set "scrollPane"
		scrollPane.setBounds(20, 0, 450, 240);
		scrollPane.setColumnHeaderView(lblSearchResults);
		scrollPane.setViewportView(list);
		lblSearchResults.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14)); //"lblSearchResults" settings.
		lblSearchResults.setForeground(new Color(255, 153, 51)); //"lblSearchResults" settings.
		lblSearchResults.setBackground(new Color(224, 224, 224)); //"lblSearchResults" settings.
		lblSearchResults.setHorizontalAlignment(SwingConstants.CENTER); //"lblSearchResults" settings.
		list.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14)); //"list" settings.
		list.setBackground(new Color(211, 207, 236)); //"list" settings.
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //"list" settings.
	}
	
	public void createDatabase() {
		articleLibrary.insert(article1);
		articleLibrary.insert(article2);
		articleLibrary.insert(article3);
		articleLibrary.insert(article4);
		articleLibrary.insert(article5);
		articleLibrary.insert(article6);
		articleLibrary.insert(article7);
		articleLibrary.insert(article8);
		articleLibrary.insert(article9);
		articleLibrary.insert(article10);
		articleLibrary.insert(article11);
		articleLibrary.insert(article12);
		articleLibrary.insert(article13);
		articleLibrary.insert(article14);
		articleLibrary.insert(article15);
		articleLibrary.insert(article16);
		articleLibrary.insert(article17);
		articleLibrary.insert(article18);
		articleLibrary.insert(article19);
		articleLibrary.insert(article20);
		articleLibrary.insert(article21);
		articleLibrary.insert(article22);
		articleLibrary.insert(article23);
		articleLibrary.insert(article24);
		articleLibrary.insert(article25);
	}

	public void setActionListener() {
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldArticleName.isEnabled() == true) { //If the radio button named "Article Name" is pressed (rdbtnArticleName).
					if (textFieldArticleName.getText().equals("")) { //If "textFieldArticleName" is empty.
						searchedArticles.clear(); //Clear "searchedArticles" list.
						JOptionPane.showMessageDialog(null, "Please do not leave \"Article Name\" text field blank!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else { //If "textFieldArticleName" is not empty.
						searchedArticles.clear(); //Article data may have been added to the "searchedArticles" list previously using the application's "Article Search" functionality. However, since we will now be using this functionality once again, we must first clear its contents completely. If we do not do this, we still see the results of our previous search in the panel, even though we perform a new search.
						String writtenText = textFieldArticleName.getText(); //The string variable named "writtenText" keeps the text written in the text field of article name.
						/*
					  	The following code do "in order traversal" in our BST named articleLibrary.
					  	Through this code, we access all tree nodes in the articleLibrary one by one.
					  	These codes are taken from the 27th page of the pdf named "BST.pdf" in the resources section of our theoretical course.
					  	Just "System.out.printf("%s ", node.getElement());" code part has been changed, an if statement has been placed instead of this line.
					  	The tree node named "node" points to one of the articles in the BST named "articleLibrary".
					  	This if statement, takes the "article name" of the article pointed by the "node" and converts it to lowercase.
					  	It also takes the content of the string variable named "writtenText" and converts it to lowercase.
					  	After then, this if statement compares these two contents with each other using a regular expression pattern that accepts lowercase letters, spaces, periods, and colons as many times as necessary including zero (*).
					    If there is a match, the corresponding article is added to the "searchedArticles" list.
					  	The while loop continues until the tree node named "node" points to all the nodes (articles) in the BST named "articleLibrary" respectively. 
					  	If there is a match, if statement updates the "searchedArticles" list, and the "searchedArticles" list takes its final form at the end of the loop.
					 	*/
					Stack<TreeNode> nodes = new Stack<>();
					TreeNode current = articleLibrary.getRoot();
					while (!nodes.isEmpty() || current != null) {
						if (current != null) {
							nodes.push(current);
							current = current.getLeft();
						} else {
							TreeNode node = (TreeNode) nodes.pop();
							if (convertToString(node).split("-")[0].toLowerCase(Locale.US)
									.matches(writtenText.toLowerCase(Locale.US) + "[a-z\\s.:]*")) {
								searchedArticles.addElement(convertToString(node));
							}
							current = node.getRight();
						}
					}
					if(searchedArticles.getSize() == 0) JOptionPane.showMessageDialog(null, "There is no match!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
				else if (textFieldAuthorName.isEnabled() == true) { //If the radio button named "Author Name" is pressed (rdbtnAuthorName).
					if (textFieldAuthorName.getText().equals("")) {
						searchedArticles.clear();
						JOptionPane.showMessageDialog(null, "Please do not leave \"Author Name\" text field blank!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
					searchedArticles.clear();
					String writtenText = textFieldAuthorName.getText();
					Stack<TreeNode> nodes = new Stack<>();
					TreeNode current = articleLibrary.getRoot();
					while (!nodes.isEmpty() || current != null) {
						if (current != null) {
							nodes.push(current);
							current = current.getLeft();
						} else {
							TreeNode node = (TreeNode) nodes.pop();
							if (convertToString(node).split("-")[1].toLowerCase(Locale.US)
									.matches(writtenText.toLowerCase(Locale.US) + "[a-z\\s.:]*")) {
								searchedArticles.addElement(convertToString(node));
							}
							current = node.getRight();
						}
					 }
					if(searchedArticles.getSize() == 0) JOptionPane.showMessageDialog(null, "There is no match!", "Error", JOptionPane.ERROR_MESSAGE);
				  }
				} else if (textFieldType.isEnabled() == true) { //If the radio button named "Type" is pressed (rdbtnType).
					if (textFieldType.getText().equals("")) {
						searchedArticles.clear();
						JOptionPane.showMessageDialog(null, "Please do not leave \"Type\" text field blank!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
					searchedArticles.clear();
					String writtenText = textFieldType.getText();
					Stack<TreeNode> nodes = new Stack<>();
					TreeNode current = articleLibrary.getRoot();
					while (!nodes.isEmpty() || current != null) {
						if (current != null) {
							nodes.push(current);
							current = current.getLeft();
						} else {
							TreeNode node = (TreeNode) nodes.pop();
							if (convertToString(node).split("-")[2].toLowerCase(Locale.US)
									.matches(writtenText.toLowerCase(Locale.US) + "[a-z\\s.:]*")) {
								searchedArticles.addElement(convertToString(node));
							}
							current = node.getRight();
						}
					}
					if(searchedArticles.getSize() == 0) JOptionPane.showMessageDialog(null, "There is no match!", "Error", JOptionPane.ERROR_MESSAGE);
				 }
				} else if (textFieldYear.isEnabled() == true) { //If the radio button named "Year" is pressed (rdbtnYear).
					if (textFieldYear.getText().equals("")) {
						searchedArticles.clear();
						JOptionPane.showMessageDialog(null, "Please do not leave \"Year\" text field blank!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						searchedArticles.clear();
						String writtenText = textFieldYear.getText();
						Stack<TreeNode> nodes = new Stack<>();
						TreeNode current = articleLibrary.getRoot();
						while (!nodes.isEmpty() || current != null) {
							if (current != null) {
								nodes.push(current);
								current = current.getLeft();
							} else {
								TreeNode node = (TreeNode) nodes.pop();
								if (convertToString(node).split("-")[3].matches(writtenText + "[0-9]*")) { //In this code, the regular expression pattern only accepts numbers because if any character other than a number is written in the text field named "textFieldYear", a match is not possible.
									searchedArticles.addElement(convertToString(node));
								}
								current = node.getRight();
							}
						}
						if(searchedArticles.getSize() == 0) JOptionPane.showMessageDialog(null, "There is no match!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnCreateArticleList.addActionListener(new ActionListener() {
			int i;
			public void actionPerformed(ActionEvent e) {
				if (textFieldArticleListName.getText().equals("")) { //If there is nothing in the text field of article list name, do nothing.
					JOptionPane.showMessageDialog(null, "Please do not leave \"Article List Name\" text field blank!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					if(sameArticleListNameFound()) JOptionPane.showMessageDialog(null, "Article list name written in \"Article List Name\" text field should not been given to another article list.", "Error", JOptionPane.ERROR_MESSAGE);
					else { //If the article list name written in the textfield named "textFieldArticleListName" has not been given to another article list before.
					for (i = 0; i < 10; i++)
						if (articleListsFrame.getArticleLists()[i].getName() == null)
							break;
					/* 
					 Suppose we already have 2 article lists created.
					 The articles held on the new article list that we will create should be kept in the list named "articleList_2" and found in the 3rd index of the array named "articleLists".
					 We use the for loop to determine this index.
					 For i=0, articleListsFrame.getArticleLists()[0].getName() command does not return null.
					 Same for i=1. Because an article list is kept at i=0 and i=1 indexes. 
					 However, this command returns null in the i=2 index. So, our index should be 2.
					 This is how we determine the index.
					 * */
					articleListsFrame.getArticleLists()[i].setName(textFieldArticleListName.getText()); //The text value written in the text field which is in front of the label named "Article List Name" is assigned to "name" of the array list kept in the i'th index of the array named "articleLists".
					articleListsFrame.getArticleListsModel().addElement(articleListsFrame.getArticleLists()[i].getName()); //The value assigned to the "name" of the array list held in the i'th index of the array named "articleLists" is added as an element to the list named "articleListsModel", which holds the names of the article lists.
					if (articleListsFrame.getArticleListsModel().getSize() == 10)
						btnCreateArticleList.setEnabled(false);
					/* 
					  If the number of elements in the "articleListsModel" list reaches 10,
					  In other words, if the number of created article lists reaches 10, 
					  This code disables the "Create Article List" button to prevent the creation of more article lists.
					  (Because the maximum number of article lists that can be created is 10, so exceeding this limit should be prevented.)
					*/
				}
			  }
			}
		});
		
		btnDisplayAllArticleLists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				articleListsFrame.getList().setModel(articleListsFrame.getArticleListsModel());
				/* 
				 This reset command updates the image of the JList component.
				 Thanks to this command, image of the JList component starts to display the contents of the "articleListsModel" list, which holds the name of the article lists.
				 This means that every time we click on the "Display All Article Lists" button, we always see the ArticleListsFrame's main screen where users interact.
				 If we do not add this reset command to our program's code, what happens?
				 Assume that we enter any article list by clicking its name and then "Display Article List" button.
				 This reaction will update the image of JList component. When we're done there, we close ArticleListsFrame.
				 Then we open the ArtlicleListFrame again to see article lists using "Display All Article Lists" button.
				 Even if we close ArtlicleListFrame and open it again, we never be able to leave that article list we entered before since we did not write the reset command here.
				 The only way to get out of that article list is to restart the program.
				 That's why this reset command is important.
				 */
				articleListsFrame.setResizable(false);
				articleListsFrame.setLocation(725, 115); //This code opens "ArticleListsFrame" at the specified coordinates (725, 115).
				articleListsFrame.setVisible(true); //Open "ArticleListsFrame".
				btnReturn.setEnabled(false); //This code disables the return button.
				btnDisplaySelectedArticleList.setVisible(true); //This code makes "Display Selected Article List" button visible.
				btnClearAllContent.setVisible(true); //This code makes "Clear All Content" button visible.
				btnAddSelectedArticleToArticleList.setVisible(true); //This code makes "Add Selected Article To Article List" button visible.
				btnDeleteSelectedArticleFromArticleList.setVisible(false); //This code makes "Delete Selected Article From Article List" button invisible.
			}
		});
		
		btnDisplayAllArticles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchedArticles.clear(); //Article data may have been added to the "searchedArticles" list previously using the application's "Article Search" functionality. However, since we will now be using this list to display all the articles, we must first clear its contents completely.
				//Binary search tree in order traversal.
				Stack<TreeNode> nodes = new Stack<>();
				TreeNode current = articleLibrary.getRoot();
				while (!nodes.isEmpty() || current != null) {
					if (current != null) {
						nodes.push(current);
						current = current.getLeft();
					}
					else {
						TreeNode node = (TreeNode) nodes.pop();
						searchedArticles.addElement(convertToString(node)); //We add all the articles which is in our database to "searchedArticles" list one by one in string format.
						current = node.getRight();
					}
				}
			}
		});
		
		btnMix.addActionListener(new ActionListener() { //When the "Mix" button is clicked, we swap the article data represented by the index value, which is randomly generated, with the article data represented by i'th index value in "searchedArticles" list.
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				for (int i = 0; i < searchedArticles.size(); i++) {
					int randomIndex = rand.nextInt(searchedArticles.size());
					String temp = (String) searchedArticles.getElementAt(randomIndex);
					searchedArticles.setElementAt(searchedArticles.getElementAt(i), randomIndex);
					searchedArticles.setElementAt(temp, i);
				}
			}
		});
		
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //When the "Sort" button is clicked, elements in the "searchedArticles" list are sorted according to the selected item among the attributes (article name, author name, type, or year) contained in the ComboBox named "sortBy".
				if (sortBy.getSelectedItem() == "Article Name") {
					if (searchedArticles.size() == 0 || searchedArticles.size() == 1)
						return;
					String[] temp = Arrays.copyOf(searchedArticles.toArray(), searchedArticles.toArray().length,
							String[].class);
					Arrays.sort(temp, new Comparator<String>() {
						public int compare(String o1, String o2) {
							return o1.split("-")[0].compareTo(o2.split("-")[0]);
						}
					});
					searchedArticles.clear();
					for (int i = 0; i < temp.length; i++) {
						searchedArticles.addElement(temp[i]);
					}
				} else if (sortBy.getSelectedItem() == "Author Name") {
					if (searchedArticles.size() == 0 || searchedArticles.size() == 1)
						return;
					String[] temp = Arrays.copyOf(searchedArticles.toArray(), searchedArticles.toArray().length,
							String[].class);
					Arrays.sort(temp, new Comparator<String>() {
						public int compare(String o1, String o2) {
							return o1.split("-")[1].compareTo(o2.split("-")[1]);
						}
					});
					searchedArticles.clear();
					for (int i = 0; i < temp.length; i++) {
						searchedArticles.addElement(temp[i]);
					}
				} else if (sortBy.getSelectedItem() == "Type") {
					if (searchedArticles.size() == 0 || searchedArticles.size() == 1)
						return;
					String[] temp = Arrays.copyOf(searchedArticles.toArray(), searchedArticles.toArray().length,
							String[].class);
					Arrays.sort(temp, new Comparator<String>() {
						public int compare(String o1, String o2) {
							return o1.split("-")[2].compareTo(o2.split("-")[2]);
						}
					});
					searchedArticles.clear();
					for (int i = 0; i < temp.length; i++) {
						searchedArticles.addElement(temp[i]);
					}
				} else if (sortBy.getSelectedItem() == "Year") {
					if (searchedArticles.size() == 0 || searchedArticles.size() == 1)
						return;
					String[] temp = Arrays.copyOf(searchedArticles.toArray(), searchedArticles.toArray().length,
							String[].class);
					Arrays.sort(temp, new Comparator<String>() {
						public int compare(String o1, String o2) {
							return o1.split("-")[3].compareTo(o2.split("-")[3]);
						}
					});
					searchedArticles.clear();
					for (int i = 0; i < temp.length; i++) {
						searchedArticles.addElement(temp[i]);
					}
				}
			}
		});
		
		rdbtnArticleName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //There are 4 basic radio buttons to use "Article Search" functionality. By clicking any of these radio buttons, we can search an article by the name of the article/author, type of article, or year of article. When we run the program for the first time, since none of these radio buttons are selected, all labels, textFields and "Search" button of "Article Search" functionality are disabled. By clicking any radio button, we can enable "Search" button and the relevant label, textField. These codes activate the relevant places according to the clicked radio button.
				resetAllSettings(); //Return to initial label settings and initial "textField" settings.
				textFieldArticleName.setEnabled(true);
				lblArticleName.setEnabled(true);
				btnSearch.setEnabled(true);
			}
		});
		
		rdbtnAuthorName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetAllSettings();
				textFieldAuthorName.setEnabled(true);
				lblAuthorName.setEnabled(true);
				btnSearch.setEnabled(true);
			}
		});
		
		rdbtnType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetAllSettings();
				textFieldType.setEnabled(true);
				lblType.setEnabled(true);
				btnSearch.setEnabled(true);
			}
		});
		
		rdbtnYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetAllSettings();
				textFieldYear.setEnabled(true);
				lblYear.setEnabled(true);
				btnSearch.setEnabled(true);
			}
		});
		
		list.addListSelectionListener(new ListSelectionListener() { //This listener captures all actions in JList.
            public void valueChanged(ListSelectionEvent e) {
                if (list.getSelectedIndex() != -1) {
                    articleListsFrame.setTemp(list.getSelectedValue());
                } else {
                	articleListsFrame.setTemp(null);
                }
            }
        });
	}
	
	public boolean sameArticleListNameFound() { //This function checks whether the article name written in the textField named "Article List Name" has been assigned to an article list before.
		for(int i = 0; i < articleListsFrame.getArticleListsModel().size(); i++) {
			if(articleListsFrame.getArticleListsModel().get(i).equals(textFieldArticleListName.getText()))
				return true;
		}
		return false;
	}

	public void resetAllSettings() {
		
		//Return to initial label settings.
		lblArticleName.setEnabled(false);
		lblAuthorName.setEnabled(false);
		lblType.setEnabled(false);
		lblYear.setEnabled(false);
		
		//Return to initial "textField" settings.
		textFieldArticleName.setText("");
		textFieldArticleName.setEnabled(false);
		textFieldAuthorName.setText("");
		textFieldAuthorName.setEnabled(false);
		textFieldType.setText("");
		textFieldType.setEnabled(false);
		textFieldYear.setText("");
		textFieldYear.setEnabled(false);
	}
	
	public String convertToString(TreeNode treeNode) {
		return treeNode.getName() + "-" + treeNode.getAuthor() + "-" + treeNode.getType() + "-" + treeNode.getYear();
	}
}