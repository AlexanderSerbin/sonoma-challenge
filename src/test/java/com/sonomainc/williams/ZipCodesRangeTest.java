package com.sonomainc.williams;

import static com.sonomainc.williams.ZipCodesRange.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ZipCodesRangeTest {

  @Test(expected = IllegalArgumentException.class)
  public void testLeftBoundInvalid() {
    new ZipCodesRange(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRightBoundInvalid() {
    new ZipCodesRange(0, 100000);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLeftMoreThanRightBound() {
    new ZipCodesRange(1, 0);
  }

  @Test
  public void testNormalZipInterval() {
    ZipCodesRange interval = new ZipCodesRange(0, 1);
    assertEquals(0, interval.getLeftBoundary());
    assertEquals(1, interval.getRightBoundary());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetWrongLeftBoundary() {
    ZipCodesRange interval = new ZipCodesRange(1, 2);
    interval.setLeftBoundary(5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetLessThanMinimumLeftBoundary() {
    ZipCodesRange interval = new ZipCodesRange(1, 2);
    interval.setLeftBoundary(-1);
  }

  @Test
  public void testSetNormalLeftBoundary() {
    ZipCodesRange interval = new ZipCodesRange(2, 5);
    interval.setLeftBoundary(1);
    assertEquals(1, interval.getLeftBoundary());
    assertEquals(5, interval.getRightBoundary());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetWrongRightBoundary() {
    ZipCodesRange interval = new ZipCodesRange(1, 2);
    interval.setRightBoundary(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetMoreThanMaximumRightBoundary() {
    ZipCodesRange interval = new ZipCodesRange(1, 2);
    interval.setRightBoundary(10000000);
  }

  @Test
  public void testSetNormalRightBoundary() {
    ZipCodesRange interval = new ZipCodesRange(2, 5);
    interval.setRightBoundary(6);
    assertEquals(6, interval.getRightBoundary());
    assertEquals(2, interval.getLeftBoundary());
  }

  @Test
  public void toStringTest() {
    assertEquals("[00000,99999]",
        new ZipCodesRange(THE_VERY_LEFT_ZIPCODE, THE_VERY_RIGHT_ZIPCODE)
            .toString());
  }

  @Test
  public void staticFactoryCreationTest() {
    ZipCodesRange interval = of(1, 2);
    assertEquals(1, interval.getLeftBoundary());
    assertEquals(2, interval.getRightBoundary());
  }

  @Test
  public void createRangeFromValidStringRepresentation() {
    ZipCodesRange range = ZipCodesRange.valueOf("[1,2]");
    assertEquals(1, range.getLeftBoundary());
    assertEquals(2, range.getRightBoundary());
  }

  @Test(expected = InvalidFormatException.class)
  public void createRangeFromInValidStringRepresentation() {
    ZipCodesRange.valueOf("[1,2");
  }

  @Test(expected = InvalidFormatException.class)
  public void createRangeFromNullStringRepresentation() {
    ZipCodesRange.valueOf(null);
  }

  @Test
  public void hashCodeAndEqualsTest() {
    ZipCodesRange range = ZipCodesRange.of(1, 1);
    assertEquals(range, range);
    assertFalse(range.equals(null));
    assertFalse(range.equals(0));
    assertTrue(of(1, 1).equals(of(1, 1)));
    assertEquals(23311, of(1, 1).hashCode());
  }

}