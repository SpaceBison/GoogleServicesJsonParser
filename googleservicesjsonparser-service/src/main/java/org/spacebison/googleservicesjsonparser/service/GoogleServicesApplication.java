package org.spacebison.googleservicesjsonparser.service;

import spark.servlet.SparkApplication;

public class GoogleServicesApplication implements SparkApplication {
    @Override
    public void init() {
        Service.init();
    }
}
