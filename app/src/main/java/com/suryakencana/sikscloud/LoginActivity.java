package com.suryakencana.sikscloud;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends Activity  {

    private Button login;
    private Intent a;
    private EditText email, password;
    private String url, success;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        session = new SessionManager(getApplicationContext());
        Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG)
                .show();

        login = (Button) findViewById(com.suryakencana.sikscloud.R.id.login);
        email = (EditText) findViewById(com.suryakencana.sikscloud.R.id.email);
        password = (EditText) findViewById(com.suryakencana.sikscloud.R.id.password);

        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                url = "http://sikscloud.surya-kencana.com/login.php?" + "email="
                        + email.getText().toString() + "&password="
                        + password.getText().toString();

                if (email.getText().toString().trim().length() > 0
                        && password.getText().toString().trim().length() > 0)
                {
                    new Masuk().execute();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Username/password masih kosong gan.!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public class Masuk extends AsyncTask<String, String, String>
    {
        ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Tunggu Bentar ya...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... arg0) {
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            pDialog.dismiss();
            if (success.equals("1")) {
                a = new Intent(LoginActivity.this, SiksCloud.class);
                startActivity(a);
                finish();
            } else {

                Toast.makeText(getApplicationContext(), "Username/password salah gan.!!", Toast.LENGTH_LONG).show();
            }

        }

    }

}