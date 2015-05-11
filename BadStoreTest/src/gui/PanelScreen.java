package gui;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import util.BadStoreTestUtil;

public class PanelScreen {

	private static PanelScreen window;
	private static JFrame frame;
	private static GraphicsDevice graphicsDevice;
	
	public static void addComponentsToPane(Container pane) {
		GridBagConstraints c = new GridBagConstraints();
		frame.setLayout(new GridBagLayout());
		
		JLabel demoLable = new JLabel();
		demoLable.setText("");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		c.weightx = 0.0;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 10;
		frame.add(demoLable, c);
		demoLable = new JLabel();
		demoLable.setText("");
		c.fill = GridBagConstraints.RELATIVE;
		c.weightx = 0.5;
		c.weighty = 0.1;
		c.gridx = 1;
		c.gridy = 0;
		c.ipady = 10;
		frame.add(demoLable, c);
		demoLable = new JLabel();
		demoLable.setText("");
		c.fill = GridBagConstraints.RELATIVE;
		c.weightx = 0.5;
		c.weighty = 0.1;
		c.gridx = 2;
		c.gridy = 0;
		c.ipady = 10;
		frame.add(demoLable, c);
		demoLable = new JLabel();
		demoLable.setText("");
		c.fill = GridBagConstraints.RELATIVE;
		c.weightx = 0.5;
		c.weighty = 0.1;
		c.gridx = 3;
		c.gridy = 0;
		c.ipady = 10;
		frame.add(demoLable, c);
		demoLable = new JLabel();
		demoLable.setText("");
		c.fill = GridBagConstraints.RELATIVE;
		c.weightx = 0.5;
		c.weighty = 0.1;
		c.gridx = 4;
		c.gridy = 0;
		c.ipady = 10;
		frame.add(demoLable, c);
		demoLable = new JLabel();
		demoLable.setText("");
		c.fill = GridBagConstraints.RELATIVE;
		c.weightx = 0.5;
		c.weighty = 0.1;
		c.gridx = 5;
		c.gridy = 0;
		c.ipady = 10;
		frame.add(demoLable, c);
		demoLable = new JLabel();
		demoLable.setText("");
		c.fill = GridBagConstraints.RELATIVE;
		c.weightx =0.5;
		c.weighty = 0.1;
		c.gridx = 6;
		c.gridy = 0;
		c.ipady = 10;
		frame.add(demoLable, c);
		demoLable = new JLabel();
		demoLable.setText("");
		c.fill = GridBagConstraints.RELATIVE;
		c.weightx = 1.0;
		c.weighty = 0.1;
		c.gridx = 7;
		c.gridy = 0;
		c.ipady = 10;
		frame.add(demoLable, c);
		
		demoLable = new JLabel();
		demoLable.setText("Barracuda WAF Demo Application");
		demoLable.setFont(new Font("Arial", Font.PLAIN, 20));
		c.fill = GridBagConstraints.RELATIVE;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.gridx = 3;
		c.gridy = 1;
		c.ipady = 50;
		c.gridwidth = 2;
		frame.add(demoLable, c);
		
		//demo 1 start button 
		JButton demoOneButton = new JButton("Demo 1");
		c.fill = GridBagConstraints.RELATIVE;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.gridx = 0;
		c.gridy = 2;
		c.ipadx = 10;
		c.ipady = 10;
		frame.add(demoOneButton, c);
		
		//demo 2 start button
		JButton demoTwoButton = new JButton("Demo 2");
		c.fill = GridBagConstraints.RELATIVE;
		c.weighty = 1.0;
		c.weightx = 0.0;
		c.gridx = 0;
		c.gridy = 3;
		c.ipadx = 10;
		c.ipady = 10;
		frame.add(demoTwoButton, c);
		
		JLabel demoOneDescriptionLable = new JLabel();
		demoOneDescriptionLable.setText("<html>This is demo for sql injection attack. In sql injection attack, Hacker login into specific user's account without having user's credentials."
				+ "<br/>steps:"
				+ "<br/>1.User creates his/her account on badstore"
				+ "<br/>2.Hacker creates his/her account on badstore"
				+ "<br/>3.User login with username and password"
				+ "<br/>4.User logout"
				+ "<br/>5.Hacker hacks user account with sql injection and user's email address"
				+ "</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 4;
		c.ipadx = 30;
		c.ipady = 10;
		frame.add(demoOneDescriptionLable, c);
		
		
		JLabel demoTwoDescriptionLable = new JLabel();
		demoTwoDescriptionLable.setText("<html>This is demo for cross site scripting(xss). In cross site scripting, Hacker steals user's cookie information to login into user's account by injecting script(cookie stealing code) through posts, comment etc."
				+ "<br/>steps:"
				+ "<br/>1.User creates his/her account on badstore"
				+ "<br/>2.Hacker creates his/her account on badstore"
				+ "<br/>3.Hacker login to his/her account"
				+ "<br/>4.Hacker inject xss with cookie stealing code"
				+ "<br/>5.User login to his/her account"
				+ "<br/>6.User post comment and redirectd to comments page. At this point, hacker's injected script steals user's cookie"
				+ "<br/>7.Hacker creates fake cookie same as user cookie and  Hacker visits website and hacker gets user account access"
				+ "</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1.0;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 4;
		c.ipadx = 30;
		c.ipady = 10;
		frame.add(demoTwoDescriptionLable, c);
		
		
		//button action 
		demoOneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<XmlInclude> includeMethodsList = new ArrayList<XmlInclude>();
				includeMethodsList.add(new XmlInclude("sqlInjectionAttackTestOne"));
				//disable mouse
				try {
					System.out.println("disable input devices");
					BadStoreTestUtil.disableInputDevices();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				startTest(includeMethodsList);
				
			}
		});
		demoTwoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<XmlInclude> includeMethodsList = new ArrayList<XmlInclude>();
				includeMethodsList.add(new XmlInclude("xssAttackTestOne"));
				//disable mouse
				try {
					System.out.println("disable input devices");
					BadStoreTestUtil.disableInputDevices();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				startTest(includeMethodsList);
				
			}
		});
	
		 frame.setExtendedState(frame.MAXIMIZED_BOTH);
		/*JButton button_max=new JButton("Maximize");
	    button_max.addActionListener(new ActionListener(){
	       public void actionPerformed(ActionEvent ae)
	       {
	    	   frame.setExtendedState(frame.MAXIMIZED_BOTH);
	       }
	      });
	    frame.add(button_max);*/
		
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		
		GraphicsEnvironment graphicsEnvironment =  GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] devices = graphicsEnvironment.getScreenDevices();
		graphicsDevice = devices[0];
		
		// Create and set up the window.
		frame = new JFrame("Barracuds WAF Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);

		// Set up the content pane.
		addComponentsToPane(frame.getContentPane());
		
		graphicsDevice.setFullScreenWindow(frame);
		
		// Display the window.
		frame.pack();
		frame.setVisible(true);
		frame.minimumSize();
		//frame.setMaximumSize(frame.getMaximumSize());
		
	}

	public static void main(String[] args) {
		try{
			// disbale mouse right click
			BadStoreTestUtil.disableMouseRightClick();
			// creating and showing this application's GUI.
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					createAndShowGUI();
					
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								window = new PanelScreen();
								graphicsDevice.setFullScreenWindow(window.frame);
								window.frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
public static void startTest(List<XmlInclude> includeMethodsList){
		
		// <suite name="Suite1" parallel="tests" thread-count="2" >
		XmlSuite suite1 = new XmlSuite();
		suite1.setName("Suite1");
		suite1.setParallel(suite1.PARALLEL_TESTS);
		suite1.setThreadCount(2);

		// <test name="Test1_01">
		XmlTest test1_01 = new XmlTest(suite1);
		test1_01.setName("Test1_01");
		/*
		 * <classes> <class name="badstore.TestClass1" /> </classes>
		 */
		List<XmlClass> test1_01_classes = new ArrayList<XmlClass>();
		XmlClass xmlClass1 = new XmlClass("badstore.TestClass1");
		xmlClass1.setIncludedMethods(includeMethodsList);
		test1_01_classes.add(xmlClass1);
		test1_01.setXmlClasses(test1_01_classes);
		
		// <test name="Test1_01">
		XmlTest test1_02 = new XmlTest(suite1);
		test1_02.setName("Test1_02");
		/*
		 * <classes> <class name="badstore.TestClass1" /> </classes>
		 */
		List<XmlClass> test1_02_classes = new ArrayList<XmlClass>();
		XmlClass xmlClass2 = new XmlClass("badstore.TestClass2");
		xmlClass2.setIncludedMethods(includeMethodsList);
		test1_02_classes.add(xmlClass2);
		test1_02.setXmlClasses(test1_02_classes);
		
		
		
		//to run testng
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite1);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		System.out.println("about to run");
		tng.run(); 
		
	}
	
	
}
