package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;







import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import sun.text.resources.FormatData;

public class StartScreen {

	private static StartScreen window;
	private static JFrame frame;
	private static GraphicsDevice graphicsDevice;
	
	/**
	 * Create the application.
	 */
	public StartScreen() {
		initialize();
	}

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		drawWindow();
		
	}

	public static void drawWindow(){
		GraphicsEnvironment graphicsEnvironment =  GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] devices = graphicsEnvironment.getScreenDevices();
		graphicsDevice = devices[0];
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new StartScreen();
					graphicsDevice.setFullScreenWindow(window.frame);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void maximizeWindow(){
		
		window.frame.setVisible(true);
		window.frame.setExtendedState(window.frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(500, 500, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(8,8));
		frame.setDefaultLookAndFeelDecorated(true);
		
		JButton oButton1 = new JButton("Demo 1");
		oButton1.setPreferredSize(new Dimension(10, 10));
		oButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<XmlInclude> includeMethodsList = new ArrayList<XmlInclude>();
				includeMethodsList.add(new XmlInclude("sqlInjectionAttackTestOne"));
				startTest(includeMethodsList);
				
			}
		});
		
		JButton oButton2 = new JButton("Demo 2");
		oButton2.setPreferredSize(new Dimension(10, 10));
		oButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<XmlInclude> includeMethodsList = new ArrayList<XmlInclude>();
				includeMethodsList.add(new XmlInclude("xssAttackTestOne"));
				startTest(includeMethodsList);
				
			}
		});
		
		JLabel lblBarracudaWafDemo = new JLabel("Barracuda WAF Demo Application");
		lblBarracudaWafDemo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBarracudaWafDemo.setVerticalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblBarracudaWafDemo);
		frame.getContentPane().add(oButton1);
		frame.getContentPane().add(oButton2);
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
