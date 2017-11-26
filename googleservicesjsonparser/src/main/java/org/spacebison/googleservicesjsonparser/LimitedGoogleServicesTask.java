package org.spacebison.googleservicesjsonparser;

import com.google.gms.googleservices.GoogleServicesTask;

import org.gradle.api.Project;

public class LimitedGoogleServicesTask extends GoogleServicesTask {

    private Project mProject;

    @Override
    public Project getProject() {
        return mProject;
    }


    public void setProject(Project project) {
        mProject = project;
    }
}
