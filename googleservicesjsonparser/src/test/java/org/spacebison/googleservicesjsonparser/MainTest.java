package org.spacebison.googleservicesjsonparser;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.io.FileMatchers.anExistingFile;
import static org.junit.Assume.assumeThat;

public class MainTest {

    private File mGoogleServicesJsonFile;
    private File mOutputFile;
    private File mTmpDir;

    @Before
    public void setUp() {
        mGoogleServicesJsonFile = new File(getClass().getResource("/mock-google-services.json").getFile());
        mOutputFile = new File("out.xml");
        mTmpDir = new File("tmp");
    }

    @Test
    public void testParseGoogleServicesJson() {
        assumeThat(mGoogleServicesJsonFile, is(anExistingFile()));
        GoogleServicesJsonParser.parseGoogleServicesJson("com.google.samples.quickstart.admobexample", mGoogleServicesJsonFile, mOutputFile, mTmpDir);
        System.out.println(mOutputFile);
        Assert.assertThat(mOutputFile, is(anExistingFile()));
    }

    @After
    public void tearDown() {
        //mOutputFile.delete();
        mTmpDir.delete();
    }
}
