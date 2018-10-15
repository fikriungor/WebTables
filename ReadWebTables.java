package WebTables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadWebTables {
	
		@Test
		public void readScores() throws InterruptedException
		{
			
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("file:///C:/Users/meral/my-app/src/test/java/WebTables/webTable.html");
			
			WebElement table = driver.findElement(By.tagName("table"));
			System.out.println(table.getText());
			
			List<WebElement> rows = driver.findElements(By.xpath("//table[@id='worldcup']/tbody/tr"));
			System.out.println("Table rows count: " + rows.size());
			
			String headerPath = "//table[@id = 'worldcup']/thead/tr/th";
			List<WebElement> headers = driver.findElements(By.xpath(headerPath));
			
			List <String> expHeaders = Arrays.asList("Team1","Score","Team3");
			List <String> actHeaders = new ArrayList<>();
			
			for (WebElement h:headers) {
				
				actHeaders.add(h.getText());
			}
			
			SoftAssert soft = new SoftAssert();
			soft.assertEquals(actHeaders, expHeaders);
			
			soft.assertAll();
			
			int rowsCount = driver.findElements(By.xpath("//table[@id='worldcup']/tbody/tr")).size();
			int colsCount = driver.findElements(By.xpath("//table[@id='worldcup']/tbody/tr/th")).size();
			
			for (int rowNum = 1; rowNum<=rowsCount; rowNum++) {
				for (int colNum = 1; colNum<=colsCount; colNum++) {
					String xpath = "//table[@id='worldcup']/tbody/tr["+rowNum+"]/td["+colNum+"]";
					String tdData = driver.findElement(By.xpath(xpath)).getText();
					System.out.print(tdData+ " ");
				}
				System.out.println();
			}
			
			driver.close();

	}

}
