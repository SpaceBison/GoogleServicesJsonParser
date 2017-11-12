package org.spacebison.googleservicesjsonparser;

import com.google.gms.googleservices.GoogleServicesTask;

import org.gradle.api.Project;

public class LimitedGoogleServicesTask extends GoogleServicesTask {

    private Project mProject = new LoggerProject(new PrintStreamLogger());

    @Override
    public Project getProject() {
        return mProject;
    }

}
