package org.spacebison.googleservicesjsonparser.service;

import com.squareup.javapoet.MethodSpec;

import org.spacebison.googleservicesjsonparser.AndroidStringResourceParser;
import org.spacebison.googleservicesjsonparser.FirebaseOptionsGenerator;
import org.spacebison.googleservicesjsonparser.GoogleServicesJsonParser;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.utils.IOUtils;
import spark.utils.StringUtils;

public final class Service {
    private Service() {
        init();
    }

    public static void init() {
        initLogger();
        initIllegalArgumentExceptionHandler();
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

        private static String getFirebaseOptionsBuilderCode(String body) throws IOException, SAXException {
            Map<String, String> firebaseConfiguration = AndroidStringResourceParser.parseStringResXml(body);
            MethodSpec builderMethod = FirebaseOptionsGenerator.getFirebaseOptionsBuilderMethod(firebaseConfiguration);
            return builderMethod.code.toString();
        }
    }

    private static class StringResourcesRoute implements Route {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            HttpServletRequest servletRequest = getMultipartHttpServletRequest(request);

            Part googleServicesJsonPart = servletRequest.getPart("google-services.json");
            if (googleServicesJsonPart == null) {
                throw new IllegalArgumentException("Missing google-services.json part");
            }

            Part packageNamePart = servletRequest.getPart("packageName");
            if (packageNamePart == null) {
                throw new IllegalArgumentException("Missing packageName part");
            }

            String packageName = IOUtils.toString(packageNamePart.getInputStream());
            String googleServicesJson = IOUtils.toString(googleServicesJsonPart.getInputStream());

            try {
                String stringResXml = GoogleServicesJsonParser.getGoogleServicesStringResXml(packageName, googleServicesJson);

                response.header("Content-Type", "application/xml; charset=utf-8");

                return stringResXml;
            } catch (Exception e) {
                return null;
            }
        }
    }

    private static HttpServletRequest getMultipartHttpServletRequest(Request request) {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(System.getProperty("java.io.tmpdir"));
        HttpServletRequest servletRequest = request.raw();
        servletRequest.setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
        return servletRequest;
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
