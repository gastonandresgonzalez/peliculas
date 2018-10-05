package ort.edu.org.pelisapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import database.DatabaseHelper;
import model.User;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity{

    private DatabaseHelper mDBHelper;
    User usuario;
    EditText user_email;
    EditText user_passwd;
    Button blogin;

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login );

        mDBHelper = new DatabaseHelper(this);

        //Check exists database
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(false == database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        blogin = findViewById(R.id.email_sign_in_button);
        user_email = findViewById(R.id.tv_email);
        user_passwd = findViewById(R.id.tv_password);


        blogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                usuario = new User(user_email.getText().toString(), user_passwd.getText().toString());

                if (mDBHelper.checkUser(usuario)) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong Login", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }



}

