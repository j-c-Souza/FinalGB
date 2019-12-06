package com.example.finalgb;

import android.util.Log;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class Firebase {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth useri = FirebaseAuth.getInstance();

    public void logarFireBase(String email, String password){

        //Verifica usuario logado
        if( useri.getCurrentUser() != null ){
            Log.i("CreateUser", "Usuario logado!" );
        }else {
            Log.i("CreateUser", "Usuario nao logado!" );
            cadastrarUser();
        }

        //Logar usuario
        useri.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if( task.isSuccessful() ){
                            Log.i("signIn", "Sucesso ao logar usuario!" );
                        }else {
                            Log.i("signIn", "Erro ao logar usuario!" );
                        }
                    }
                });

    }

    private void cadastrarUser(){
        useri.createUserWithEmailAndPassword("sza@gmail.com", "123456")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if( task.isSuccessful() ){
                            Log.d("CreateUser", "Sucesso ao cadastrar usuario!" );


                        }else {
                            Log.d("CreateUser", "Erro ao cadastrar usuario!" );
                        }
                    }
                });
    }

    public void armazenarLocal(String data, String hora, String lat, String lng){
        Token token = new Token(data, hora, lat, lng);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("posicoes").document().set(token);

    }


    public void logoutUser(){
        useri.signOut();
    }

    public boolean isLogged(){
        return useri.getCurrentUser() != null;
    }



}
