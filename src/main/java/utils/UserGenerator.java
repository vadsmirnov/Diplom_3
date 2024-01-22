package utils;

import mod.CreateUser;
import mod.LoginUser;

import static utils.DataGenerator.randomString;

public class UserGenerator {
    final static String DEFAULT_EMAIL = randomString(6) + "@" + randomString(4) + ".ru";
    final static String DEFAULT_PASSWORD = randomString(8);
    final static String DEFAULT_NAME = randomString(5);

    public static CreateUser getDefaultRegistrationData() {
        return new CreateUser(DEFAULT_EMAIL, DEFAULT_PASSWORD, DEFAULT_NAME);
    }

    public static LoginUser getDefaultLoginData() {
        return new LoginUser(DEFAULT_EMAIL, DEFAULT_PASSWORD, DEFAULT_NAME);
    }
}