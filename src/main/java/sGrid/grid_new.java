package sGrid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class grid_new {
	  WebDriver driver;
	/*
	 * @Test public void firefox() throws MalformedURLException { //
	 * DesiredCapabilities capability =new DesiredCapabilities();
	 * capability.setBrowserName("firefox"); capability.setPlatform(Platform.ANY);
	 * FirefoxOptions Options=new FirefoxOptions();
	 * Options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
	 * Options.merge(capability); String Node = "http://192.168.1.24:4445/wd/hub";
	 * WebDriver driver = new RemoteWebDriver(new URL(Node), Options);
	 * driver.get("https://mohs10.io/"); driver.manage().window().maximize(); }
	 * 
	 * @Test(priority = 2) public void chrome() throws MalformedURLException {
	 * //chrome DesiredCapabilities cap = new DesiredCapabilities();
	 * cap.setBrowserName("chrome"); cap.setPlatform(Platform.WIN10); ChromeOptions
	 * options = new ChromeOptions(); options.merge(cap); //options.
	 * setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe "
	 * ); String Node = "http://192.168.1.24:4445/wd/hub"; WebDriver driver = new
	 * RemoteWebDriver(new URL(Node), options);
	 * 
	 * // Launch website driver.get("https://mohs10.io/");
	 * 
	 * }
	 */
	 @Test 
	  public  WebDriver launchapp(String browser,String Url) throws MalformedURLException, InterruptedException { 
		  
	  
	    if (browser.equalsIgnoreCase("firefox")) {
	    	DesiredCapabilities capability =new DesiredCapabilities();
	    	capability.setBrowserName("firefox");
	    	capability.setPlatform(Platform.ANY);
	    	FirefoxOptions Options=new FirefoxOptions();
	    	Options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
	    	Options.merge(capability);
	    	String Node = "http://192.168.1.24:4444/wd/hub";
	    	WebDriver driver = new RemoteWebDriver(new URL(Node), Options);
	    	driver.get(Url);
	    	driver.manage().window().maximize();
	    	System.out.println("firefox closed");
	    	Thread.sleep(5000);
	    	
	    	

	  }
	    
	    
	    else if(browser.equalsIgnoreCase("chrome")) {
	    	 DesiredCapabilities cap = new DesiredCapabilities();
	   	     cap.setBrowserName("chrome");
	   	     cap.setPlatform(Platform.WIN10);
	    
	    	 ChromeOptions options = new ChromeOptions();
	    	 options.merge(cap);
	  	 
	         System.out.println(" Executing on CHROME");
	        String Node = "http://192.168.1.24:4444/wd/hub"; 
	        WebDriver driverq = new RemoteWebDriver(new URL(Node), options);
	
        // Launch website 
	      driverq.get(Url);
	      driverq.manage().window().maximize();
	      Thread.sleep(5000);
	       System.out.println("chrome closed");
	      
	  }
	  
	
	    else if (browser.equalsIgnoreCase("msedge")) { // Check if the browser is Microsoft Edge
	    	 DesiredCapabilities cap =  new DesiredCapabilities();
	    	   cap.setBrowserName("msedge");
	    	    cap.setPlatform(Platform.WIN10);
	    	    EdgeOptions option = new EdgeOptions();
	    	    option.merge(cap);
	    	    
	    	 
	    	    String node = "http://192.168.1.24:4444/wd/hub";
	    	    System.out.println("Executing on edge");
	    	
	    	    WebDriver driver1 = new RemoteWebDriver(new URL(node), option);
	    	 
	    	 
	    	    
	    	    // Create a new RemoteWebDriver to run the Edge driver on a remote machine
	    	  
	    	    // Launch website
	    	    driver1.get(Url);
	    	    driver1.manage().window().maximize();
	    	    Thread.sleep(5000);
	    	    System.out.println("edge closed");
	    	    driver.close();

		}

	 
	  else {
		  throw new IllegalArgumentException("The Browser Type is Undefined");
		  }
	  
		
		
		return driver;
		
	  }
	 
	 @Test
	public void url() throws MalformedURLException, InterruptedException {
		grid_new grid_new = new grid_new();
		grid_new.launchapp("chrome", "https://mohs10.io/");
		grid_new.launchapp("Firefox","https://mohs10.io/");
		//grid_new.launchapp("internet explorer","https://mohs10.io/");
		  
	  }

}
