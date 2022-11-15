package com.magaya.dfp.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.Duration;

import static java.lang.Boolean.TRUE;
import static org.springframework.util.ObjectUtils.isEmpty;

@PropertySource("classpath:application.properties")
@Configuration
public class DriverConfiguration {

    @Value("${baseUrl}")
    private String baseUrl;
    @Value("${browser}")
    private String browser;

    @Value("${browser.headless}")
    private boolean headless;


    private WebDriver driver;

    private WebDriverWait wait;


    public WebDriver getDriver() {
        if (isEmpty(driver)) {
            initialiseDriver();
        }
        return driver;
    }

    public WebDriverWait getWait() {
        if (isEmpty(wait)) {
            initialiseDriver();
        }
        return wait;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    private void initialiseDriver() {
        setDriverProperty();
        if ("chrome".equals(browser)) {
            confChromeDriver();
        } else if ("firefox".equals(browser)) {
            confFirefoxDriver();
        }
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.navigate().to(baseUrl);
    }

    private void setDriverProperty() {
        if (System.getProperty("os.name").toLowerCase().contains("win")){
            if(browser.equalsIgnoreCase("firefox")){
                System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/win/geckodriver.exe");
            }else if(browser.equalsIgnoreCase("chrome")){
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/win/chromedriver.exe");
            }
        } else if (System.getProperty("os.name").toLowerCase().contains("mac")){
            if(browser.equalsIgnoreCase("firefox")){
                System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/mac/geckodriver");
            }else if(browser.equalsIgnoreCase("chrome")){
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/mac/chromedriver");
            }
        }
    }

    private void confChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");

        // Headless mode
        if (TRUE.equals(headless)) {
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("window-size=1920,1080");
        }
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void confFirefoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxBinary firefoxBinary = new FirefoxBinary();

        // Headless mode
        if (TRUE.equals(headless)) {
            firefoxBinary.addCommandLineOptions("--headless");
            firefoxOptions.addArguments("--width=1920");
            firefoxOptions.addArguments("--height=1080");
        }

        firefoxOptions.setBinary(firefoxBinary);

        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void closeDriver() {
        driver.close();
    }

}

