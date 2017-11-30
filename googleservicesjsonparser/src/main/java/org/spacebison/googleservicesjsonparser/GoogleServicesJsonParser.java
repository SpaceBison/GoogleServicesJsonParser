package org.spacebison.googleservicesjsonparser;

import com.google.common.io.Files;
import com.google.gms.googleservices.GoogleServicesTask;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import org.objenesis.ObjenesisStd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

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

    private static GoogleServicesTask getGoogleServicesTask(String packageName, File googleServicesJsonFile, File tmpDir) throws IOException {
        final LimitedGoogleServicesTask task = new ObjenesisStd().getInstantiatorOf(LimitedGoogleServicesTask.class).newInstance();
        task.setProject(new LoggerProject(new PrintStreamLogger()));
        task.intermediateDir = tmpDir;
        task.packageName = packageName;
        task.quickstartFile = googleServicesJsonFile;
        return task;
    }

    private static String findPackageName(File googleServicesJsonFile) throws IOException {
        JsonObject jsonObject = new Gson().fromJson(new BufferedReader(new FileReader(googleServicesJsonFile)), JsonObject.class);
        return findInJson(jsonObject, "package_name");
    }

    private static String findInJson(JsonElement element, String name) {
        if (!element.isJsonObject()) {
            return null;
        }

        JsonObject jsonObject = element.getAsJsonObject();

        JsonElement jsonElement = jsonObject.get(name);
        if (jsonElement != null && jsonElement.isJsonPrimitive()) {
            JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
            if (jsonPrimitive.isString()) {
                return jsonPrimitive.getAsString();
            }
        }

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String found = findInJson(entry.getValue(), name);
            if (found != null) {
                return found;
            }
        }

        return null;
    }
}
