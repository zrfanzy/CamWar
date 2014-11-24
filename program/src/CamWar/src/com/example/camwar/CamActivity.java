package com.example.camwar;



import java.io.BufferedOutputStream;
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
  
  




import android.app.Activity;  
import android.content.Intent;  
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;  
import android.hardware.Camera;  
import android.hardware.Camera.PictureCallback;  
import android.os.Bundle;  
import android.os.Environment;  
import android.view.KeyEvent;  
import android.view.MotionEvent;  
import android.view.Surface;  
import android.view.SurfaceHolder;  
import android.view.SurfaceHolder.Callback;  
import android.view.SurfaceView;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.Toast;  
  
public class CamActivity extends Activity {  
    private View layout;  
    private Camera camera;  
    private Camera.Parameters parameters = null;  
  
    Bundle bundle = null; // 澹版槑涓�釜Bundle瀵硅薄锛岀敤鏉ュ瓨鍌ㄦ暟鎹� 
  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        // 鏄剧ず鐣岄潰  
        setContentView(R.layout.activity_cam);  
  
        layout = this.findViewById(R.id.buttonLayout);  
        SurfaceView surfaceView = (SurfaceView) this  
                .findViewById(R.id.surfaceView);  
        surfaceView.getHolder()  
                .setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);  
        surfaceView.getHolder().setFixedSize(176, 144); //璁剧疆Surface鍒嗚鲸鐜� 
        surfaceView.getHolder().setKeepScreenOn(true);// 灞忓箷甯镐寒  
        surfaceView.getHolder().addCallback(new SurfaceCallback());//涓篠urfaceView鐨勫彞鏌勬坊鍔犱竴涓洖璋冨嚱鏁� 
    }  
  
    /** 
     * 鎸夐挳琚偣鍑昏Е鍙戠殑浜嬩欢 
     *  
     * @param v 
     */  
    public void btnOnclick(View v) {  
        if (camera != null) {  
            switch (v.getId()) {  
            case R.id.takepicture:  
                // 鎷嶇収  
                camera.takePicture(null, null, new MyPictureCallback());  
                break;  
            }  
        }  
    }  
  
    /** 
     * 鍥剧墖琚偣鍑昏Е鍙戠殑鏃堕棿 
     *  
     * @param v 
     */  
    public void imageClick(View v) {  
        if (v.getId() == R.id.takepicture) {  
            if (bundle == null) {  
                Toast.makeText(getApplicationContext(), "take photo",  
                        Toast.LENGTH_SHORT).show();  
            } else {  
                Intent intent = new Intent(this, ShowPicActivity.class);  
                intent.putExtras(bundle);  
                startActivity(intent);  
            }  
        }  
    }  
  
    private final class MyPictureCallback implements PictureCallback {  
  
        @Override  
        public void onPictureTaken(byte[] data, Camera camera) {  
            try {  
            	/*Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);    
                File file = new File("/sdcard/wjh.jpg");    
                BufferedOutputStream bos     
                = new BufferedOutputStream(new FileOutputStream(file));    
                bm.compress(Bitmap.CompressFormat.JPEG,100,bos);    
                bos.flush();    
                bos.close();  */  
            	bundle = new Bundle();  
                bundle.putByteArray("bytes", data); //灏嗗浘鐗囧瓧鑺傛暟鎹繚瀛樺湪bundle褰撲腑锛屽疄鐜版暟鎹氦鎹� 
                saveToSDCard(data); // 淇濆瓨鍥剧墖鍒皊d鍗′腑  
                
                Toast.makeText(getApplicationContext(), "success",  
                        Toast.LENGTH_SHORT).show();  
                camera.startPreview(); // 鎷嶅畬鐓у悗锛岄噸鏂板紑濮嬮瑙� 
                
        		Intent intent= new Intent();
        		intent.setClass(CamActivity.this, RecoActivity.class);
        		startActivity(intent);
                
                
  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /** 
     * 灏嗘媿涓嬫潵鐨勭収鐗囧瓨鏀惧湪SD鍗′腑 
     * @param data   
     * @throws IOException 
     */  
    /*public static void saveToSDCard(byte[] data) throws IOException {  
        //Date date = new Date();  
        //SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // 鏍煎紡鍖栨椂闂� 
        //String filename = format.format(date) + ".jpg";  
        File fileFolder = new File(Environment.getExternalStorageDirectory()+"");  
    	/*File fileFolder = new File("");
        if (!fileFolder.exists()) { // 濡傛灉鐩綍涓嶅瓨鍦紝鍒欏垱寤轰竴涓悕涓�finger"鐨勭洰褰� 
            fileFolder.mkdir();  
        }  */
        /*File jpgFile = new File(fileFolder,"test.jpg");  
        FileOutputStream outputStream = new FileOutputStream(jpgFile); // 鏂囦欢杈撳嚭娴� 
        outputStream.write(data); // 鍐欏叆sd鍗′腑  
        outputStream.close(); // 鍏抽棴杈撳嚭娴� 
    }  */
    
    public void saveToSDCard(byte[] data) throws IOException {  
    	Bitmap bMap; 
    	File fileFolder = new File(Environment.getExternalStorageDirectory()+""); 
    	File jpgFile = new File(fileFolder,"test.jpg");  
    	try 
    	{
    		bMap = BitmapFactory.decodeByteArray(data, 0, data.length);        
    		Bitmap bMapRotate;  
    		Configuration config = getResources().getConfiguration();
    		if (config.orientation==1)
    		{ // 鍧氭媿
    		Matrix matrix = new Matrix();
    		matrix.reset();
    		matrix.postRotate(90);
    		bMapRotate = Bitmap.createBitmap(bMap, 0, 0, bMap.getWidth(), bMap.getHeight(), matrix, true);
    		bMap = bMapRotate;
    		}
    		Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);   
    		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(jpgFile)); 
    		bMap.compress(Bitmap.CompressFormat.JPEG, 50, bos);//灏嗗浘鐗囧帇缂╁埌娴佷腑 
    		bos.flush();//杈撳嚭 
    		bos.close();//鍏抽棴 
    	}catch(Exception e)  
    	{ 
    		e.printStackTrace(); 
    	} 
} 
  
  
    private final class SurfaceCallback implements Callback {  
  
        // 鎷嶇収鐘舵�鍙樺寲鏃惰皟鐢ㄨ鏂规硶  
        @Override  
        public void surfaceChanged(SurfaceHolder holder, int format, int width,  
                int height) {  
            parameters = camera.getParameters(); // 鑾峰彇鍚勯」鍙傛暟  
            //parameters.set("camera-id", 2);  
            parameters.setPictureFormat(PixelFormat.JPEG); // 璁剧疆鍥剧墖鏍煎紡 
            parameters.setPreviewSize(width, height); // 璁剧疆棰勮澶у皬  
            parameters.setPreviewFrameRate(5);  //璁剧疆姣忕鏄剧ず4甯� 
            parameters.setPictureSize(width, height); // 璁剧疆淇濆瓨鐨勫浘鐗囧昂瀵� 
            parameters.setJpegQuality(80); // 璁剧疆鐓х墖璐ㄩ噺  
            //camera.setParameters(parameters);
        }  
  
        // 寮�鎷嶇収鏃惰皟鐢ㄨ鏂规硶  
        @Override  
        public void surfaceCreated(SurfaceHolder holder) {  
            try {  
                camera = Camera.open(); // 鎵撳紑鎽勫儚澶� 
                camera.setPreviewDisplay(holder); // 璁剧疆鐢ㄤ簬鏄剧ず鎷嶇収褰卞儚鐨凷urfaceHolder瀵硅薄  
                camera.setDisplayOrientation(getPreviewDegree(CamActivity.this));  
                camera.startPreview(); // 寮�棰勮 
                
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
  
        }  
  
        // 鍋滄鎷嶇収鏃惰皟鐢ㄨ鏂规硶  
        @Override  
        public void surfaceDestroyed(SurfaceHolder holder) {  
            if (camera != null) {  
                camera.release(); // 閲婃斁鐓х浉鏈� 
                camera = null;  
            }  
        }  
    }  
  
    /** 
     * 鐐瑰嚮鎵嬫満灞忓箷鏄紝鏄剧ず涓や釜鎸夐挳 
     */  
    @Override  
    public boolean onTouchEvent(MotionEvent event) {  
        switch (event.getAction()) {  
        case MotionEvent.ACTION_DOWN:  
            layout.setVisibility(ViewGroup.VISIBLE); // 璁剧疆瑙嗗浘鍙  
            break;  
        }  
        return true;  
    }  
  
      
    @Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        switch (keyCode) {  
        case KeyEvent.KEYCODE_CAMERA: // 鎸変笅鎷嶇収鎸夐挳  
            if (camera != null && event.getRepeatCount() == 0) {  
                // 鎷嶇収  
                //娉細璋冪敤takePicture()鏂规硶杩涜鎷嶇収鏄紶鍏ヤ簡涓�釜PictureCallback瀵硅薄鈥斺�褰撶▼搴忚幏鍙栦簡鎷嶇収鎵�緱鐨勫浘鐗囨暟鎹箣鍚� 
                //锛孭ictureCallback瀵硅薄灏嗕細琚洖璋冿紝璇ュ璞″彲浠ヨ礋璐ｅ鐩哥墖杩涜淇濆瓨鎴栦紶鍏ョ綉缁� 
                camera.takePicture(null, null, new MyPictureCallback());  
            }  
        }  
        return super.onKeyDown(keyCode, event);  
    }  
  
    // 鎻愪緵涓�釜闈欐�鏂规硶锛岀敤浜庢牴鎹墜鏈烘柟鍚戣幏寰楃浉鏈洪瑙堢敾闈㈡棆杞殑瑙掑害  
    public static int getPreviewDegree(Activity activity) {  
        // 鑾峰緱鎵嬫満鐨勬柟鍚� 
        int rotation = activity.getWindowManager().getDefaultDisplay()  
                .getRotation();  
        int degree = 0;  
        // 鏍规嵁鎵嬫満鐨勬柟鍚戣绠楃浉鏈洪瑙堢敾闈㈠簲璇ラ�鎷╃殑瑙掑害  
        switch (rotation) {  
        case Surface.ROTATION_0:  
            degree = 90;  
            break;  
        case Surface.ROTATION_90:  
            degree = 0;  
            break;  
        case Surface.ROTATION_180:  
            degree = 270;  
            break;  
        case Surface.ROTATION_270:  
            degree = 180;  
            break;  
        }  
        return degree;  
    }  
}  