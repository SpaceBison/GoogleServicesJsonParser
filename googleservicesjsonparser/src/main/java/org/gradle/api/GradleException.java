package org.gradle.api;

/**
 * Created by wmatuszewski on 1/10/17.
 */

public class GradleException extends Exception {
    public GradleException() {
    }

    public GradleException(String s) {
        super(s);
    }

    public GradleException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public GradleException(Throwable throwable) {
        super(throwable);
    }

    public GradleException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
