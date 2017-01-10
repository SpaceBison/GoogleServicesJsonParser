package org.spacebison.googleservicesjsonparser;

import com.google.common.io.Files;
import com.google.gms.googleservices.GoogleServicesTask;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final GoogleServicesTask task = new GoogleServicesTask();
        final File jsonFile = new File(args[0]);
        final File tmp = new File(jsonFile.getAbsoluteFile().getParentFile(), "tmp");
        tmp.mkdir();
        task.intermediateDir = tmp;
        task.packageName = args[1];
        task.quickstartFile = jsonFile;
        task.action();
        final File values = new File(tmp.getAbsolutePath() + "/values", "values.xml");
        Files.move(values, new File(tmp.getParentFile(), "google-services-values.xml"));
        deleteFolder(tmp);
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
}
