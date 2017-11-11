package org.spacebison.googleservicesjsonparser;

import com.google.common.io.Files;
import com.google.gms.googleservices.GoogleServicesTask;

import java.io.File;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println(getUsageString());
            System.exit(1);
            return;
        }

        final File jsonFile = new File(args[0]);
        final String packageName = args[1];

        if (!jsonFile.exists()) {
            System.err.println("File not found: " + jsonFile.getPath());
        }

        final File tmp = makeTmpDir(null);

        final GoogleServicesTask task = new GoogleServicesTask();
        task.intermediateDir = tmp;
        task.packageName = packageName;
        task.quickstartFile = jsonFile;

        try {
            task.action();
            final File values = new File(tmp.getAbsolutePath() + "/values", "values.xml");
            Files.move(values, new File("google-services-values.xml"));
        } catch (Exception e) {
            System.err.println("Error generating resources: " + e.getMessage());
        }

        deleteFolder(tmp);
    }

    private static File makeTmpDir(File parentFile) {
        if (parentFile == null) {
            parentFile = new File(".");
        }

        StringBuilder sb = new StringBuilder("tmp");
        File tmp;

        do {
            sb.append('_');
            tmp = new File(parentFile, sb.toString());
        } while (tmp.exists());

        tmp.mkdir();

        return tmp;
    }

    private static void deleteFolder(final File folder) {
        if (!folder.exists()) {
            return;
        }

        File[] files = folder.listFiles();
        if (files != null) {
            for (final File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }

        folder.delete();
    }

    private static String getUsageString() {
        return "Usage: googleservicesjsonparser google-services.json package_name";
    }
}
