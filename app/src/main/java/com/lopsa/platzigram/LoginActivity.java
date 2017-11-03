package com.lopsa.platzigram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends  android.support.v7.app.AppCompatActivity  {

    private Button btnlogin;
    private TextView lblcreateaccound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        lblcreateaccound=(TextView)findViewById(R.id.lblcreateaccount);

        lblcreateaccound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,createaccountActivity.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                   //los flags son para borrar el historial de navegacion
                    Intent intent = new Intent( LoginActivity.this, homeActivity.class );
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    startActivity(intent);
                }
                catch (Exception ex){
                    Toast.makeText(LoginActivity.this, ""+ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
