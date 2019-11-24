package com.example.milanease.ui.login;

import com.example.milanease.core.ui.providers.Provider;

import java.util.List;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;
    private List<Provider> providers;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<Provider> getProviders() {
        return providers;
    }
}
