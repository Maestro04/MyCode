package seleniumPackage;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;




public class WebElements_Actions extends SeleniumLaunchBrowser
{

	public void createNewTabOne(String url)
	{
		this.url=url;
		instantiateBrowser();

		// First way of handling creation of new tab, closing the same and shifting to the initially opened tab.
		String originalwin= driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.close(); // Closes currently focused tab.
		driver.switchTo().window(originalwin); //Shifting to the initially opened tab.
		driver.navigate().to(url);
		System.out.println(driver.getCurrentUrl());	
		driver.quit();
		
	}
	public void createNewTabTwo(String url) throws InterruptedException
	{
		this.url=url;
		instantiateBrowser();
		driver.switchTo().newWindow(WindowType.TAB);
		
		// Navigate through opened tabs
		ArrayList<String> tab= new ArrayList<String>(driver.getWindowHandles());
		System.out.println(tab.size()); // Gets the number of opened tabs.
		driver.switchTo().window(tab.get(1));
		System.out.println(driver.getTitle());
		driver.close();
		driver.switchTo().window(tab.get(0));
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println(driver.getCurrentUrl());
		driver.quit();
	}
	public void tableAction()
	{
		instantiateBrowser();
		driver.get("C:/Users/baner/Downloads/newsample.html");
	    
		int csize;
		
		// To locate table
	    WebElement table= driver.findElement(By.id("users-list"));
	    
	    // To locate table rows
	    List<WebElement> rows= table.findElements(By.tagName("tr"));
	    
	    // To calculate number of rows in the table
	    int rsize=rows.size();
	    System.out.println("Total Row Count: "+rsize);
	    
	    /* To get data(cell value) from each cell in the table
	     * NOTE: To get data(cell value) from each cell accurately, use <tbody> tag(in line 89) while locating the table.
	     */
	    
	    // Loop will execute till the last row
	    for(int i=0; i<rsize; i++)
	    {
		    // To locate cells of that specific row
		    List<WebElement> cells= rows.get(i).findElements(By.tagName("td"));
		    
		    // To calculate number of cells in that specific row
		    csize=cells.size();
		    System.out.println("Number of cells In Row " + i + " are " + csize);
		    
		    // Loop will execute till the last cell of that specific row
		    for(int j=0; j<csize; j++)
		    {
		    	// To retrieve data(value) from that specific cell
		    	
		    	String cellValue= cells.get(j).getText();
		    	
		    	System.out.println("Cell Value of row number " + i + " and column number " + j + " Is " + cellValue);
		    	
		    }
		    
		    System.out.println("-------------------------------------------------- ");
	    }
	    

	    // To locate all cells of the table
	    List<WebElement> cells= table.findElements(By.tagName("td"));
	    
	    // To calculate total number of cells of the table
	    csize=cells.size();
	    System.out.println("Total Cell Count: "+csize);
	    
	    // To locate cells head or column head of the table
	    List<WebElement> cellheads= table.findElements(By.tagName("th"));
	    
	    // To calculate number of cells head or column head of the table
	    int chsize=cellheads.size();
	    System.out.println("Total Cell_Heads Count: "+chsize);
	    
	    // To calculate number of columns of the table
	    int colsize=(csize+chsize)/rsize;
	    System.out.println("Total Column Count: "+colsize);
	    
	    
	    driver.quit();
	}
	
