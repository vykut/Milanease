package com.example.milanease.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milanease.R;
import com.example.milanease.core.MainActivity;
import com.example.milanease.data.UsernameException;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private TextView title;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText passwordRepeatEditText;
    private EditText emailEditText;
    private EditText clientIDEditText;
    private Button loginButton;

    //stop registration process
    private Button backToLoginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();

        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        loginViewModel.getLogin().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    title.setText(R.string.activity_login_login);

                    passwordRepeatEditText.setVisibility(View.GONE);
                    emailEditText.setVisibility(View.GONE);
                    clientIDEditText.setVisibility(View.GONE);
                    backToLoginButton.setVisibility(View.GONE);

                    passwordEditText.removeTextChangedListener(registrationTextChangedListener);
                } else {
                    title.setText(R.string.login_activity_registration_form);
                    loginButton.setEnabled(aBoolean);
                    loginButton.setText(R.string.login_activity_register);
                    backToLoginButton.setVisibility(View.VISIBLE);
                    passwordRepeatEditText.setVisibility(View.VISIBLE);
                    emailEditText.setVisibility(View.VISIBLE);
                    clientIDEditText.setVisibility(View.VISIBLE);

                    passwordEditText.addTextChangedListener(registrationTextChangedListener);

                }
            }
        });

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }

                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
                if (loginFormState.isDataValid())
                    loginButton.setEnabled(loginFormState.isDataValid());
            }
        });

        loginViewModel.getRegisterFormState().observe(this, new Observer<RegisterFormState>() {
            @Override
            public void onChanged(RegisterFormState registerFormState) {
                if (registerFormState == null) {
                    return;
                }
                loginButton.setEnabled(registerFormState.isDataValid());
                if (registerFormState.getPasswordError() != null)
                    passwordRepeatEditText.setError(getString(registerFormState.getPasswordError()));
                if (registerFormState.getEmailError() != null)
                    emailEditText.setError(getString(registerFormState.getEmailError()));
                if (registerFormState.getClientIDError() != null)
                    clientIDEditText.setError(getString(registerFormState.getClientIDError()));
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }

                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError().getLocalizedMessage());
                    if (loginResult.getError() instanceof UsernameException)
                        loginViewModel.startRegistrationProcess();
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                    setResult(Activity.RESULT_OK);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });



    }

    private TextWatcher loginTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // ignore
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // ignore
        }

        @Override
        public void afterTextChanged(Editable s) {
            loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString());
        }
    };

    TextWatcher registrationTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // ignore
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // ignore
        }

        @Override
        public void afterTextChanged(Editable s) {
            loginViewModel.registrationDataChanged(usernameEditText.getText().toString(), passwordEditText.getText().toString(),
                    passwordRepeatEditText.getText().toString(), emailEditText.getText().toString(),
                    clientIDEditText.getText().toString());
        }
    };

    private void initComponents() {
        title = findViewById(R.id.activity_login_title);
        usernameEditText = findViewById(R.id.activity_login_username);
        passwordEditText = findViewById(R.id.activity_login_password);
        loginButton = findViewById(R.id.activity_login_btn_login);
        passwordRepeatEditText = findViewById(R.id.activity_login_password_repeat);
        emailEditText = findViewById(R.id.activity_login_email);
        clientIDEditText = findViewById(R.id.activity_login_client_id);
        backToLoginButton = findViewById(R.id.activity_login_btn_sign_in_instead);

        usernameEditText.addTextChangedListener(loginTextChangedListener);
        passwordEditText.addTextChangedListener(loginTextChangedListener);
        passwordRepeatEditText.addTextChangedListener(registrationTextChangedListener);
        emailEditText.addTextChangedListener(registrationTextChangedListener);
        clientIDEditText.addTextChangedListener(registrationTextChangedListener);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginViewModel.getLogin().getValue())
                    loginViewModel.login(usernameEditText.getText().toString(), passwordEditText.getText().toString(), getApplicationContext());
                else
                    loginViewModel.register(usernameEditText.getText().toString(), passwordEditText.getText().toString(),
                            emailEditText.getText().toString(), clientIDEditText.getText().toString(), getApplicationContext());
            }
        });

        backToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.stopRegistrationProcess();
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(String errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
