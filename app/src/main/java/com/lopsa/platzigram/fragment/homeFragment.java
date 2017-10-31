package com.lopsa.platzigram.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lopsa.platzigram.Model.Picture;
import com.lopsa.platzigram.R;
import com.lopsa.platzigram.adapter.PictureAdapterreciclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class homeFragment extends Fragment {
private FloatingActionButton fab;

    public homeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.tab_home),false,view);

        RecyclerView picturesRecicler=(RecyclerView)view.findViewById(R.id.resiclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        picturesRecicler.setLayoutManager(linearLayoutManager);
        PictureAdapterreciclerView pictureAdapterreciclerView=new PictureAdapterreciclerView(builPicture(),R.layout.carview_picture,getActivity());
        picturesRecicler.setAdapter(pictureAdapterreciclerView);

        fab =(FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"FAB Button Hi",Snackbar.LENGTH_LONG).show();
            }
        });

        return view;
    }

    public ArrayList<Picture> builPicture(){
        ArrayList<Picture>pictures=new ArrayList<Picture>();
        pictures.add(new Picture("https://upload.wikimedia.org/wikipedia/en/4/4b/Freeman_500x500.jpg","Omar lopez","5 Dias","10 Me Gusta"));
        pictures.add(new Picture("@drawable/image","Franco DVita","10 Dias","15 Me Gusta"));
        pictures.add(new Picture("https://static.pexels.com/photos/34950/pexels-photo.jpg","Paola Saveedra","5 Dias","8 Me Gusta"));
        pictures.add(new Picture("http://www.novalandtours.com/images/guide/guili.jpg","Eliana Melagr","8 Dias","5 Me Gusta"));
        return pictures;
    }

    //metodo que activa el boton atras
    private void showToolbar(String title, boolean upbutton ,View view){
        Toolbar toolbar=(Toolbar)view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upbutton);
    }
}
