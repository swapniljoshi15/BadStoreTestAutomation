import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class TestApplication {

	public static void main(String[] args) {

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
		test1_01_classes.add(new XmlClass("badstore.TestClass1"));
		test1_01.setXmlClasses(test1_01_classes);

		// <test name="Test1_01">
		XmlTest test1_02 = new XmlTest(suite1);
		test1_02.setName("Test1_02");
		/*
		 * <classes> <class name="badstore.TestClass1" /> </classes>
		 */
		List<XmlClass> test1_02_classes = new ArrayList<XmlClass>();
		test1_02_classes.add(new XmlClass("badstore.TestClass2"));
		test1_02.setXmlClasses(test1_02_classes);
		
		//to run testng
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite1);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.run(); 

	}

}
