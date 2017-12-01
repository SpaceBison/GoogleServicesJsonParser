package org.spacebison.googleservicesjsonparser.service;

import com.google.common.io.Files;
import com.squareup.javapoet.MethodSpec;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.spacebison.googleservicesjsonparser.AndroidStringResourceParser;
import org.spacebison.googleservicesjsonparser.FirebaseOptionsGenerator;
import org.spacebison.googleservicesjsonparser.GoogleServicesJsonParser;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.utils.IOUtils;
import spark.utils.StringUtils;

public final class Service {
    public static final ServletFileUpload FILE_UPLOAD = new ServletFileUpload(new DiskFileItemFactory(10000, Files.createTempDir()));

    private Service() {
        init();
    }

    public static void init() {
        initIllegalArgumentExceptionHandler();
        //Spark.exception(Exception.class, (exception, request, response) -> response.body(Throwables.getStackTraceAsString(exception)));
        Spark.post("/stringResources", "multipart/form-data", new StringResourcesRoute());
        Spark.post("/firebaseOptions", new FirebaseOptionsRoute());
    }

    private static void initIllegalArgumentExceptionHandler() {
        Spark.exception(IllegalArgumentException.class, (exception, request, response) -> {
            response.status(HttpURLConnection.HTTP_BAD_REQUEST);
            response.body(exception.getMessage() + '\n');
        });
    }

    private static void initLogger() {
        Spark.before((Filter) new LoggerFilter());
    }

    private static class FirebaseOptionsRoute implements Route {
        private static String getFirebaseOptionsBuilderCode(String body) throws IOException, SAXException {
            Map<String, String> firebaseConfiguration = AndroidStringResourceParser.parseStringResXml(body);
            MethodSpec builderMethod = FirebaseOptionsGenerator.getFirebaseOptionsBuilderMethod(firebaseConfiguration);
            return builderMethod.code.toString();
        }

        @Override
        public Object handle(Request request, Response response) throws Exception {
            String stringResXml = request.body();
            if (StringUtils.isBlank(stringResXml)) {
                throw new IllegalArgumentException("Empty body");
            }

            try {
                String code = getFirebaseOptionsBuilderCode(stringResXml);
                response.type("text/x-java-source");
                return code;
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Error parsing xml: " + e.getMessage());
            }
        }
    }

    private static class StringResourcesRoute implements Route {
        private static final String PART_GOOGLE_SERVICES_JSON = "google-services.json";
        private static final String PART_PACKAGE_NAME = "packageName";

        @Override
        public Object handle(Request request, Response response) throws Exception {
            HttpServletRequest rawRequest = request.raw();

            if (!FileUpload.isMultipartContent(new ServletRequestContext(rawRequest))) {
                throw new IllegalArgumentException("Expected multipart request body");
            }

            Map<String, List<FileItem>> parameterMap = FILE_UPLOAD.parseParameterMap(rawRequest);

            if (!parameterMap.containsKey(PART_PACKAGE_NAME)) {
                throw new IllegalArgumentException("Missing packageName part");
            }

            if (!parameterMap.containsKey(PART_GOOGLE_SERVICES_JSON)) {
                throw new IllegalArgumentException("Missing google-services.json part");
            }

            String packageName = parameterMap.get(PART_PACKAGE_NAME).iterator().next().getString();

            File tmpJsonFile = File.createTempFile("google-services", ".json");
            try (OutputStream os = new FileOutputStream(tmpJsonFile)) {
                IOUtils.copy(parameterMap.get(PART_GOOGLE_SERVICES_JSON).iterator().next().getInputStream(), os);
            }

            try {
                String stringResXml = GoogleServicesJsonParser.getGoogleServicesStringResXml(packageName, tmpJsonFile);
                response.header("Content-Type", "application/xml; charset=utf-8");
                return stringResXml;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class LoggerFilter implements Filter {
        @Override
        public void handle(Request request, Response response) throws Exception {
            System.out.println(request.requestMethod() + " " + request.uri());

            for (String header : request.headers()) {
                System.out.println(" " + header + ": " + request.headers(header));
            }

            System.out.println(request.body());
        }
    }
}
