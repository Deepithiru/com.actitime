package com.acti.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class DriverScript {

	public static WebDriver driver;
	public static Properties prop;

	public DriverScript() {
		try
		{
			File src = new File("./atConfigFile/config.properties");
			FileInputStream fis = new FileInputStream(src);
		    prop = new Properties();
			prop.load(fis);
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}

	}
@Test
	public void initalizeApp() {
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./atbrowser/chromedriver.exe");
			driver = new ChromeDriver();

		}

		else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./atbrowser/geckodriver.exe");
			driver = new FirefoxDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String url = prop.getProperty("url");
		driver.get(url);

	}

}
