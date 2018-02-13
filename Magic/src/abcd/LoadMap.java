package abcd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoadMap
{
	WebDriver driver; // Global declaration of variable driver
	public void launchBrowser()
	{
		//System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
		/*Above code is used to instantiate browser driver but here we are not using it 
		 * since we are using Selenium 2.53.0 version 
		where the default browser is Firefox*/
		
		driver = new FirefoxDriver();  // Creating browser driver
		driver.get("http://www.mapsynq.com"); // application url populated
	}
	public void verifyApp()
	{
		boolean element = driver.findElement(By.xpath(".//*[@id='div_header']/div[2]/div[4]/a[1]")).isDisplayed(); // Verifying existence of WebElement SignIn
		if(element == true)     // Examining the expected output
		{
			System.out.println("Test Case Passed");   // If statement is true, then test case is passed
		}
		else
		{
			System.out.println("Test Case Failed");  //If statement is false, then test case is failed
		}
	}
	public void closeBrowser()
	{
		driver.close();   // Closes the application after verification
	}

	public static void main(String[] args)
	{
		LoadMap ob = new LoadMap();  // Creating object of LoadMap class
		ob.launchBrowser();         // Accessing Test Cases using the object reference
		ob.verifyApp();            // Accessing Test Cases using the object reference
		ob.closeBrowser();        // Accessing Test Cases using the object reference		
}
}
