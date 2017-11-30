package org.spacebison.googleservicesjsonparser;

import com.squareup.javapoet.MethodSpec;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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
        File parsedXml = GoogleServicesJsonParser.createGoogleServicesStringResXmlFile("com.google.samples.quickstart.admobexample", mGoogleServicesJsonFile, mTmpDir);
        assertThat(parsedXml, is(anExistingFile()));
    }

    @Test
    public void testParseGoogleServicesJsonWithoutPackageName() throws IOException {
        assumeThat(mGoogleServicesJsonFile, is(anExistingFile()));
        File parsedXml = GoogleServicesJsonParser.createGoogleServicesStringResXmlFile(null, mGoogleServicesJsonFile, mTmpDir);
        assertThat(parsedXml, is(anExistingFile()));
    }

    @Test
    public void testParseGoogleServicesStringResXml() throws IOException, SAXException {
        Map<String, String> firebaseConfig = getFirebaseConfigMap();
        System.out.println(firebaseConfig);
    }

    private Map<String, String> getFirebaseConfigMap() throws IOException, SAXException {
        assumeThat(mGoogleServicesJsonFile, is(anExistingFile()));
        String parsedXml = GoogleServicesJsonParser.getGoogleServicesStringResXml("com.google.samples.quickstart.admobexample", mGoogleServicesJsonFile, mTmpDir);
        assumeThat(parsedXml, is(notNullValue()));

        return AndroidStringResourceParser.parseStringResXml(parsedXml);
    }

    @Test
    public void testGetFirebaseOptionsMethod() throws IOException, SAXException {
        Map<String, String> firebaseConfigMap = getFirebaseConfigMap();
        MethodSpec method = FirebaseOptionsGenerator.getFirebaseOptionsBuilderMethod(firebaseConfigMap);
        System.out.println(method.code.toString());
    }

    @After
    public void tearDown() {
        //mOutputFile.delete();
        mTmpDir.delete();
    }
}
