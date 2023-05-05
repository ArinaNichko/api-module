package configurations;

import clients.RestClient;
import org.testng.annotations.BeforeClass;
import utils.PropertiesHelper;

import static utils.PropertiesHelper.getInstance;

public class BaseTest {
  protected static RestClient restClient;
  protected static PropertiesHelper propertiesHelper;
  protected static String usersPath;

  @BeforeClass
  public static void beforeClassConfiguration() {
    propertiesHelper = getInstance();
    restClient = new RestClient();
    usersPath = propertiesHelper.getProperty("usersPath");
  }
}
