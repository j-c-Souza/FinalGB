package com.example.finalgb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class RelatorioActivity extends AppCompatActivity {
    private LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Token tkn;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        ll = findViewById(R.id.LinearId);
        getAll();
    }


    private void fillTview(TextView tview, Token token){
        tview.setText(
                "Data: "+token.getData()+"||"+
                        "Hora: "+token.getHora()+"||"+
                        "Latitude: "+token.getLat()+"||"+
                        "Longitude: "+token.getLgn()
        );
    }

    private void getAll( ){
        MainActivity.fire.db.collection("posicoes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                TextView tview = new TextView(RelatorioActivity.this);
                                fillTview(tview, document.toObject(Token.class));
                                ll.addView(tview);
                            }
                        } else {

                        }
                    }
                });
    }

}
