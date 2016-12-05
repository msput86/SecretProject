package com.secretproject.secretproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by andrew on 25.11.16.
 */

public class MainActivity extends AppCompatActivity {

    public String sNickname;
    private TextView nickNameTextView;
    String resultString = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        nickNameTextView = (TextView) findViewById(R.id.nickNameTextView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();
        Intent intent;
           // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_map:
                intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                intent = new Intent(this, MyPreferenceActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_about:
                intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goAboutClick(View view) {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void goSettingsClick(View view) {
        //Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        //startActivity(intent);

        Intent intent = new Intent(this, MyPreferenceActivity.class);
        startActivity(intent);
    }

    public void goMapClick(View view) {
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);
    }


    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        // читаем установленное значение из
        sNickname = prefs.getString("server_nickname", "Anonimus");
        nickNameTextView.setText(sNickname);

        MyApplication myApp = (MyApplication) getApplication();
        myApp.setParam("server_nickname", sNickname);
        //myApp.getParams()

    }

    public void sayHello(View view) {

        String myURL = "http://andrew-6676.ddns.net:8080/test/test1";
        String params = "param=hello from server";
        byte[] data = null;

        //String resultString = "";
        InputStream is = null;

        try {
            nickNameTextView.setText("wait...");
            URL url = new URL(myURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));
            OutputStream os = conn.getOutputStream();
            data = params.getBytes("UTF-8");
            os.write(data);
            data = null;

            conn.connect();
            int responseCode= conn.getResponseCode();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            is = conn.getInputStream();

            byte[] buffer = new byte[8192]; // Такого вот размера буфер
            // Далее, например, вот так читаем ответ
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            data = baos.toByteArray();
            resultString = new String(data, "UTF-8");
        } catch (Exception e) {
            nickNameTextView.setText("error");
        } finally {
            nickNameTextView.setText("response: "+resultString);
            try {
                if (is != null)
                    is.close();
            } catch (Exception ex) {
                nickNameTextView.setText("error2");
            }
        }




        Toast toast3 = Toast.makeText(getApplicationContext(),
                "Пошёл на хуй!", Toast.LENGTH_LONG);
        toast3.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastContainer = (LinearLayout) toast3.getView();
        ImageView catImageView = new ImageView(getApplicationContext());
        catImageView.setImageResource(R.drawable.fy);
        toastContainer.addView(catImageView, 0);
        toast3.show();
    }


}
