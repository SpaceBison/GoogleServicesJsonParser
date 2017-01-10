package org.gradle.internal.nativeintegration.console;

/**
 * Created by wmatuszewski on 1/10/17.
 */

public interface ConsoleMetaData {
    /**
     * Returns true if the current process' stdout is attached to the console.
     */
    boolean isStdOut();

    /**
     * Returns true if the current process' stderr is attached to the console.
     */
    boolean isStdErr();

    /**
     * <p>Returns the number of columns available in the console.</p>
     *
     * @return The number of columns available in the console. If no information is available return 0.
     */
    int getCols();
}
