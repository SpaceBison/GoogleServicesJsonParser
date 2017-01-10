package org.gradle.api;

import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.logging.Logger;

/**
 * Created by wmatuszewski on 1/10/17.
 */

public interface Project {
    ConfigurationContainer getConfigurations();
    Logger getLogger();
}
