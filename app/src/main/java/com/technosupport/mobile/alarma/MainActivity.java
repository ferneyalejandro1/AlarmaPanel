package com.technosupport.mobile.alarma;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
    }

    public void pressedButton (View view){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String numero = sharedPreferences.getString(SettingsActivity.NUMBER_TO_SEND, "");
        String mensaje = "";

        switch (view.getId()){
            case R.id.find:
                //mensaje = "fix0305002n123456";
                mensaje = sharedPreferences.getString(SettingsActivity.FIND_CODE, "");
                composeMessage(mensaje, numero);
                break;

            case R.id.open:
                //mensaje = "disarm123456";
                mensaje = sharedPreferences.getString(SettingsActivity.OPEN_CODE, "");
                composeMessage(mensaje, numero);
                break;

            case R.id.close:
                //mensaje = "arm123456";
                mensaje = sharedPreferences.getString(SettingsActivity.CLOSE_CODE, "");
                composeMessage(mensaje, numero);
                break;

            case R.id.on:
                //mensaje = "resume123456";
                mensaje = sharedPreferences.getString(SettingsActivity.ON_CODE, "");
                composeMessage(mensaje, numero);
                break;

            case R.id.off:
                //mensaje = "stop123456";
                mensaje = sharedPreferences.getString(SettingsActivity.OFF_CODE, "");
                composeMessage(mensaje, numero);
                break;
        }

    }

    public void composeMessage(String message, String number){
        if (message != "" && number != "" && message != null && number != null){
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + number));
            intent.putExtra("sms_body", message);
            if (intent.resolveActivity(getPackageManager()) != null ){
                startActivity(intent);
            } else
                Toast.makeText(MainActivity.this, "No se detecta programa de mensajería, contactese con soporte", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(MainActivity.this, "Configure correctamente los números y códigos de la aplicación", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.keyboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.keyboardMenu :
                showSettings();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}
