package com.sonomainc.williams;

public class Constants {

  private Constants() {}

  public static class Messages {

    private Messages() {}

    public static final String LEFT_BOUNDARY_CANT_BE_LESS_THAN_MIN =
        "Left boundary zip code %s can't be less than minimal zip code";
    public static final String RIGHT_BOUNDARY_CANT_BE_MORE_THAN_MAX =
        "Right boundary zip code %s boundary can't be more than maximum zip code";
    public static final String LEFT_BOUNDARY_CANT_BE_MORE_THAN_RIGHT =
        "Left boundary zip code %s can't be more than right boundary zip code %s";
    public static final String WRONG_RANGE_TEXT_FORMAT =
        "Zip code range representation %s is of unexpected format. "
            + "Expected format example is [1,2]";

  }

}
