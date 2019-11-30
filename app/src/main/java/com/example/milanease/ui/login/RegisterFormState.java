package com.example.milanease.ui.login;

import androidx.annotation.Nullable;

public class RegisterFormState {

    @Nullable
    private Integer passwordError;
    @Nullable
    private Integer emailError;
    @Nullable
    private Integer clientIDError;
    private boolean isDataValid;

    public RegisterFormState(@Nullable Integer passwordError, @Nullable Integer emailError, @Nullable Integer clientIDError) {
        this.passwordError = passwordError;
        this.emailError = emailError;
        this.clientIDError = clientIDError;
    }

    public RegisterFormState(boolean isDataValid) {
        passwordError = null;
        emailError = null;
        clientIDError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    public Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    public Integer getEmailError() {
        return emailError;
    }

    @Nullable
    public Integer getClientIDError() {
        return clientIDError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }
}
