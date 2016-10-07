package sellit.soict.com.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dang Hien on 14/04/2016.
 */
public class PictureUtils {

    public static String encode(ArrayList<Bitmap> bitmaps) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bitmaps.size(); i++) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmaps.get(i).compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byte_arr = stream.toByteArray();
            String encode = Base64.encodeToString(byte_arr, 0);
            builder.append(encode + " ; ");
        }
        return builder.toString();
    }

    public static String saveToFile(Fragment fragment, int key) {

        File dir = Environment.getExternalStorageDirectory();
        dir = new File(dir.getAbsolutePath() + "/Sell-it/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String picName = dir.getAbsolutePath() + new Date().toString();
        File file = new File(picName);
        Uri outputFileUri = Uri.fromFile(file);
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        fragment.startActivityForResult(cameraIntent, key);
        return file.getPath();
    }
    public static void getFromGallery(Fragment fragment,int key){
        Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        fragment.startActivityForResult(i, key);
    }

//    private static final String TAG = Request.class.getSimpleName();


}
