package com.lopsa.platzigram;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnLongClick;

public class createaccountActivity extends AppCompatActivity {

    @BindView(R.id.txtcorreo)   TextInputEditText txtCorreo;
    @BindView(R.id.txtnombre)   TextInputEditText txtNombre;
    @BindView(R.id.txtusuario)   TextInputEditText txtUser;
    @BindView(R.id.txtcontrasena)   TextInputEditText txtContrasena;
    @BindView(R.id.txtconfircontrasena)   TextInputEditText txtConfirContrasena;
    @BindView(R.id.btncreateaccount)   Button btnCreateAcccount;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);
        ButterKnife.bind(this);

        firebaseAuth=FirebaseAuth.getInstance();


      //  Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        showToolbar(getResources().getString(R.string.toolbar_title),true);


    }

    @OnClick(R.id.btncreateaccount) void crearCuenta(){

        String email=txtCorreo.getText().toString().trim();
        String name=txtNombre.getText().toString().trim();
        String userName=txtUser.getText().toString().trim();
        String password=txtContrasena.getText().toString().trim();
        String passwordconfirmation=txtConfirContrasena.getText().toString().trim();
        if (email.equals("")){
            Toast.makeText(this, " porfavor Ingrese Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(passwordconfirmation)){
            if (password.equals("")){
                Toast.makeText(this, "Passwor Invalid", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        Toast.makeText(this, "el password es igual", Toast.LENGTH_SHORT).show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(createaccountActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(createaccountActivity.this, "Auth failed"+task.getException(), Toast.LENGTH_SHORT).show();
                }
                else {

                    startActivity(new Intent(createaccountActivity.this,LoginActivity.class));
                    finish();

                }
            }
        });

    }

    //metodo que activa el boton atras
    private void showToolbar(String title, boolean upbutton ){
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upbutton);
    }
}
