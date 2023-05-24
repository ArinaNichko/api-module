package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

  public static ExtentReports createInstance(String fileName) {
    ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);

    sparkReporter.config().setTheme(Theme.DARK);
    sparkReporter.config().setDocumentTitle("Statistics Reports");
    sparkReporter.config().setEncoding("utf-8");
    sparkReporter.config().setReportName(fileName);

    ExtentReports extent = new ExtentReports();
    extent.attachReporter(sparkReporter);

    return extent;
  }
}