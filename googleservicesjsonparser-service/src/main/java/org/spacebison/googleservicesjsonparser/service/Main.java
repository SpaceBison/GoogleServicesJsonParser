package org.spacebison.googleservicesjsonparser.service;

import spark.Spark;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class Main {
    public static void main(String[] args) {
        Spark.port(6666);
        new ApiController();
        System.out.println("Listening on port " + Spark.port());
/*        if (args.length != 2) {
            System.err.println(getUsageString());
            System.exit(1);
            return;
        }

        final File jsonFile = new File(args[0]);
        final String packageName = args[1];

        if (!jsonFile.exists()) {
            System.err.println("File not found: " + jsonFile.getPath());
        }

        File tmpDir = GoogleServicesJsonParser.makeTmpDir(null);
        String parsed = GoogleServicesJsonParser.createGoogleServicesStringResXmlFile(packageName, jsonFile, tmpDir);
        GoogleServicesJsonParser.deleteDir(tmpDir);

        System.out.println(parsed);*/
    }

    private static String getUsageString() {
        return "Usage: googleservicesjsonparser google-services.json package_name";
    }

}
