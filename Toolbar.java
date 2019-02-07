
//@author Halil Ibryam B6033541
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class Toolbar extends JToolBar implements ActionListener {
	// Buttons, text field and label to iterate in the browser
	private JButton backButton;
	private JButton forwardButton;
	private JButton homeButton;
	private JButton refreshButton;
	private JTextField textField;
	private JButton searchButton;
	private JLabel label;
	// private final Toolbar tb = this;
	private JPane pane;
	String home = "http://www.google.com";
	public static int currentIndex = 0;
	public static ArrayList<String> urls = new ArrayList<String>();

	public Toolbar(JPane pane) {
		super(); // call to the constructor of superclass
		Toolbar tB = this;
		// Initializng the Buttons, textField and the label and also adding
		// action listeers to them
		backButton = new JButton("<");
		backButton.addActionListener(this);
		forwardButton = new JButton(">");
		forwardButton.addActionListener(this);
		homeButton = new JButton("Home");
		homeButton.addActionListener(this);
		refreshButton = new JButton("\u21bb");
		refreshButton.addActionListener(this);
		textField = new JTextField(home,50);
		// Adding a KeyListener to the Enter button.
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {  //Adding a keyListener to the EnterButton
					String url = textField.getText();
					try {// Using buffered writer to write the visited pages to
							// history.txt
						BufferedWriter writer = new BufferedWriter(new FileWriter(Browser.history, true));
						writer.write("\n" + url);
						writer.newLine();
						currentIndex++;
						urls.add(url);
						writer.close();
						pane.setPage(url);
						backEnable(tB);
						forwEnable(tB);
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				}
			}

		});

		searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		this.setLayout(new FlowLayout());
		label = new JLabel("URL:");
		// Adding the buttons to the toolbar
		this.add(backButton);
		this.add(forwardButton);
		this.add(homeButton);
		this.add(refreshButton);
		this.add(label);
		this.add(textField);
		this.add(searchButton);
		this.pane = pane;

	}

	@Override // Overriding the actionPerformed method
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == searchButton) {
			String url = textField.getText();

			try {// Using buffered writer to write the visited pages in
					// history.txt when search button is clicked
				BufferedWriter writer = new BufferedWriter(new FileWriter(Browser.history, true));
				writer.write(url);
				writer.newLine();
				writer.close();
				currentIndex++;
				urls.add(url);
				pane.setPage(url);
				backEnable(this);
				forwEnable(this);

			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}

		if (e.getSource() == homeButton) {
			String file_name = "homepage.txt";
			try {// Using buffered and file reader to set the page to the
					// homepage using the link in homepage.txt
				FileReader file = new FileReader(file_name);
				BufferedReader textReader = new BufferedReader(file);
				String homeurl = textReader.readLine();
				this.pane.setPage(homeurl);
				BufferedWriter writer = new BufferedWriter(new FileWriter(Browser.history, true));
				writer.write(homeurl);
				writer.newLine();
				textReader.close();
				currentIndex++;
				urls.add(homeurl);
				textField.setText(homeurl);
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

		if (e.getSource() == refreshButton) {
			String homeurl = textField.getText();
			try { // if and else to check if the url is different to reload the
					// current page and update the textfield
				if (urls.get(currentIndex).equals(homeurl)) {
					this.pane.setPage(homeurl);
				}

				else {
					this.pane.setPage(urls.get(currentIndex));
					textField.setText(urls.get(currentIndex));
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource() == backButton) {
			currentIndex--;  //subtracting 1 from the current index
			String prevURL = urls.get(currentIndex);  // Setting prevURL to be equal to currentIndex(currentIndex--)
			try {//Buffered writer to write the history in history.txt when the forwardButton is clicked
				BufferedWriter writer = new BufferedWriter(new FileWriter(Browser.history, true));
				this.pane.setPage(prevURL);
				textField.setText(prevURL);
				writer.write(getTextField().getText());
				writer.newLine();
				writer.close();
				backEnable(this);
				forwEnable(this);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource() == forwardButton) {
			currentIndex++;  //Adding 1 to the currentIndex
			String nextURL = urls.get(currentIndex);  // Setting nextURL to be equal to currentIndex(currentIndex++)
			// String url = textField.getText();

			try {	//Buffered writer to write the history in history.txt when the forwardButton is clicked
				BufferedWriter writer = new BufferedWriter(new FileWriter(Browser.history, true));
				this.pane.setPage(nextURL);
				textField.setText(nextURL);
				writer.write(getTextField().getText());
				writer.newLine();
				writer.close();
				backEnable(this);
				forwEnable(this);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void backEnable(Toolbar bar) {	 //If and else to check when the button should be enabled and when disabled
		if (currentIndex == 0) {
			bar.getBackButton().setEnabled(false);
		} else {
			bar.getBackButton().setEnabled(true);
		}
	}

	public void forwEnable(Toolbar bar) {	//If and else to check when the button should be enabled and when disabled
		if (currentIndex == urls.size() - 1) {
			bar.getForwardButton().setEnabled(false);
		} else {
			bar.getForwardButton().setEnabled(true);
		}
	}
	// getters and setters for the buttons, textField and the label
	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	public JButton getForwardButton() {
		return forwardButton;
	}

	public void setForwardButton(JButton forwardButton) {
		this.forwardButton = forwardButton;
	}

	public JButton getHomeButton() {
		return homeButton;
	}

	public void setHomeButton(JButton homeButton) {
		this.homeButton = homeButton;
	}

	public JButton getRefreshButton() {
		return refreshButton;
	}

	public void setRefreshButton(JButton refreshButton) {
		this.refreshButton = refreshButton;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

}