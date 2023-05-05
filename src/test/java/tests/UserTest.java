package tests;

import com.fasterxml.jackson.databind.JsonNode;
import configurations.BaseTest;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;

import org.testng.annotations.Test;
import utils.DataUtil;

import static enums.ResponseCodes.OK;
import static java.util.Collections.emptyMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertFalse;
import static utils.FileHelper.readJsonData;

@Slf4j
public class UserTest extends BaseTest {
  private static final int USERS_COUNT = 10;

  @Test
  public void getStatus() {
    Response response =
            restClient.sendRequestWithParams(Method.GET, emptyMap());

    assertThat(response.statusCode(), Matchers.equalTo(OK.getValue()));
  }

  @Test(dataProvider = "HeaderData", dataProviderClass = DataUtil.class)
  public void getHeader(String headerName, String headerValue) {
    Response response =
            restClient.sendRequestWithParams(Method.GET, emptyMap());

    assertFalse(response.getHeader(headerName).isEmpty());
    assertThat(response.getHeader(headerName),
            Matchers.equalTo(headerValue));
  }

  @Test
  public void getCountOfUser() {
    JsonNode expectedResult = readJsonData(usersPath);

    Response response =
            restClient.sendRequestWithParams(Method.GET, emptyMap());
    JsonNode actualResult = readJsonData(response.asString());

    assertThat(actualResult.size(), Matchers.equalTo(USERS_COUNT));
    assertThat(actualResult, Matchers.equalTo(expectedResult));
  }
}
