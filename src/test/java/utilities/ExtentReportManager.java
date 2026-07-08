package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {

    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {

        String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("practo.com - Automation Testing");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // System Information
        extent.setSystemInfo("Project", "Automation Testing");
        extent.setSystemInfo("Website", "https://practo.com/");
        extent.setSystemInfo("Environment", "QEA");
        extent.setSystemInfo("Browsers", "Chrome, Edge");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        // Team Members
        extent.setSystemInfo("Testers", "Kiranmoorthy, Abinaya, Yuvan, Bindhu");

    }

    @Override
    public void onTestStart(ITestResult result) {

        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.log(Status.PASS,
                "Test Case PASSED : " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.log(Status.FAIL,
                "Test Case FAILED : " + result.getName());

        test.log(Status.FAIL,
                "Reason : " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.log(Status.SKIP,
                "Test Case SKIPPED : " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}