package OnlineTestExam;

//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
// import javax.imageio.stream.ImageInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class OnlineTest {

	public static WebDriver driver;
	public static void main(String[] args) throws IOException {
	
		System.setProperty("webdriver.chrome.driver", "F:\\NARESH WORKSPACE\\WorkspaceSelenium\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		//1. Open the page https://en.wikipedia.org/wiki/...
		driver.get("https://en.wikipedia.org/wiki/Selenium");
		
		//2. Verify that the external links in “External links“ section work
		WebElement element = driver.findElement(By.id("External_links"));
		VerifyExternalLink(element);
		
		//3.Click on the “Oxygen” link on the Periodic table at the bottom of the page
		WebElement O2 = driver.findElement(By.xpath("//td[@title='O, Oxygen']//a[@href='/wiki/Oxygen']"));
		String oxy = O2.getAttribute("title");  
		O2.click();
		System.out.println("Clicked on given element:" +oxy);
		
		//4.Verify that it is a “featured article”
		String fa = driver.getCurrentUrl();
		if(fa.contains("Oxygen")) {
			System.out.println("It is a featured article");
		}
		else
		{
			System.out.println("It is a not featured article");
		}
		
		//5.take screens shot
		WebElement prop = driver.findElement(By.xpath("//table[@class='infobox']"));
		prop.getLocation();
		Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		//full image of the entire current web page
		File fullimg = new File("F:\\NARESH WORKSPACE\\WorkspaceSelenium\\NareshChilamakuriWorkspace\\OnlineTest\\src\\fullwebpage.png");
		System.out.println(fullimg.getAbsolutePath());
		ImageIO.write(screenshot.getImage(),"PNG",fullimg);
		int width = prop.getSize().getWidth();
		int height = prop.getSize().getHeight();
		BufferedImage fullpage = screenshot.getImage();
		BufferedImage dest = fullpage.getSubimage(prop.getLocation().getX(),prop.getLocation().getY(), width, height);
		//particular  web element image
		File parimg = new File("F:\\NARESH WORKSPACE\\WorkspaceSelenium\\NareshChilamakuriWorkspace\\OnlineTest\\src\\oxygenproperties.png");
		ImageIO.write(dest,"PNG",parimg);
		System.out.println(parimg.getAbsolutePath());
		
		//6.Count the number of pdf links in “References“
		List<WebElement> list = driver.findElements(By.className("reflist columns references-column-width"));
		list.size();

		
		driver.close();
		

	}
	//2 value verifying
	public static void VerifyExternalLink(WebElement element) {
		String s1 = element.getText();
		boolean b1 = element.isDisplayed();
		boolean b2 = element.isEnabled();
		boolean b3 = element.isSelected();
		String aa = element.getAttribute("href");
		System.out.println(aa);
		System.out.println("b1 : "+b1+", b2 : "+b2+", b3 :"+b3);
		if(b1 && b2)
		{
				if(b3 && element.getAttribute("href")!=null) //for every link there should be href attribute
				{
					element.click();  // if element is having href, then it redirect to that page
					System.out.println("'"+s1+" ' Element is displayed & Enabled and working");
				}
				else		// if element doesn't have href, then it unable to redirect
				{
					System.out.println("'"+s1+" ' Element is displayed & Enabled but not working");
				}
		}
		else 
		{
				System.out.println("'"+s1+" ' Element is not displayed");
		}
	}  // 2 condition closed
	
}
