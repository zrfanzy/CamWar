package com.example.camwar;


import android.app.Activity;  
import android.content.Intent;  
import android.graphics.Bitmap;  
import android.graphics.BitmapFactory;  
import android.graphics.Matrix;  
import android.os.Bundle;  
import android.widget.ImageView;  
  
public class ShowPicActivity extends Activity {  
    private ImageView ivPic = null; // 显示图片控件  
   
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_cam);  
        ivPic = (ImageView) findViewById(R.id.takepicture);  
        setImageBitmap(getImageFormBundle());  
  
    }  
  

    public void setImageBitmap(byte[] bytes) {  
        Bitmap cameraBitmap = byte2Bitmap();  
        Matrix matrix = new Matrix();  
        matrix.setRotate(CamActivity.getPreviewDegree(this));  
        cameraBitmap = Bitmap  
                .createBitmap(cameraBitmap, 0, 0, cameraBitmap.getWidth(),  
                        cameraBitmap.getHeight(), matrix, true);  
        ivPic.setImageBitmap(cameraBitmap);  
    }  
  

    public byte[] getImageFormBundle() {  
        Intent intent = getIntent();  
        Bundle data = intent.getExtras();  
        byte[] bytes = data.getByteArray("bytes");  
        return bytes;  
    }  
  
    private Bitmap byte2Bitmap() {  
        byte[] data = getImageFormBundle();  
        // 将byte数组转换成Bitmap对象  
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);  
        return bitmap;  
    }  
}  