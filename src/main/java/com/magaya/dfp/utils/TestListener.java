package com.magaya.dfp.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {

    public void saveFailureScreenShot(WebDriver driver, ITestResult iTestResult) {
        String projectPath = System.getProperty("user.dir");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(projectPath + "/screenshots/" + iTestResult.getName() + "_" + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("@@@@@@@@ START @@@@@@@@");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("@@@@@@@@ SUCCESS @@@@@@@@");
    }


    public void onTestFailure(ITestResult iTestResult) {
        ITestContext context = iTestResult.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        if (driver instanceof WebDriver) {
            saveFailureScreenShot(driver, iTestResult);
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("@@@@@@@@ SKIPPED @@@@@@@@");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("PERCENTAGE");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("@@@@@@@@ START @@@@@@@@");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("@@@@@@@@ FINISH @@@@@@@@");
    }


}
