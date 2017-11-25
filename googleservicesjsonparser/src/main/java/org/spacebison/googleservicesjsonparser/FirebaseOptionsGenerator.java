package org.spacebison.googleservicesjsonparser;

import com.google.common.collect.ImmutableMap;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;

import java.util.Map;

public final class FirebaseOptionsGenerator {
    public static final String KEY_DEFAULT_WEB_CLIENT_ID = "default_web_client_id";
    public static final String KEY_GOOGLE_APP_ID = "google_app_id";
    public static final String KEY_GCM_DEFAULT_SENDER_ID = "gcm_defaultSenderId";
    public static final String KEY_PROJECT_ID = "project_id";
    public static final String KEY_GOOGLE_API_KEY = "google_api_key";
    public static final String KEY_GOOGLE_CRASH_REPORTING_API_KEY = "google_crash_reporting_api_key";
    public static final String KEY_FIREBASE_DATABASE_URL = "firebase_database_url";

    private static final Map<String, String> SETTER_NAMES =
            ImmutableMap.<String, String>builder()
                    .put(KEY_GOOGLE_API_KEY, "setApiKey")
                    .put(KEY_GOOGLE_APP_ID, "setApplicationId")
                    .put(KEY_FIREBASE_DATABASE_URL, "setDatabaseUrl")
                    .put(KEY_GCM_DEFAULT_SENDER_ID, "setGcmSenderId")
                    .put(KEY_PROJECT_ID, "setProjectId")
                    .build();
    private static final String INDENT = "    ";

    private FirebaseOptionsGenerator() {
    }

    public static MethodSpec getFirebaseOptionsBuilderMethod(Map<String, String> firebaseConfiguration) {
        MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder("getFirebaseOptions")
                .returns(ClassName.get("com.google.firebase", "FirebaseOptions"))
                .addCode("return new FirebaseOptions.Builder()\n");

        firebaseConfiguration.forEach((k,v) -> {
            if (SETTER_NAMES.containsKey(k)) {
                methodSpecBuilder.addCode("$L.$L($S)\n", INDENT, SETTER_NAMES.get(k), v);
            }
        });

        return methodSpecBuilder
                .addStatement("$L.build()", INDENT).build();
    }
}
