package org.spacebison.googleservicesjsonparser;

import org.gradle.api.logging.Logger;

class LoggerProject extends NullProject {
    private final Logger mLogger;

    LoggerProject(Logger logger) {
        mLogger = logger;
    }

    @Override
    public Logger getLogger() {
        return mLogger;
    }

}
