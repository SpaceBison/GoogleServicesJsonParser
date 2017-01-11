package org.spacebison.googleservicesjsonparser;

import com.google.common.io.Files;
import com.google.gms.googleservices.GoogleServicesTask;

import java.io.File;
import java.io.IOException;

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

        final GoogleServicesTask task = new GoogleServicesTask();
        final File tmp = makeTmpDir(jsonFile.getAbsoluteFile().getParentFile());
        task.intermediateDir = tmp;
        task.packageName = packageName;
        task.quickstartFile = jsonFile;

        try {
            task.action();
            final File values = new File(tmp.getAbsolutePath() + "/values", "values.xml");
            Files.move(values, new File(tmp.getParentFile(), "google-services-values.xml"));
        } catch (IOException e) {
            System.err.println("Error generating resoureces: " + e.getMessage());
        } finally {
            deleteFolder(tmp);
        }
    }

    private static File makeTmpDir(File parentFile) {
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
