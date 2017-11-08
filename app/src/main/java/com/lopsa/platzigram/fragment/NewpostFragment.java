package com.lopsa.platzigram.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lopsa.platzigram.LoginActivity;
import com.lopsa.platzigram.R;
import com.lopsa.platzigram.createaccountActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class NewpostFragment extends Fragment {
 @BindView(R.id.IvPicture)    ImageView ivpicture;
 //@BindView(R.id.BtnTakePicture)   Button btntakepicture;
  String mCurrenPothoPath;
   static  final  int requesImageCapture=1;


    public NewpostFragment() {
        // Required empty public constructor

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_newpost,container,false);
       ButterKnife.bind(this,view);

        return view;
    }

    @OnClick({R.id.BtnTakePicture}) public void takePicture() {
        Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
       Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      if (takePictureIntent.resolveActivity(getActivity().getPackageManager())!=null){

          File photoFile=null;
          try {
              photoFile=createImageFile();
          } catch (IOException e) {
              e.printStackTrace();
          }
          if (photoFile!=null){

              Uri photoUri= FileProvider.getUriForFile(getActivity(),"com.lopsa.platzigram",photoFile);
              takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
              startActivityForResult(takePictureIntent,requesImageCapture);
          }

      }

    }
    //este metodo se imprementa . despues de la toma de camara
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==requesImageCapture && resultCode==getActivity().RESULT_OK){
            
            Picasso.with(getActivity()).load(mCurrenPothoPath).into(ivpicture);
            addPictureToGallery();
            Toast.makeText(getActivity(), mCurrenPothoPath, Toast.LENGTH_SHORT).show();

            //muestra la captura de la imagen sin guardarla
            /*Bundle extras= data.getExtras();
            Bitmap imageBitmap= (Bitmap)extras.get("data");
            ivpicture.setImageBitmap(imageBitmap);*/
        }
    }

    private File createImageFile()throws IOException{
        //creamos un nuevo archivo
        String timeStamp= new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String imageFileName="IMG" + timeStamp + "-";
        File storageDir=getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image=File.createTempFile(imageFileName,".jpg",storageDir);
        mCurrenPothoPath="file:" + image.getAbsolutePath();
            return image;
    }

    private  void  addPictureToGallery(){
          Intent   mediaScanIntent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
          File newFile=new File(mCurrenPothoPath);
          Uri contentUri=Uri.fromFile(newFile);
          mediaScanIntent.setData(contentUri);
          getActivity().sendBroadcast(mediaScanIntent);
    }

}
