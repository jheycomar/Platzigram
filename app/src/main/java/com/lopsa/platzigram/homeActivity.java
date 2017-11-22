package com.lopsa.platzigram;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.lopsa.platzigram.fragment.homeFragment;
import com.lopsa.platzigram.fragment.profileFragment;
import com.lopsa.platzigram.fragment.searchFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class homeActivity extends AppCompatActivity {
private  BottomBar bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(R.id.tab_home);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {


                switch (tabId){
                    case R.id.tab_search:
                        searchFragment searchfragment=new searchFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,searchfragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();

                        break;

                    case R.id.tab_home:

                        homeFragment homefragment=new homeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homefragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;
                    case R.id.tab_profile:
                        profileFragment profilefragment=new profileFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profilefragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();

                        break;

                }


            }
        });



    }

    @Override
    public void onBackPressed() {
        bottomBar.removeOnTabSelectListener();
        Toast.makeText(this, "hiback", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }


}
