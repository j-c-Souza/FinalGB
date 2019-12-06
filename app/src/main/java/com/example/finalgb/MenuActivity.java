package com.example.finalgb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MenuActivity extends AppCompatActivity {
    private Gps gps = new Gps(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button cadPos  = (Button) findViewById(R.id.btnCadLoc);
        Button exibR  = (Button) findViewById(R.id.btnExibRel);
        Button logout  = (Button) findViewById(R.id.btnLogOut);


        cadPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps.atualizar();
            }
        });
        exibR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RelatorioActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fire.logoutUser();
                finish();
            }
        });

        pedirPermissoes();
        gps.configurarServico();
    }

    private void pedirPermissoes() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }


    }
}
