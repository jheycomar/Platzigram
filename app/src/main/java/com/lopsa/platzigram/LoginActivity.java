package com.lopsa.platzigram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends  android.support.v7.app.AppCompatActivity  {

    private Button btnlogin;
    //@BindView(R.id.lblcreateaccount) TextView lblcreateaccound;
//gracias ala libreria Butter Knife "cuchillo de mantequilla"
    @OnClick(R.id.lblcreateaccount) void registrar() {
        Toast.makeText(this, "entro", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(LoginActivity.this,createaccountActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        btnlogin=(Button)findViewById(R.id.btnlogin);
       // lblcreateaccound=(TextView)findViewById(R.id.lblcreateaccount);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                   //los flags son para borrar el historial de navegacion
                    Intent intent = new Intent( LoginActivity.this, homeActivity.class );
                    startActivity(intent);
                }
                catch (Exception ex){
                    Toast.makeText(LoginActivity.this, ""+ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