	@SuppressWarnings("deprecation")
	public void screenShotAction() throws IOException, InterruptedException
	{
		instantiateBrowser();
		driver.navigate().to("https://www.ebizindia.com/");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		
		// 1) Capture screen_shot for current opened window
		File imgcapture= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try
		{
			FileHandler.copy(imgcapture, new File("C:/Users/baner/Downloads/ss.png")); // copies and saves the screen_shot image
			// to mentioned location
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		// 2) Capture screen_shot of entire web page
		
		Screenshot imgss= new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1500)).takeScreenshot(driver); // Capturing the Screenshot with the help of  Ashot()
		File entireimg = new File("C:/Users/baner/Downloads/ss1.png");
		try
		{
			ImageIO.write(imgss.getImage(), "png", entireimg); //The screenshot to be captured will be in .png image
			// format and would be saved at above mentioned path.
			// NOTE: We can use BufferedImage actualimage= imgss.getImage() method to get the captured screen_shot image.
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		// 3) Capturing screenshot of a specific web element
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		WebElement textlink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("3s-services")));
		File eleimage= textlink.getScreenshotAs(OutputType.FILE);
		
		try
		{		
			File eximage= new File("C:/Users/baner/Downloads/ss2.png");
			FileHandler.copy(eleimage,eximage);
			
			//textlink.sendKeys(Keys.PAGE_DOWN);		
			/* 1) For scrolling by defining number of pixels(normal scroll) use the following:
			 * JavascriptExecutor js= (JavascriptExecutor)driver;
			 * js.executeScript("window.scrollBy(0,350)", "");
			 * NOTE: (+ve)x & y denotes right & down scrolling and (-ve)x & y denotes left & up scrolling, respectively.
			 * 
			 * 2) For scrolling to the bottom of the web page:
			 * js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			*/
			
			//Screenshot webeleimage= new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, textlink);
			//File eleimage = new File("C:/Users/baner/Downloads/ss2.png");
			//ImageIO.write(webeleimage.getImage(), "PNG", eleimage);
			
		}
		catch(Exception e)	
		{
			System.out.println(e.getMessage());
		}
		
		// 4) Comparing web-element image with initial image.
		
		try
		{
			// Reading the image for comparison
			BufferedImage actualimage= ImageIO.read(imgcapture);
			BufferedImage expectedimage= ImageIO.read(eleimage);
			ImageDiffer img_diff= new ImageDiffer();
			ImageDiff difference= img_diff.makeDiff(expectedimage, actualimage);
			if(difference.hasDiff())
			{
				System.out.println("Both images didn't match");
			}
			else
			{
				System.out.println("Both images matched");
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			driver.quit();
		}
		
	}
	public void mouseHover()
	{
		// Get tool_tip text in mouse-hover action.
		
		instantiateBrowser();
		driver.navigate().to("https://www.google.com/");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement tooltipelem= driver.findElement(By.id("APjFqb"));
		String toolTipMsg= tooltipelem.getAttribute("title");
		System.out.println("Tool-tip message: "+toolTipMsg); // Get the desired text through getAttribute() method
		// if the element has title/aria-label attribute
		
		// OR
		
		// Use Action class to perform the desired action
		
		Actions action= new Actions(driver);
		action.moveToElement(tooltipelem).perform(); //Performing the mouse hover action
		String ttMessage=null;
		if(tooltipelem.equals(driver.switchTo().activeElement())) // Check if driver focus is currently in desired element
		{
			ttMessage= tooltipelem.getAttribute("title");
			System.out.println("Tool-tip message using 'Actions' class: "+ttMessage);
		}
		
		// Comparing the two strings returned
		
		if(toolTipMsg.equals(ttMessage))
		{
			System.out.println("Tool-tip messsages matched in both ways");
		}
		else
		{
			System.out.println("Tool-tip messsages didn't match in both ways");
		}

		driver.quit();
		
	}
	
	public void alertPopUpError() throws InterruptedException
	{
		/* 1) Getting error message for blank validation.
		 * 2) Getting error message for blank validation from alert window.
		*/
		
		// 1) Getting error message for blank validation.
		
		instantiateBrowser();
		driver.navigate().to("https://demo8.ebizindia.com/memdir/login.php");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\'login-box\']/div/form/div[4]/div/button")).click();
		System.out.println("Erro Message: "+driver.findElement(By.id("alert-error-message")).getText());
		
		driver.findElement(By.id("login_username")).sendKeys("nishant@ebizindia.com");
		driver.findElement(By.id("login_password")).sendKeys("xyz123");
		driver.findElement(By.xpath("//*[@id=\'login-box\']/div/form/div[4]/div/button")).click();
		Thread.sleep(1000);
		driver.navigate().to("https://demo8.ebizindia.com/memdir/users.php#mode=addUser");
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		driver.findElement(By.id("record-save-button")).click();
		
		// 2) Getting error message for blank validation from alert window
		Alert alert= driver.switchTo().alert();
		System.out.println("Alert Message: "+alert.getText());
		alert.accept();

		driver.quit();
	}
	
	public void dropDownActions() throws InterruptedException
	{
		/* 1) Selection of item from drop-down
		 * 2) Getting all items as List
		 * 3) Getting size of the drop-down
		 * 4) Getting the selected item
		 * 5) Printing all items of drop-down
		 */
		
		instantiateBrowser();
		driver.navigate().to("https://demo8.ebizindia.com/memdir/login.php");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.findElement(By.id("login_username")).sendKeys("nishant@ebizindia.com");
		driver.findElement(By.id("login_password")).sendKeys("xyz123");
		driver.findElement(By.xpath("//*[@id=\'login-box\']/div/form/div[4]/div/button")).click();
		Thread.sleep(1000);
		driver.navigate().to("https://demo8.ebizindia.com/memdir/users.php#mode=addUser");
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		driver.findElement(By.id("record-save-button")).click();
		Alert alert= driver.switchTo().alert();
		alert.accept();
		
		// Verifying the border color on error
		try
		{
			String orgColorHex= "#ff0000";
			WebElement dDown= driver.findElement(By.id("add_form_field_title"));
			String rgbCol= dDown.getCssValue("border-color"); // Getting color property
			System.out.println("Color Is: "+rgbCol);
			/*String hexCode= Color.fromString(rgbCol).asHex(); // Converting to HEX Code
			System.out.println("HEX Code Of Color: "+hexCode);
			if(orgColorHex.equals(hexCode))
			{
				System.out.println("Color matched");
			}
			else
			{
				System.out.println("Color didn't match");
			}*/
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		driver.quit();
		
		/*Select drop= new Select(driver.findElement(By.id("add_form_field_title")));
		drop.selectByIndex(1); // Selection of item from drop-down
		
		List<WebElement> options= drop.getOptions(); // Getting all items as List
		System.out.println("Size of drop-down: "+options.size()); // Getting size of the drop-down
		System.out.println("Selected Item: "+drop.getFirstSelectedOption().getText()); // Getting the selected item
		for(WebElement items: options) // Printing all items of drop-down
		{
			System.out.println(items.getText());
		}*/
		
	}
	
	public void brokenLinkActions() throws InterruptedException
	{
		/* 1) Fetch all links present on the web page
		 * 2) Send HTTP request for the link
		 * 3) Verify the HTTP response code for the link
		 * 4) Determine if the link is valid or it is broken based on the HTTP response code
		 * 5) Repeat the process for all links captured with the first step
		 */
		
		instantiateBrowser();
		driver.navigate().to("https://www.memdir.in/index.php");
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		
		List<WebElement> links= driver.findElements(By.tagName("a"));
		System.out.println("Number Of Links: "+links.size());
		String url=null;
		for(WebElement fetchedLinks: links)
		{
			url= fetchedLinks.getAttribute("href");
			
			try
			{
				URL link= new URL(url);
				HttpURLConnection setConnection= (HttpURLConnection) link.openConnection();
				setConnection.setConnectTimeout(3000);
				setConnection.connect();
				
				if(setConnection.getResponseCode()== 200)
				{
					System.out.println(url+": "+setConnection.getResponseMessage());
				}
				else
				{
					System.out.println(url+": "+setConnection.getResponseMessage()+"- is broken");
				}
				
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}			
		}
		driver.close();
			
	}

}
