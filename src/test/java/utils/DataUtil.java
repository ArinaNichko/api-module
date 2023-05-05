package utils;

import model.Header;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.List;

import static utils.FileHelper.readCsvFileAsObject;

public class DataUtil {

  @DataProvider(name = "HeaderData")
  public static Object[][] createHeaderData(ITestContext context) {
    String filepath = context.getCurrentXmlTest().getParameter("filepath");
    List<Header> headerList = readCsvFileAsObject(new File(filepath), Header.class);
    Object[][] data = new Object[headerList.size()][];
    for (int i = 0; i < headerList.size(); i++) {
      Header item = headerList.get(i);
        data[i] = new Object[]{item.getHeaderName(), item.getHeaderValue()};
    }
    return data;
  }
}
