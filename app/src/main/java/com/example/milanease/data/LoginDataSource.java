package com.example.milanease.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.milanease.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public static String USER_CREDENTIALS = "user_vault";
    public static String USERNAME = "user_name";
    public static String PASSWORD = "password";
    public static String EMAIL = "email";
    public static String CLIENT_ID = "client_id";

    public Result<LoggedInUser> login(String username, String password, Context context) {

        SharedPreferences sharedPref = context.getSharedPreferences(USER_CREDENTIALS, Context.MODE_PRIVATE);

        try {
            int savedUsername = sharedPref.getInt(USERNAME, 0);
            if (savedUsername != username.hashCode())
                throw new UsernameException();
            int savedPassword = sharedPref.getInt(PASSWORD,0);
            if (password.hashCode() != savedPassword)
                throw new PasswordException();
            String email = sharedPref.getString(EMAIL, "default@email.com");
            String clientID = sharedPref.getString(CLIENT_ID, "00000000");

            LoggedInUser user = new LoggedInUser(username, email, clientID);
            return new Result.Success<>(user);
        } catch (Exception e) {
            return new Result.Error(e);
        }
    }

    public Result<LoggedInUser> register(String username, String password, String email, String clientID, Context context) {

        // save user data on disk
        SharedPreferences sharedPref = context.getSharedPreferences(USER_CREDENTIALS, Context.MODE_PRIVATE);

        try {
            int savedUsername = sharedPref.getInt(USERNAME, 0);
            if (savedUsername == username.hashCode())
                throw new UsernameException();
            sharedPref.edit().putInt(USERNAME, username.hashCode()).apply();
            sharedPref.edit().putInt(PASSWORD, password.hashCode()).apply();
            sharedPref.edit().putString(EMAIL, email).apply();
            sharedPref.edit().putString(CLIENT_ID, clientID).apply();

            LoggedInUser user = new LoggedInUser(username, email, clientID);
            return new Result.Success<>(user);
        } catch (Exception e) {
            return new Result.Error(e);
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
