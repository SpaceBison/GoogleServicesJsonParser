package org.spacebison.googleservicesjsonparser;

import com.google.common.io.Files;

import java.io.File;
import java.net.HttpURLConnection;

import spark.ExceptionHandler;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.utils.StringUtils;

public class ApiController {
    public ApiController() {
        Spark.before(new Filter() {
            @Override
            public void handle(Request request, Response response) throws Exception {
                System.out.println(request.requestMethod() + " " + request.uri());

                for (String header : request.headers()) {
                    System.out.println(" " + header + ": " + request.headers(header));
                }

                System.out.println(request.body());
            }
        });
        Spark.post("/parseJson", "application/json", (request, response) -> {
            String packageName = request.queryParams("packageName");
            if (packageName == null) {
                throw new IllegalArgumentException("No packageName specified");
            }

            String body = request.body();
            if (StringUtils.isBlank(body)) {
                throw new IllegalArgumentException("Empty body");
            }

            File tempDir = Files.createTempDir();
            String valuesXml = GoogleServicesJsonParser.parseGoogleServicesJson(packageName, body, tempDir);
            tempDir.delete();

            return valuesXml;
        });

        Spark.exception(IllegalArgumentException.class, new ExceptionHandler<IllegalArgumentException>() {
            @Override
            public void handle(IllegalArgumentException exception, Request request, Response response) {
                response.status(HttpURLConnection.HTTP_BAD_REQUEST);
                response.body(exception.getMessage());
            }
        });
    }
}
