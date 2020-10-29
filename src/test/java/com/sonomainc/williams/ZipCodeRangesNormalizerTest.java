package com.sonomainc.williams;

import static com.sonomainc.williams.ZipCodesRange.*;
import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

public class ZipCodeRangesNormalizerTest {

  @Test
  public void normalizeNullZipIntervals() {
    assertArrayEquals(new ZipCodesRange[]{},
        new ZipCodeRangesNormalizer(null).normalize().toArray());
  }

  @Test
  public void normalizeEmptyZipIntervals() {
    List<ZipCodesRange> zipCodesRanges = Collections.emptyList();
    assertArrayEquals(new ZipCodesRange[]{},
        new ZipCodeRangesNormalizer(zipCodesRanges).normalize().toArray());
  }

  @Test
  // [-----b]
  //         [b+1----]
  public void normalizeTochingZipIntervals() {
    assertArrayEquals(new ZipCodesRange[]{of(1,4)},
        new ZipCodeRangesNormalizer(buildMutableZipCodeRanges(of(1, 2), of(3, 4))).normalize().toArray());
  }

  @Test
  // [-----]
  //             [----]
  public void normalizeNotOverlappingZipIntervals() {
    assertArrayEquals(new ZipCodesRange[]{of(1,2), of(4, 6)},
        new ZipCodeRangesNormalizer(buildMutableZipCodeRanges(of(1, 2), of(4, 6))).normalize().toArray());
  }

  @Test
  // [--b]
  //    [b--]
  public void normalizeTwoIntervalsWithConnectionPoint() {
    assertArrayEquals(new ZipCodesRange[]{of(1, 3)},
        new ZipCodeRangesNormalizer(buildMutableZipCodeRanges(of(1, 2), of(2, 3))).normalize().toArray());
  }

  @Test
  // [----]
  //   [----]
  public void normalizeOverlappingIntervals() {
    assertArrayEquals(new ZipCodesRange[]{of(1, 3)},
        new ZipCodeRangesNormalizer(buildMutableZipCodeRanges(of(1, 3), of(2, 3))).normalize().toArray());
  }

  @Test
  // [----]
  // [----]
  public void normalizeEqualIntervals() {
    assertArrayEquals(new ZipCodesRange[]{of(1, 3)},
        new ZipCodeRangesNormalizer(buildMutableZipCodeRanges(of(1, 3), of(1, 3))).normalize().toArray());
  }

  @Test
  // [----]
  //  [_]
  public void normalizeIncludedInterval() {
    assertArrayEquals(new ZipCodesRange[]{of(1, 5)},
        new ZipCodeRangesNormalizer(buildMutableZipCodeRanges(of(1, 5), of(2, 2))).normalize().toArray());
  }

  @Test
  //        [----]
  //  [__]
  public void normalizeUnsortedIntervals() {
    assertArrayEquals(new ZipCodesRange[]{of(1, 3), of(5, 10)},
        new ZipCodeRangesNormalizer(buildMutableZipCodeRanges(of(5, 10), of(1, 2), of(3, 3))).normalize()
            .toArray());
  }

  @Test
  //[a,a]
  public void normalizePoint() {
    assertArrayEquals(new ZipCodesRange[]{of(5, 5)},
        new ZipCodeRangesNormalizer(buildMutableZipCodeRanges(of(5, 5))).normalize()
            .toArray());
  }

  @Test
  public void challengeExample1Test() {
    assertArrayEquals(new ZipCodesRange[]{of(94133, 94133), of(94200, 94299), of(94600, 94699)},
        new ZipCodeRangesNormalizer(
            buildMutableZipCodeRanges(of(94133, 94133), of(94200, 94299), of(94600, 94699))).normalize()
            .toArray());

  }
  @Test
  public void challengeExample2Test() {
    assertArrayEquals(new ZipCodesRange[]{of(94133,94133), of(94200,94399)},
        new ZipCodeRangesNormalizer(
            buildMutableZipCodeRanges(of(94133,94133), of(94200,94299), of(94226,94399))).normalize()
            .toArray());

  }

  private List<ZipCodesRange> buildMutableZipCodeRanges(ZipCodesRange... intervals) {
    return Arrays.stream(intervals).collect(Collectors.toList());
  }

}