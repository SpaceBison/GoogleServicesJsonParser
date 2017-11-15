package org.spacebison.googleservicesjsonparser;

import com.google.common.io.Files;
import com.google.gms.googleservices.GoogleServicesTask;

import org.gradle.api.internal.AbstractTask;
import org.gradle.api.internal.project.ProjectInternal;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

public class GoogleServicesJsonParser {

    public static final Charset UTF8 = Charset.forName("UTF-8");

    public static File parseGoogleServicesJson(String packageName, File googleServicesJsonFile, File tmpDir) throws IOException {
        final GoogleServicesTask task = getGoogleServicesTask(packageName, googleServicesJsonFile, tmpDir);
        System.out.println(googleServicesJsonFile);
        task.action();
        return new File(new File(tmpDir, "values"), "values.xml");
    }

    public static String parseGoogleServicesJson(String packageName, String googleServicesJson, File tmpDir) throws IOException {
        File googleServicesJsonFile = new File(tmpDir, "google-services.json");

        File valuesXmlFile;
        try {
            Files.write(googleServicesJson, googleServicesJsonFile, UTF8);
            valuesXmlFile = parseGoogleServicesJson(packageName, googleServicesJsonFile, new File(tmpDir, "output"));
        } finally {
            googleServicesJsonFile.delete();
        }

        String valuesXml;
        try {
            valuesXml = Files.toString(valuesXmlFile, UTF8);
        } finally {
            valuesXmlFile.delete();
        }

        return valuesXml;
    }

    private static GoogleServicesTask getGoogleServicesTask(String packageName, File googleServicesJsonFile, File tmpDir) {
        try {
            Field next_instance = AbstractTask.class.getDeclaredField("NEXT_INSTANCE");
            next_instance.setAccessible(true);
            ThreadLocal<Object> o = (ThreadLocal<Object>) next_instance.get(null);
            Class<?> taskInfoClass = Class.forName("org.gradle.api.internal.AbstractTask$TaskInfo");
            Constructor<?> constructor = taskInfoClass.getDeclaredConstructor(ProjectInternal.class, String.class, Class.class);
            constructor.setAccessible(true);
            Object othreadInfoInstance = constructor.newInstance(new NullProjectInternal(), "name", LimitedGoogleServicesTask.class);
            o.set(othreadInfoInstance);
        } catch (NoSuchMethodException | InstantiationException | NoSuchFieldException | InvocationTargetException | ClassNotFoundException | IllegalAccessException e) {
            throw new RuntimeException("Could not get GoogleServicesTask", e);
        }

        final GoogleServicesTask task = new LimitedGoogleServicesTask();

        task.intermediateDir = tmpDir;
        task.packageName = packageName;
        task.quickstartFile = googleServicesJsonFile;
        return task;
    }
}
