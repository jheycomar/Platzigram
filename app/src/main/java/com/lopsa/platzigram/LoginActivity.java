package com.lopsa.platzigram;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends  android.support.v7.app.AppCompatActivity  {

    private Button btnlogin;
    @BindView(R.id.txtuser)
    TextInputEditText etemail;
    @BindView(R.id.txtpassword) TextInputEditText etpassword;
    //gracias ala libreria Butter Knife "cuchillo de mantequilla"
    private FirebaseAuth firebaseAuth;

    @BindView(R.id.btnloginFb)    LoginButton btnLoginFB;
    CallbackManager callbackManager;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        firebaseAuth= FirebaseAuth.getInstance();
       // lblcreateaccound=(TextView)findViewById(R.id.lblcreateaccount);

        callbackManager=CallbackManager.Factory.create();
        mAuth=FirebaseAuth.getInstance();

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if (user!=null){
                    Log.d("Login",user.getUid());

                }
                else {
                    Log.d("Login","Singned Out");
                }
            }
        };

        btnLoginFB.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                siginWhithFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });




        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=etemail.getText().toString().trim();
                String password=etpassword.getText().toString().trim();
              if (email.equals("")){
                  Toast.makeText(LoginActivity.this, "Proporcione un Nombre de Usuario valido", Toast.LENGTH_SHORT).show();
                  return;
              }
              if (password.equals("")){
                  Toast.makeText(LoginActivity.this, "Proporcione una contraseña valida", Toast.LENGTH_SHORT).show();
                  return;
              }

              firebaseAuth.signInWithEmailAndPassword(email,password)
                      .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                             if (!task.isSuccessful()){
                                 Toast.makeText(LoginActivity.this, "Login Failed"+task.getException(), Toast.LENGTH_SHORT).show();
                             }
                             else {
                             startActivity(new Intent(LoginActivity.this,homeActivity.class));
                             }
                          }
              });

            }
        });
    }


    @OnClick(R.id.lblcreateaccount) void registrar() {
        Intent intent=new Intent(LoginActivity.this,createaccountActivity.class);
        startActivity(intent);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener!=null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }



    private void siginWhithFacebook(AccessToken accessToken){
        AuthCredential credential= FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this,homeActivity.class));
                }
            }
        });
    }



}
