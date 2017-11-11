package org.spacebison.googleservicesjsonparser;

import com.google.gms.googleservices.GoogleServicesTask;

import org.gradle.api.Project;
import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.logging.Logger;

public class PortableGoogleServicesTask extends GoogleServicesTask {
    @Override
    public Project getProject() {
        return new Project() {
            @Override
            public ConfigurationContainer getConfigurations() {
                return null;
            }

            @Override
            public Logger getLogger() {
                return System.out::println;
            }
        };
    }
}
