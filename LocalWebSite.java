package localWebsite;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class LocalWebSite {
	WebDriver driver;
	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		System.setProperty("driver.chrome,webdriver", "/Users/manasnageshwar/Downloads/chromedriver-mac-arm64/chromedriver");
		driver.get("http://127.0.0.1:5500/index.html");
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	@Test
	public void test() {
		Actions action = new Actions(driver);
		WebElement slider = driver.findElement(By.id("slider"));
		action.dragAndDropBy(slider, 174, 0).build().perform();
		ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("slider-val")), "100");
		Assert.assertEquals(driver.findElement(By.id("slider-val")).getText(), "100");
		
		WebElement resize = driver.findElement(By.id("resizer"));
		action.clickAndHold(resize).moveToElement(resize, 200, 0).release().build().perform();
		Assert.assertEquals(driver.findElement(By.id("resize-status")).getText(), "Success: Box Expanded!");
		
		WebElement canvas = driver.findElement(By.id("paint-canvas"));
		action.moveToElement(canvas, -150, -50).clickAndHold().moveByOffset(100, 50).moveByOffset(200, 200).release().build().perform();
//		action.moveToElement(canvas, -150, -50) // Starting point relative to center
//        .clickAndHold()
//        .moveByOffset(100, 50)           // Draw diagonal line
//        .moveByOffset(100, -50)          // Draw another line to make a "V"
//        .release()
//        .perform();
		WebElement button = driver.findElement(By.id("hold-btn"));
		action.clickAndHold(button).pause(Duration.ofSeconds(3)).release().build().perform();
		
		
//		for(int i=174;i<175;i++) {
//			try{
//				action.dragAndDropBy(slider, i, 0).perform();
//			}
//			catch(Exception e) {
//				break;
//			}
//		}
//		WebElement list = driver.findElement(By.xpath("//li[text()='Item 1']"));
//		WebElement list2 = driver.findElement(By.xpath("//li[text()='Item 2']"));
//		WebElement list3 = driver.findElement(By.xpath("//li[text()='Item 3']"));
////		action.keyDown(Keys.COMMAND).keyDown(Keys.SHIFT).keyDown(Keys.getKeyFromUnicode('J')).perform();
//		action.keyDown(Keys.CONTROL)  // 1. Press and hold Ctrl
//	      .click(list)
//	      .click(list2)
//	      .click(list3)// 2. Perform the click
//	      .keyUp(Keys.CONTROL)    // 3. IMPORTANT: Release the key
//	      .build()
//	      .perform();
		WebElement tooltip = driver.findElement(By.id("tooltip-trigger"));
		action.moveToElement(tooltip).perform();
		ExpectedConditions.visibilityOfAllElements(driver.findElement(By.id("tooltip-box")));
		Assert.assertEquals(driver.findElement(By.id("tooltip-box")).getText(),"CODE: 2026-BETA");
		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript().
		action.moveToElement(driver.findElement(By.id("drag-status")));
		WebElement clickc = driver.findElement(By.id("db-click-area"));
		action.doubleClick(clickc).perform();
		Assert.assertEquals(driver.findElement(By.id("db-status")).getText(), "Double-click validated.");
		
		WebElement element = driver.findElement(By.id("draggable"));
		WebElement droppeble = driver.findElement(By.id("droppable"));
		
		Actions actions1 = new Actions(driver);
		actions1.dragAndDrop(element, droppeble).perform();
		
		Assert.assertEquals(driver.findElement(By.id("drag-status")).getText(), "Drop validated.");
				
		Actions action2 = new Actions(driver);
		action.contextClick(driver.findElement(By.id("context-area"))).perform();
		
		Assert.assertEquals(driver.findElement(By.id("context-status")).getText(), "Right-click detected!");
		
		WebElement hover = driver.findElement(By.id("hover-menu"));
		action.moveToElement(hover).perform();
		
		action.moveToElement(driver.findElement(By.id("hidden-button"))).click().perform();
		Assert.assertEquals(driver.findElement(By.id("hover-status")).getText(), "Button Clicked!");
		
		
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
