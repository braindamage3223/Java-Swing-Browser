
//@author Halil Ibryam B6033541
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.*;

public class Browser extends JFrame {
	
	public static String history = "history.txt"; // Initializing the string with a .txt file

	public Browser() throws IOException {

		super(); // call to the constructor of superclass
		//Setting the size of the JFrame
		this.setLayout(new BorderLayout());
		this.setSize(1200, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) throws IOException {
		String file_name = "homepage.txt"; // Initializing the string with a .txt file
		//Using a file and buffered reader to set the homepage
		FileReader file = new FileReader(file_name);
		BufferedReader textReader = new BufferedReader(file);
		String homeurl = textReader.readLine();
		textReader.close();
		Toolbar.urls.add("http://www.google.com");
		//Creating objects
		Browser br = new Browser();   //The browser
		JPane jPane = new JPane(homeurl);	//The JPane
		Toolbar tBar = new Toolbar(jPane);	//The Toolbar
		jPane.setBar(tBar);
		br.add(tBar, BorderLayout.NORTH);
		br.add(new JScrollPane(jPane), BorderLayout.CENTER);
		br.setVisible(true);  
		tBar.backEnable(tBar);  //updating the back button
		tBar.forwEnable(tBar);  //updating the forw button
	}

}
