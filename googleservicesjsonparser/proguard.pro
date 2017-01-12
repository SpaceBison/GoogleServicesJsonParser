-libraryjars  <java.home>/lib/rt.jar
-libraryjars  <java.home>/lib/jce.jar

-keepclasseswithmembers public class * {
    public static void main(java.lang.String[]);
}

-dontwarn com.google.errorprone.annotations.**
-dontwarn com.google.common.io.Files