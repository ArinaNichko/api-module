package enums;

public enum ResponseCodes {
  OK(200),
  CREATED(201),
  BAD_REQUEST(400),
  UNAUTHORIZED(401),
  INTERNAL_SERVER_ERROR(500),
  NOT_FOUND(404);

  private final int statusCode;

  ResponseCodes(int statusCode) {
    this.statusCode = statusCode;
  }

  public int getValue() {
    return statusCode;
  }
}
