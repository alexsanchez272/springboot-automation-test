package com.magaya.dfp;

import com.magaya.dfp.utils.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @Test
    public void checkUrl() {
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equalsIgnoreCase("https://www.google.com/?gws_rd=ssl"));
    }
}
