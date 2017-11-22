package com.lopsa.platzigram;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.apache.commons.logging.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnloginFb2) LoginButton btnLoginFB;
    @BindView(R.id.btnloginemail)  Button btnLoginEmail;
    CallbackManager callbackManager;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        callbackManager=CallbackManager.Factory.create();
        mAuth=FirebaseAuth.getInstance();

       mAuthListener=new FirebaseAuth.AuthStateListener() {
           @Override
           public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user=firebaseAuth.getCurrentUser();
            if (user!=null){
                //Log.d("Login",user.getUid());
                android.util.Log.d("Login",user.getUid());

            }
            else {
                android.util.Log.d("Login","Singned Out");
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
    }
    @OnClick(R.id.btnloginemail) void loginemail(){
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
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
                        startActivity(new Intent(MainActivity.this,homeActivity.class));
                    }
                }
            });
        }

}
