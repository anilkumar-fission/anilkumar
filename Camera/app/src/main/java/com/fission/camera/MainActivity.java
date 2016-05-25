package com.fission.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
   private static final int CAMERA_CAPTURE_IMAGE_RESEQUEST_CODE=100;
    private static final int MEDIA_TYPE_IMAGE=1;
    Button mBtncamera;
    ImageView mIvimage;
    private static final String IMAGE_DIRECTORY_NAME="CAMERA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtncamera=(Button)findViewById(R.id.camera);
        mIvimage=(ImageView) findViewById(R.id.image);
        mBtncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage();
            }
            private void captureImage() {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_CAPTURE_IMAGE_RESEQUEST_CODE);
            }
        });

    }
    private void previewCapturedImage() {
        try {
            mIvimage.setVisibility(View.VISIBLE);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==CAMERA_CAPTURE_IMAGE_RESEQUEST_CODE){
            if (resultCode==RESULT_OK){

                Bitmap photo = (Bitmap) data.getExtras().get("data");
                mIvimage.setImageBitmap(photo);
               // previewCapturedImage();
                Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
            }
            else if (resultCode==RESULT_CANCELED){
                Toast.makeText(getApplicationContext(),"user cancel the image",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"failed to capture the image",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
