package configurations;

import clients.RestClient;
import org.testng.annotations.BeforeClass;
import utils.PropertiesHelper;

import static utils.PropertiesHelper.getInstance;

public class BaseTest {
  protected static RestClient restClient;
  protected static PropertiesHelper propertiesHelper;
  protected static int usersCount;
  protected static String headerName;
  protected static String headerValue;

  @BeforeClass
  public static void beforeClassConfiguration() {
    propertiesHelper = getInstance();
    restClient = new RestClient();

    initializeConstants();
  }

  private static void initializeConstants() {
    usersCount = Integer.parseInt(propertiesHelper.getProperty("usersCount"));
    headerName = propertiesHelper.getProperty("headerName");
    headerValue = propertiesHelper.getProperty("headerValue");
  }
}
