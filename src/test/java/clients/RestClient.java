package clients;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import utils.PropertiesHelper;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.util.Collections.emptyMap;
import static java.util.Optional.ofNullable;

public class RestClient {
  public static String baseURL = PropertiesHelper.getInstance().getProperty("baseUrl");

  public Response sendRequestWithParams(
          Method requestType, Map<String, String> queryParams) {
    return sendRequestForHttpMethods(requestType, null, queryParams);
  }

  private Response sendRequestForHttpMethods(
          Method httpMethod, Object requestBody, Map<String, String> params) {
    RequestSpecification requestSpecification =
            given()
                    .contentType(ContentType.JSON)
                    .body(ofNullable(requestBody).orElse(StringUtils.EMPTY))
                    .queryParams(ofNullable(params).orElse(emptyMap()));
    return requestSpecification.request(httpMethod, baseURL);
  }
}
