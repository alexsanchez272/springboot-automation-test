package com.magaya.dfp;

import com.magaya.dfp.config.DriverConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

@ContextConfiguration(classes = DriverConfiguration.class)
public class BaseTest  extends AbstractTestNGSpringContextTests {

    @Autowired
    protected DriverConfiguration driver;

    @BeforeMethod(alwaysRun = true)
    public void createTestContext(ITestContext context) {
        context.setAttribute("driver", driver.getDriver());
    }

    @AfterSuite(alwaysRun = true)
    public void closeBrowser() {
        driver.closeDriver();
    }


}
