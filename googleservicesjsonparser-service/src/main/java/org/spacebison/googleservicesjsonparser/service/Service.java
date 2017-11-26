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
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.utils.StringUtils;

public final class Service {
    private Service() {
        init();
    }

    public static void init() {
        initLogger();
        initIllegalArgumentExceptionHandler();
        Spark.post("/stringResources", new StringResourcesRoute());
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
        @Override
        public Object handle(Request request, Response response) throws Exception {
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
        }

        private static String getFirebaseOptionsBuilderCode(String body) throws IOException, SAXException {
            Map<String, String> firebaseConfiguration = AndroidStringResourceParser.parseStringResXml(body);
            MethodSpec builderMethod = FirebaseOptionsGenerator.getFirebaseOptionsBuilderMethod(firebaseConfiguration);
            return builderMethod.code.toString();
        }
    }

    private static class StringResourcesRoute implements Route {
        @Override
        public Object handle(Request request, Response response) throws Exception {
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
