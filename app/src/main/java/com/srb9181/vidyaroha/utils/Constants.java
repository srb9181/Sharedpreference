package com.srb9181.vidyaroha.utils;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by Rudra on 8/12/2017.
 */

public class Constants {

    public static final String  APP = "vidyaroha";
    public static final String  APP_COUNT = "AppCount";
    public static final String  IMAGE = "srb";
    public static final String  IMAGE_PATH = "path";
    public static final String  IMAGE_PATH2 = "path2";
    public static final String IMAGE2 = "srb2";
    public static final int CAM_REQUEST = 9181;
    public static final int CAM_PICK = 91;
    public static int ACT =1;

    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";

    private static final String LOCAL_STORAGE_BASE_PATH_FOR_MEDIA = Environment
            .getExternalStorageDirectory() + "/" + APP;

    public static final String LOCAL_STORAGE_BASE_PATH_FOR_USER_PHOTOS =
            LOCAL_STORAGE_BASE_PATH_FOR_MEDIA + "/User/Photos/";



    public static File setUpImageFile(String directory) throws IOException {
        File imageFile = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            File storageDir = new File(directory);
            if (storageDir != null) {
                if (!storageDir.mkdirs()) {
                    if (!storageDir.exists()) {
                        //Log.d("CameraSample", "failed to create directory");
                        return null;
                    }
                }
            }
            imageFile = File.createTempFile(JPEG_FILE_PREFIX
                            + System.currentTimeMillis() + "_",
                    JPEG_FILE_SUFFIX, storageDir);
        }
        return imageFile;
    }
}
