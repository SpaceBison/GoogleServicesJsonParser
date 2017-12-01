package org.spacebison.googleservicesjsonparser.service;

import spark.Spark;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class Main {
    public static void main(String[] args) {
        Spark.port(8080);
        Service.init();
        System.out.println("Listening on port " + Spark.port());
    }
}
