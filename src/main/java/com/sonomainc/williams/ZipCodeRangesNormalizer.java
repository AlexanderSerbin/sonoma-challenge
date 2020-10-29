package com.sonomainc.williams;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ZipCodeRangesNormalizer {

  private final List<ZipCodesRange> zipCodesRanges;
  private final LinkedList<ZipCodesRange> normalizedList;

  public ZipCodeRangesNormalizer(List<ZipCodesRange> zipCodesRanges) {
    this.zipCodesRanges = zipCodesRanges;
    this.normalizedList = new LinkedList<>();
  }

  public List<ZipCodesRange> normalize() {
    if (zipCodesRanges == null) {
      return normalizedList;
    }
    Collections.sort(zipCodesRanges);
    for (ZipCodesRange zipCodesRange : zipCodesRanges) {
      //to append not overlapping intervals
      if (normalizedList.isEmpty() || getLast().getRightBoundary() + 1 < zipCodesRange
          .getLeftBoundary()) {
        normalizedList.add(zipCodesRange);
      } else {
        //to merge if intervals overlap
        getLast().setRightBoundary(
            Math.max(getLast().getRightBoundary(), zipCodesRange.getRightBoundary()));
      }
    }
    return normalizedList;
  }

  private ZipCodesRange getLast() {
    return normalizedList.getLast();
  }

}
