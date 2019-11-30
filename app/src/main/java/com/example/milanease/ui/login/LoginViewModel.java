package com.example.milanease.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.content.Context;
import android.util.Patterns;

import com.example.milanease.data.LoginRepository;
import com.example.milanease.data.Result;
import com.example.milanease.data.model.LoggedInUser;
import com.example.milanease.R;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private MutableLiveData<Boolean> login = new MutableLiveData<>();
    private MutableLiveData<RegisterFormState> registerFormState = new MutableLiveData<>();

    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
        this.login.setValue(true);
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    public LiveData<Boolean> getLogin() {
        return login;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public LiveData<RegisterFormState> getRegisterFormState() {
        return registerFormState;
    }

    public void login(String username, String password, Context context) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password, context);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(((Result.Error) result).getError()));
        }
    }

    public void register(String username, String password, String email, String clientID, Context context) {
        Result<LoggedInUser> result = loginRepository.register(username, password, email, clientID, context);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(((Result.Error) result).getError()));
        }
    }

    public void loginDataChanged(String username, String password) {
        if (!isFieldValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isFieldValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    public void startRegistrationProcess() {
        login.setValue(false);
    }

    public void stopRegistrationProcess() {
        login.setValue(true);
    }

    public void registrationDataChanged(String username, String password, String passwordRepeated, String email, String clientID) {
        if (!passwordsMatch(password, passwordRepeated))
            registerFormState.setValue(new RegisterFormState(R.string.invalid_email, null, null));
        else if (!isUserEmailValid(email))
            registerFormState.setValue(new RegisterFormState(null, R.string.passwords_dont_match, null));
        else if (!isClientIDValid(clientID))
            registerFormState.setValue(new RegisterFormState(null, null, R.string.invalid_client_id));
        else
            registerFormState.setValue(new RegisterFormState(true));
    }

    private boolean isClientIDValid(String clientID) {
        // test if number keyboard can insert other characters
        return clientID != null && clientID.length() == 8;
    }

    private boolean passwordsMatch(String password, String passwordRepeated) {
        return password != null && password.equals(passwordRepeated);
    }

    // A placeholder username validation check
    private boolean isUserEmailValid(String email) {
        if (email == null) {
            return false;
        }
        if (email.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        } else {
            return !email.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isFieldValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
