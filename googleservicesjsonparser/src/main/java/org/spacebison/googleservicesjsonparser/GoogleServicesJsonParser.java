package org.spacebison.googleservicesjsonparser;

import com.google.common.io.Files;
import com.google.gms.googleservices.GoogleServicesTask;

import org.gradle.api.internal.AbstractTask;
import org.gradle.api.internal.project.ProjectInternal;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class GoogleServicesJsonParser {
    public static void parseGoogleServicesJson(String packageName, File googleServicesJsonFile, File outputFile, File tmpDir) {
        final GoogleServicesTask task = getGoogleServicesTask(packageName, googleServicesJsonFile, tmpDir);
        try {
            task.action();
            final File values = new File(tmpDir.getAbsolutePath() + "/values", "values.xml");
            Files.move(values, outputFile);
        } catch (Exception e) {
            System.err.println("Error generating resources");
            e.printStackTrace();
        }
    }

    public static File makeTmpDir(File parentFile) {
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

    public static void deleteDir(final File folder) {
        if (!folder.exists()) {
            return;
        }

        File[] files = folder.listFiles();
        if (files != null) {
            for (final File file : files) {
                if (file.isDirectory()) {
                    deleteDir(file);
                } else {
                    file.delete();
                }
            }
        }

        folder.delete();
    }

    private static GoogleServicesTask getGoogleServicesTask(String packageName, File googleServicesJsonFile, File tmpDir) {
        try {
            Field next_instance = AbstractTask.class.getDeclaredField("NEXT_INSTANCE");
            next_instance.setAccessible(true);
            ThreadLocal<Object> o = (ThreadLocal<Object>) next_instance.get(null);
            Class<?> taskInfoClass = Class.forName("org.gradle.api.internal.AbstractTask$TaskInfo");
            Constructor<?> constructor = taskInfoClass.getDeclaredConstructor(ProjectInternal.class, String.class, Class.class);
            constructor.setAccessible(true);
            Object othreadInfoInstance = constructor.newInstance(new LoggerProject(new PrintStreamLogger()), "name", LimitedGoogleServicesTask.class);
            o.set(othreadInfoInstance);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        final GoogleServicesTask task = new LimitedGoogleServicesTask();

        task.intermediateDir = tmpDir;
        task.packageName = packageName;
        task.quickstartFile = googleServicesJsonFile;
        return task;
    }
}
