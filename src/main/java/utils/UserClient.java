package utils;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import mod.CreateUser;
import mod.LoginUser;

import static io.restassured.RestAssured.given;



public class UserClient extends Client {

    private final static String REGISTER_API="api/auth/register";
    private final static String LOGIN_USER_API = "api/auth/login";
    private final static String UPDATE_USER_API ="api/auth/user";

    @Step("Создание пользователя")
    public static ValidatableResponse createUser(CreateUser data) {
        return given()
                .spec(Client.getSpec())
                .body(data)
                .log().all()
                .when()
                .post(REGISTER_API)
                .then()
                .log().all();

    }
    @Step("Вход пользователя")
    public static ValidatableResponse loginUser(LoginUser data) {
        return given()
                .spec(Client.getSpec())
                .body(data)
                .log().all()
                .when()
                .post(LOGIN_USER_API)
                .then()
                .log().all();

    }

    @Step("Удаление пользователя")
    public static ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(Client.getSpec(accessToken))
                .when()
                .log().all()
                .delete(UPDATE_USER_API)
                .then()
                .log().all();

    }

}