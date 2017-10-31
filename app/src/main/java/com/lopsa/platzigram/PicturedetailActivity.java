package com.lopsa.platzigram;

import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.transition.Fade;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class PicturedetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picturedetail);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showToolbar("",true);
            if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
                getWindow().setEnterTransition(new android.transition.Fade());
            }
    }
    //metodo que activa el boton atras
    private void showToolbar(String title, boolean upbutton ){
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upbutton);
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsinToolbardetail);
    }
}
