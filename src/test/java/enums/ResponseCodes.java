package enums;

public enum ResponseCodes {
  OK(200);

  private final int statusCode;

  ResponseCodes(int statusCode) {
    this.statusCode = statusCode;
  }

  public int getValue() {
    return statusCode;
  }
}
