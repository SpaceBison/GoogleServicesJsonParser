package org.gradle.api;

import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.logging.Logger;

/**
 * Created by wmatuszewski on 1/10/17.
 */

public class DefaultTask {
    public Project getProject() {
        return new Project() {
            @Override
            public ConfigurationContainer getConfigurations() {
                return null;
            }

            @Override
            public Logger getLogger() {
                return new Logger() {
                    @Override
                    public void warn(String string) {
                    }
                };
            }
        };
    }
}
