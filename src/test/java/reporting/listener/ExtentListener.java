package reporting.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reflection.CustomTest;
import reporting.ExtentManager;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Date;

public class ExtentListener implements ITestListener {
  public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();
  static Date date = new Date();
  static String fileName = "Report_" + date.toString().replace(":", "_").replace(" ", "_") + ".html";
  private static final ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir") + "\\reports\\" + fileName);

  public void onTestStart(ITestResult result) {
    ExtentTest test = extent.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
    testReport.set(test);
    Method testMethod = result.getMethod().getConstructorOrMethod().getMethod();

    if (testMethod.isAnnotationPresent(CustomTest.class)) {
      CustomTest customTest = testMethod.getAnnotation(CustomTest.class);
      String testName = customTest.name();
      String modifiers = Modifier.toString(testMethod.getModifiers());
      String logText = "<b>Test Name: " + testName + "</b><br>" +
              "<b>Modifiers: " + modifiers + "</b><br>";
      Markup m = MarkupHelper.createLabel(logText, ExtentColor.PINK);
      testReport.get().info(m);
    }
  }

  public void onTestSuccess(ITestResult result) {
    String methodName = result.getMethod().getMethodName();
    String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
    Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
    testReport.get().pass(m);
    testReport.get().info("Test parameters: " + Arrays.toString(result.getParameters()));
  }

  public void onTestFailure(ITestResult result) {
    String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
    testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
            + "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");

    String failureLogg = "TEST CASE FAILED";
    Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
    testReport.get().log(Status.FAIL, m);
    testReport.get().info("Test parameters: " + Arrays.toString(result.getParameters()));
  }

  public void onTestSkipped(ITestResult result) {
    String methodName = result.getMethod().getMethodName();
    String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
    Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
    testReport.get().skip(m);
    testReport.get().info("Test parameters: " + Arrays.toString(result.getParameters()));
  }

  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
  }

  public void onStart(ITestContext context) {
  }

  public void onFinish(ITestContext context) {
    extent.flush();
  }
}