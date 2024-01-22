package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Client {
    private final static String BASE_URI = "https://stellarburgers.nomoreparties.site";

    protected static RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URI)
                .build();
    }

    protected static RequestSpecification getSpec(String accessToken) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("authorization", accessToken)
                .setBaseUri(BASE_URI)
                .build();
    }
}
