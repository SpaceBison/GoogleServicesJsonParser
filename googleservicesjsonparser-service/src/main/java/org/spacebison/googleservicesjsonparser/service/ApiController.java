package org.spacebison.googleservicesjsonparser.service;

import com.squareup.javapoet.MethodSpec;

import org.spacebison.googleservicesjsonparser.AndroidStringResourceParser;
import org.spacebison.googleservicesjsonparser.FirebaseOptionsGenerator;
import org.spacebison.googleservicesjsonparser.GoogleServicesJsonParser;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import spark.Filter;
import spark.Spark;
import spark.utils.StringUtils;

public class ApiController {
    public ApiController() {
        //Spark.initExceptionHandler(Throwable::printStackTrace);
        setupLogger();
        setupIllegalArgumentExceptionHandler();
        stringResources();
        firebaseOptions();
    }

    private static String getFirebaseOptionsBuilderCode(String body) throws IOException, SAXException {
        Map<String, String> firebaseConfiguration = AndroidStringResourceParser.parseStringResXml(body);
        MethodSpec builderMethod = FirebaseOptionsGenerator.getFirebaseOptionsBuilderMethod(firebaseConfiguration);
        return builderMethod.code.toString();
    }

    private void firebaseOptions() {
        Spark.post("/firebaseOptions", "text/x-java-source", (request, response) -> {
            String body = request.body();
            if (StringUtils.isBlank(body)) {
                throw new IllegalArgumentException("Empty body");
            }

            String stringResXml = body;

            String contentType = request.headers("Content-Type");
            if ("application/json".equals(contentType)) {
                String packageName = request.queryParams("packageName");
                if (StringUtils.isBlank(packageName)) {
                    throw new IllegalArgumentException("No packageName specified");
                }

                stringResXml = GoogleServicesJsonParser.getGoogleServicesStringResXml(packageName, body);
            }

            try {
                String code = getFirebaseOptionsBuilderCode(stringResXml);
                response.type("text/x-java-source");
                return code;
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Error parsing xml: " + e.getMessage());
            }
        });
    }

    private void setupIllegalArgumentExceptionHandler() {
        Spark.exception(IllegalArgumentException.class, (exception, request, response) -> {
            response.status(HttpURLConnection.HTTP_BAD_REQUEST);
            response.body(exception.getMessage() + '\n');
        });
    }

    private void stringResources() {
        Spark.post("/stringResources", "application/json", (request, response) -> {
            String packageName = request.queryParams("packageName");
            if (packageName == null) {
                throw new IllegalArgumentException("No packageName specified");
            }

            String body = request.body();
            if (StringUtils.isBlank(body)) {
                throw new IllegalArgumentException("Empty body");
            }

            try {
                String stringResXml = GoogleServicesJsonParser.getGoogleServicesStringResXml(packageName, body);

                response.header("Content-Type", "application/xml; charset=utf-8");

                return stringResXml;
            } catch (Exception e) {
                return null;
            }
        });
    }

    private void setupLogger() {
        Spark.before((Filter) (request, response) -> {
            System.out.println(request.requestMethod() + " " + request.uri());

            for (String header : request.headers()) {
                System.out.println(" " + header + ": " + request.headers(header));
            }

            System.out.println(request.body());
        });
    }
}
