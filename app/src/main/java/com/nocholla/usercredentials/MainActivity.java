package com.nocholla.usercredentials;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Widgets
    private EditText inputUsername;
    private EditText inputPassword;
    private Button btnLogin;

    /**
     * @method onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Begin Lock Screen Orientation for Phones to Portrait Mode
        if (!isTablet()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        // Begin Lock Screen Orientation for Tablets to Landscape Mode
        if (isTablet()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        // Widgets
        inputUsername = findViewById(R.id.username);
        inputPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);

        // Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = inputUsername.getText().toString();
                Log.d("DEBUG USERNAME", username);

                final String password = inputPassword.getText().toString();
                Log.d("DEBUG PASSWORD", password);

                // Username = edureka & Password = edureka123" Validation
                if(username.matches("edureka") && password.matches("edureka123\"")) {
                    // Toast On Success
                    Toast.makeText(MainActivity.this, getString(R.string.toast_success), Toast.LENGTH_SHORT).show();

                    // Disable Button On Success
                    btnLogin.setEnabled(false);
                }

                // Wrong Username
                if(!username.matches("edureka")) {
                    inputUsername.setError(getString(R.string.error_wrong_username));
                }

                // Wrong Password
                if(!password.matches("edureka123\"")) {
                    inputPassword.setError(getString(R.string.error_wrong_password));
                }

                // Username Empty Validation
                if (TextUtils.isEmpty(username)) {
                    inputUsername.setError(getString(R.string.error_enter_username));

                    return;
                }

                // Password Empty Validation
                if (TextUtils.isEmpty(password)) {
                    inputPassword.setError(getString(R.string.error_enter_password));

                    return;
                }
            }
        });


    }

    /**
     * Set Screen Orientation
     */
    private boolean isTablet() {
        return (this.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
