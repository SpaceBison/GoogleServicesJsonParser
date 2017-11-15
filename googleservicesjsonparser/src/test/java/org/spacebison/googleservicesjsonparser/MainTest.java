package org.spacebison.googleservicesjsonparser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.io.FileMatchers.anExistingFile;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

public class MainTest {

    private File mGoogleServicesJsonFile;
    private File mTmpDir;

    @Before
    public void setUp() {
        mGoogleServicesJsonFile = new File(getClass().getResource("/mock-google-services.json").getFile());
        mTmpDir = new File("tmp");
    }

    @Test
    public void testParseGoogleServicesJson() throws IOException {
        assumeThat(mGoogleServicesJsonFile, is(anExistingFile()));
        File parsedXml = GoogleServicesJsonParser.parseGoogleServicesJson("com.google.samples.quickstart.admobexample", mGoogleServicesJsonFile, mTmpDir);
        assertThat(parsedXml, is(anExistingFile()));
    }

    @After
    public void tearDown() {
        //mOutputFile.delete();
        mTmpDir.delete();
    }
}
