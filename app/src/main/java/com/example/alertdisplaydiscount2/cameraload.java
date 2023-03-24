package com.example.alertdisplaydiscount2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cameraload extends AppCompatActivity {

    public static final String TAG = cameraload.class.getSimpleName()+"My";
    public static final int CAMERA_PERMISSION = 100;//檢測相機權限用
    public static final int REQUEST_LOW_IMAGE = 101;//檢測低畫質相機回傳

    Button b1,b2,b4;
    TextView end;
    ImageView imageView;
    Intent intent;
    int PICK_CONTACT_REQUEST=1;
    Uri uri;
    String data_list,currentPhotoPath;
    StorageReference storageReference,pic_storage;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameraload);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b4=(Button)findViewById(R.id.button4);

        end=(TextView)findViewById(R.id.textView2);
        imageView=(ImageView)findViewById(R.id.imageView);


        Button btLow = findViewById(R.id.buttonLow);
        /**取得相機權限*/
        if (checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION);
        /**按下低畫質照相之拍攝按鈕*/
        btLow.setOnClickListener(v -> {
            Intent lowIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(lowIntent,REQUEST_LOW_IMAGE);
        });

        storageReference= FirebaseStorage.getInstance().getReference();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,1);
                b2.setEnabled(true);
                b4.setEnabled(true);

                b4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pic_storage=storageReference.child("1."+data_list);
                        pic_storage.delete();
                        end.setText("刪除成功");
                        imageView.setImageURI(null);
                    }
                });
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pic_storage=storageReference.child("1."+data_list);
                pic_storage.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        end.setText("上傳成功");
                    }
                });
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data)
    {
        if(requestCode==PICK_CONTACT_REQUEST ){
            uri = data.getData();
            imageView.setImageURI(uri);
            ContentResolver contentResolver=getContentResolver();
            MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
            data_list=mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

        }
        super.onActivityResult(requestCode,resultCode,data);
        Log.d(TAG, "onActivityResult: requestCode: "+requestCode+", resultCode "+resultCode);
        /**如果是低畫質的相片回傳*/
        if (requestCode == REQUEST_LOW_IMAGE && resultCode == -1) {
            ImageView imageLow = findViewById(R.id.imageView);
            Bundle getImage = data.getExtras();
            Bitmap getLowImage = (Bitmap) getImage.get("data");
            //以Glide設置圖片
            Glide.with(this)
                    .load(getLowImage)
                    .centerCrop()
                    .into(imageLow);
        }else{
            Toast.makeText(this, "未作任何拍攝", Toast.LENGTH_SHORT).show();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
}