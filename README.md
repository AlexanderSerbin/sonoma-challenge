# The Sonoma back end coding challenge

Source code main points:
## .\src\main\java\com\sonomainc\williams\ZipCodesRange.java

   This class was introduced to encapsulate specific about domain we're normalizing ranges in - shipping/delivery/zip codes.
   Naturally, this class is right place to encapsulate validation for zip code range. Also, it adds
   some convenience by providing static methods for ranges creation.

## .\src\main\java\com\sonomainc\williams\ZipCodeRangesNormalizer.java

    Main part of algorithm which normalizes collection of zip code ranges.
    Implementation's runtime is O(n * log n) since we sort ranges first in O(n * log n) runtime
    and then we one time traverse sorted collection to get ranges merged.

## .\src\test\java\com\sonomainc\williams\ZipCodeRangesNormalizerTest.java
   .\src\test\java\com\sonomainc\williams\ZipCodesRangeTest.javajava/com/sonomainc/williams/ZipCodeRangesNormalizerTest.java

  Unit tests

## .\src\main\java\com\sonomainc\williams\ChallengeEvaluatorHelper.java

   Class which poses the tool for evaluating correctness of implementation. Hopefully it may help.

   Run from challenge response current folder

   > gradlew clean build

   If everything goes smooth, jar with dependencies will appear in ./build/libs folder
   
   Run sonoma-challenge.jar

   > java -jar sonoma-challenge.jar

   Create input like [95661,95662] [95433,95433] and verify results.

## Code coverage and unit tests
   
   Run from challenge response current folder

   > gradlew clean build
   
   Find code coverage report at ./build/reports/jacoco/test/html/index.html
   Find unit tests report  at ./build/reports/tests/test/index.html


 
   
  



