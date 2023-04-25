package tests;

import configurations.BaseTest;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import org.hamcrest.Matchers;

import org.testng.annotations.Test;

import java.util.List;

import static enums.ResponseCodes.OK;
import static java.util.Collections.emptyMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertFalse;

@Slf4j
public class UserTest extends BaseTest {

  @Test
  public void getStatus() {
    Response response =
            restClient.sendRequestWithParams(Method.GET, emptyMap());

    assertThat(response.statusCode(), Matchers.equalTo(OK.getValue()));
  }

  @Test
  public void getHeader() {
    Response response =
            restClient.sendRequestWithParams(Method.GET, emptyMap());

    assertFalse(response.getHeader(headerName).isEmpty());
    assertThat(response.getHeader(headerName),
            Matchers.equalTo(headerValue));
  }

  @Test
  public void getCountOfUser() {
    Response response =
            restClient.sendRequestWithParams(Method.GET, emptyMap());
    List<String> userList = response.jsonPath().getList("");

    assertThat(userList.size(), Matchers.equalTo(usersCount));
  }
}
