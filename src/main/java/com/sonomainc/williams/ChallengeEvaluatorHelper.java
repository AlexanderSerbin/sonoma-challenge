package com.sonomainc.williams;

import java.io.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * Tool for verifying correctness of implementation
 */
public final class ChallengeEvaluatorHelper {

  private ChallengeEvaluatorHelper() {
  }

  public static void main(String[] args) {
    Console console = System.console();
    if (console == null) {
      System.out.println("Console is not supported");
      System.exit(1);
    }
    System.out.println("Enter zip code ranges in [a,b] format separated by SPACE (i. e. [1,2] [2,2]): ");
    String line;
    while (StringUtils.isNoneEmpty(line = console.readLine())) {
      try {
        List<ZipCodesRange> ranges = Arrays.stream(line.trim().split(" "))
            .filter(StringUtils::isNotEmpty)
            .map(ZipCodesRange::valueOf)
            .collect(Collectors.toList());
        List<ZipCodesRange> normalizedRange = new ZipCodeRangesNormalizer(ranges).normalize();
        System.out.println("result = " + normalizedRange.stream().map(ZipCodesRange::toString)
            .collect(Collectors.joining(" ")));
      } catch (InvalidFormatException | IllegalArgumentException e) {
        System.out.println(e.getMessage() + ". Try again");
      }
    }
  }

}
