package org.spacebison.googleservicesjsonparser;

import com.google.common.io.Files;
import com.google.gms.googleservices.GoogleServicesTask;

import org.objenesis.ObjenesisStd;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class GoogleServicesJsonParser {
    public static final Charset UTF_8 = StandardCharsets.UTF_8;

    private GoogleServicesJsonParser() {
    }

    public static File createGoogleServicesStringResXmlFile(String packageName, File googleServicesJsonFile, File tmpDir) throws IOException {
        final GoogleServicesTask task = getGoogleServicesTask(packageName, googleServicesJsonFile, tmpDir);
        System.out.println(googleServicesJsonFile);
        task.action();
        return new File(new File(tmpDir, "values"), "values.xml");
    }

    public static String getGoogleServicesStringResXml(String packageName, File googleServicesJsonFile, File tmpDir) throws IOException {
        File valuesXmlFile = createGoogleServicesStringResXmlFile(packageName, googleServicesJsonFile, tmpDir);

        try {
            return Files.toString(valuesXmlFile, UTF_8);
        } finally {
            valuesXmlFile.delete();
        }
    }

    public static String getGoogleServicesStringResXml(String packageName, String googleServicesJson, File tmpDir) throws IOException {
        File valuesXmlFile = createGoogleServicesStringResXmlFile(packageName, googleServicesJson, tmpDir);

        try {
            return Files.toString(valuesXmlFile, UTF_8);
        } finally {
            valuesXmlFile.delete();
        }
    }

    public static String getGoogleServicesStringResXml(String packageName, String googleServicesJson) throws IOException {
        File tempDir = Files.createTempDir();

        try {
            return getGoogleServicesStringResXml(packageName, googleServicesJson, tempDir);
        } finally {
            tempDir.delete();
        }
    }

    public static File createGoogleServicesStringResXmlFile(String packageName, String googleServicesJson) throws IOException {
        File tempDir = Files.createTempDir();

        try {
            return createGoogleServicesStringResXmlFile(packageName, googleServicesJson, tempDir);
        } finally {
            tempDir.delete();
        }
    }


    public static File createGoogleServicesStringResXmlFile(String packageName, String googleServicesJson, File tmpDir) throws IOException {
        File googleServicesJsonFile = new File(tmpDir, "google-services.json");

        try {
            Files.write(googleServicesJson, googleServicesJsonFile, UTF_8);
            return createGoogleServicesStringResXmlFile(packageName, googleServicesJsonFile, new File(tmpDir, "output"));
        } finally {
            googleServicesJsonFile.delete();
        }
    }

    private static GoogleServicesTask getGoogleServicesTask(String packageName, File googleServicesJsonFile, File tmpDir) {
        final LimitedGoogleServicesTask task = new ObjenesisStd().getInstantiatorOf(LimitedGoogleServicesTask.class).newInstance();
        task.setProject(new LoggerProject(new PrintStreamLogger()));
        task.intermediateDir = tmpDir;
        task.packageName = packageName;
        task.quickstartFile = googleServicesJsonFile;
        return task;
    }
}
