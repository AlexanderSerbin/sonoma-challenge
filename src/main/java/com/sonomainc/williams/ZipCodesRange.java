package com.sonomainc.williams;

import static com.sonomainc.williams.Constants.Messages.LEFT_BOUNDARY_CANT_BE_LESS_THAN_MIN;
import static com.sonomainc.williams.Constants.Messages.LEFT_BOUNDARY_CANT_BE_MORE_THAN_RIGHT;
import static com.sonomainc.williams.Constants.Messages.RIGHT_BOUNDARY_CANT_BE_MORE_THAN_MAX;
import static com.sonomainc.williams.Constants.Messages.WRONG_RANGE_TEXT_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ZipCodesRange implements Comparable<ZipCodesRange> {

  private static final String ZIP_CODE_FORMAT = "%05d";
  private static final Pattern RANGE_PATTERN = Pattern
      .compile("\\[{1}(\\d{1,5}),{1}(\\d{1,5})\\]{1}");

  private int leftBoundary;
  private int rightBoundary;

  static final int THE_VERY_LEFT_ZIPCODE = 0;
  static final int THE_VERY_RIGHT_ZIPCODE = 99999;

  public ZipCodesRange(int leftBoundary, int rightBoundary) {
    checkConstraints(leftBoundary, rightBoundary);
    this.leftBoundary = leftBoundary;
    this.rightBoundary = rightBoundary;
  }

  private void checkConstraints(int leftBoundary, int rightBoundary) {
    validateLeftBoundary(leftBoundary);
    validateRightBoundary(rightBoundary);
    validateInterval(leftBoundary, rightBoundary);
  }

  private void validateInterval(int leftBoundary, int rightBoundary) {
    if (leftBoundary > rightBoundary) {
      throw new IllegalArgumentException(
          String.format(LEFT_BOUNDARY_CANT_BE_MORE_THAN_RIGHT, leftBoundary, rightBoundary));
    }
  }

  private void validateRightBoundary(int rightBoundary) {
    if (rightBoundary > THE_VERY_RIGHT_ZIPCODE) {
      throw new IllegalArgumentException(
          String.format(RIGHT_BOUNDARY_CANT_BE_MORE_THAN_MAX, rightBoundary));
    }
  }

  private void validateLeftBoundary(int leftBoundary) {
    if (leftBoundary < THE_VERY_LEFT_ZIPCODE) {
      throw new IllegalArgumentException(
          String.format(LEFT_BOUNDARY_CANT_BE_LESS_THAN_MIN, leftBoundary));
    }
  }

  public int getLeftBoundary() {
    return leftBoundary;
  }

  public int getRightBoundary() {
    return rightBoundary;
  }

  public void setLeftBoundary(int leftBoundary) {
    checkConstraints(leftBoundary, this.rightBoundary);
    this.leftBoundary = leftBoundary;
  }

  public void setRightBoundary(int rightBoundary) {
    checkConstraints(this.leftBoundary, rightBoundary);
    this.rightBoundary = rightBoundary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ZipCodesRange that = (ZipCodesRange) o;

    return new EqualsBuilder()
        .append(leftBoundary, that.leftBoundary)
        .append(rightBoundary, that.rightBoundary)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(leftBoundary)
        .append(rightBoundary)
        .toHashCode();
  }

  @Override
  public String toString() {
    return '['
        + String.format(ZIP_CODE_FORMAT, leftBoundary)
        + ','
        + String.format(ZIP_CODE_FORMAT, rightBoundary)
        + ']';
  }

  public static ZipCodesRange of(int a, int b) {
    return new ZipCodesRange(a, b);
  }

  @Override
  public int compareTo(ZipCodesRange zipCodesRange) {
    return Integer.compare(leftBoundary, zipCodesRange.leftBoundary);
  }

  public static ZipCodesRange valueOf(String range) {
    range = range != null ? range.trim() : "";
    Matcher matcher = RANGE_PATTERN.matcher(range);
    if (!matcher.matches()) {
      throw new InvalidFormatException(String.format(WRONG_RANGE_TEXT_FORMAT, range));
    }
    return of(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
  }

}
