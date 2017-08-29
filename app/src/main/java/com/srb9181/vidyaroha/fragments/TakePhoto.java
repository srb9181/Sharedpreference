package com.srb9181.vidyaroha.fragments;


import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.srb9181.vidyaroha.BuildConfig;
import com.srb9181.vidyaroha.utils.Constants;
import com.srb9181.vidyaroha.R;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakePhoto extends Fragment {

    String mCurrentPhotoPath;
    Uri photoURI;
    ImageView imageView1, imageView2;
    Bitmap imageBitmap = null,imageBitmap2=null;
    String clicked ;

    public TakePhoto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_take_photo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked ="one";
                takePickture();

            }
        });


        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePickture();
                clicked ="two";
            }
        });

        imageView1 = (ImageView) view.findViewById(R.id.imageView1);
        imageView2 = (ImageView) view.findViewById(R.id.imageView2);

        SharedPreferences preferences = getActivity().getSharedPreferences(Constants.IMAGE, Context.MODE_PRIVATE);
        String path = preferences.getString(Constants.IMAGE_PATH,"");


        if(!path.equals("")) {
            imageBitmap = BitmapFactory.decodeFile(path);
            imageView1.setImageBitmap(imageBitmap);
        }

        SharedPreferences preferences2 = getActivity().getSharedPreferences(Constants.IMAGE2, Context.MODE_PRIVATE);
        String path2 = preferences2.getString(Constants.IMAGE_PATH2,"");
        if(!path2.equals("")) {
            imageBitmap2 = BitmapFactory.decodeFile(path2);
            imageView2.setImageBitmap(imageBitmap2);
        }
    }

    private void takePickture() {

        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP)
        {
            if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    ||ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {

                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE}, Constants.CAM_REQUEST);

                } else
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE}, Constants.CAM_REQUEST);

                }
                else {
                    captureImage();
                 }
            }
            else {
              captureImage();
        }

        }

    private void captureImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        /*if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {

            startActivityForResult(takePictureIntent, Constants.CAM_PICK);
        }*/
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        BuildConfig.APPLICATION_ID+".fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, Constants.CAM_PICK);
                Constants.ACT = 2;
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(ActivityCompat.checkSelfPermission(getActivity(),permissions[0])==PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case Constants.CAM_REQUEST:
                    captureImage();
            }

        }
        else{
            Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.CAM_PICK && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
        //    String uri  = data.getExtras().toString();
           //(Bitmap) extras.get("data");





            if(clicked.equals("one")) {
                SharedPreferences.Editor  editor = getActivity().getSharedPreferences(Constants.IMAGE, Context.MODE_PRIVATE).edit();
                editor.putString(Constants.IMAGE_PATH, mCurrentPhotoPath);
                editor.commit();

                SharedPreferences preferences = getActivity().getSharedPreferences(Constants.IMAGE, Context.MODE_PRIVATE);
                String path = preferences.getString(Constants.IMAGE_PATH,"");
                imageBitmap = BitmapFactory.decodeFile(path);
                imageView1.setImageBitmap(imageBitmap);
            }
            else if (clicked.equals("two")){
                SharedPreferences.Editor  editor = getActivity().getSharedPreferences(Constants.IMAGE2, Context.MODE_PRIVATE).edit();
                editor.putString(Constants.IMAGE_PATH2, mCurrentPhotoPath);
                editor.commit();

                SharedPreferences preferences = getActivity().getSharedPreferences(Constants.IMAGE2, Context.MODE_PRIVATE);
                String path2 = preferences.getString(Constants.IMAGE_PATH2,"");
                imageBitmap2 = BitmapFactory.decodeFile(path2);
                imageView2.setImageBitmap(imageBitmap2);
            }
            //mImageView.setImageBitmap(imageBitmap);
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name

        File storageDir = getActivity().getExternalFilesDir(Environment.getExternalStorageDirectory()+"Pic");
        File image = Constants.setUpImageFile(Constants.LOCAL_STORAGE_BASE_PATH_FOR_USER_PHOTOS) ;/*File.createTempFile(
                imageFileName,  *//* prefix *//*
                ".jpg",         *//* suffix *//*
                storageDir      *//* directory
        );*/

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }



}

