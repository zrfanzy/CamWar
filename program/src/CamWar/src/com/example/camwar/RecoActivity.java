package com.example.camwar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RecoActivity extends Activity {
	private static int nowCode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String item = getItemBy("/storage/sdcard0/test.jpg");
		String fake = getFakeBy(nowCode);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reco);
		
		//��ʾ��ǰ��״����
		TextView textViewFake = (TextView) findViewById(R.id.textViewFake);
		textViewFake.setText(fake);
		
		//��ʾ��Ӧ�ĵ���ͼƬ
		ImageView iv = (ImageView)findViewById(R.id.imageView1);
		switch (nowCode) {
			//case 1170:
			//	iv.setImageDrawable(getResources().getDrawable(R.drawable.lance));
			//	break;
			case 3474:
				iv.setImageDrawable(getResources().getDrawable(R.drawable.futou));
				break;
			case 1210: 
				iv.setImageDrawable(getResources().getDrawable(R.drawable.jian));
				break;
			case 1530:
				iv.setImageDrawable(getResources().getDrawable(R.drawable.dun));
				break;
			case 4088:
				iv.setImageDrawable(getResources().getDrawable(R.drawable.tou));
				break;
			case 511:
				iv.setImageDrawable(getResources().getDrawable(R.drawable.tou));
				break;
			default:
				iv.setImageDrawable(getResources().getDrawable(R.drawable.what));
				break;
		}
		
		//��ʾ��ǰ��������ֵ
		TextView textViewItem = (TextView) findViewById(R.id.textViewItem);
		textViewItem.setText(item);
		//setContentView(tv);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reco, menu);
		return true;
	}

	//ת�Ҷ�
    public final static Bitmap convertGreyImg(Bitmap img) {    
        int width = img.getWidth();         //��ȡλͼ�Ŀ�    
        int height = img.getHeight();       //��ȡλͼ�ĸ�    
            
        int []pixels = new int[width * height]; //ͨ��λͼ�Ĵ�С�������ص�����    
            
        img.getPixels(pixels, 0, width, 0, 0, width, height);    
        int alpha = 0xFF << 24;     
        for(int i = 0; i < height; i++)  {    
            for(int j = 0; j < width; j++) {    
                int grey = pixels[width * i + j];    
                    
                int red = ((grey  & 0x00FF0000 ) >> 16);    
                int green = ((grey & 0x0000FF00) >> 8);    
                int blue = (grey & 0x000000FF);    
                    
                grey = (int)((float) red * 0.3 + (float)green * 0.59 + (float)blue * 0.11);    
                grey = alpha | (grey << 16) | (grey << 8) | grey;    
                pixels[width * i + j] = grey;    
            }    
        }    
        Bitmap result = Bitmap.createBitmap(width, height, Config.RGB_565);    
        result.setPixels(pixels, 0, width, 0, 0, width, height);    
        return result;    
    }
    
    //�õ���Ϣ
    public final static String outInfo(Bitmap img) {
        int code = 0;
    	String item = "";
        int gray = 0;
        int acc = 0;
        int count = 0;
        int hit = 0;
            
        int width = img.getWidth();         //��ȡλͼ�Ŀ�    
        int height = img.getHeight();       //��ȡλͼ�ĸ�    
        int []pixels = new int[width * height]; //ͨ��λͼ�Ĵ�С�������ص�����    
            
        img.getPixels(pixels, 0, width, 0, 0, width, height);    
        int alpha = 0xFF << 24;
        int countall = 0;
        for(int i = 0; i < height; i++)  {    
            for(int j = 0; j < width; j++) {    
                int pos = pixels[width * i + j];    
                    
                gray = ((pos  & 0x00FF0000 ) >> 16);    
                
                int index = width * height - i * width - j - 1;
                countall += gray;
            }    
        }
        int door = countall / 12;
        for(int i = 0; i < height; i++)  {    
            for(int j = 0; j < width; j++) {    
                int pos = pixels[width * i + j];    
                    
                gray = ((pos  & 0x00FF0000 ) >> 16);    
                
                int index = width * height - i * width - j - 1;
                if (gray < door) {
                    code += java.lang.Math.pow(2, index);
                    acc += (255-gray);
                    count++;
                    hit += java.lang.Math.pow((gray-(acc/count)),2);
                }  
            }    
        }
        int win = 1;
        
        switch (code) {
        	//case 1170:
        	//	item += "���õ���һ�Ѻ������ص��ջ����!\n\n";
            //	break;
        	case 3474:
        		item += "������һ������ɽ�ӵĿ�����!\n\n";
        		GameData.me.it=new Axe();
        		GameData.me.it.strength=acc%10+1;
        		GameData.meitem=1;
        		break;
        	case 1210:
        		item += "��õ���һ���������������֮��!\n\n";
        		GameData.me.it=new Blade();
        		GameData.me.it.strength=acc%10+1;
        		GameData.meitem=2;
        		break;
        	case 1530:
        		item += "�㷢����һ���â���ɵ���˹֮��!\n\n";
        		GameData.me.it=new Shield();
        		GameData.me.it.strength=acc%10+1;
        		GameData.meitem=3;
        		break;
        	case 4088:
        		item += "�㷢����һ�Ż���İ���˹̹֮��!\n\n";
        		GameData.me.it=new Dice();
        		GameData.me.it.strength=acc%10+1;
        		GameData.meitem=4;
        		break;
        	case 511:
        		item += "�㷢����һ�Ż���İ���˹̹֮��!\n\n";
        		GameData.me.it=new Dice();
        		GameData.me.it.strength=acc%10+1;
        		GameData.meitem=4;
        		break;
        	default:
        		item += "��!�ҵ��۾��������ɱ���!\n\n";
        		win = 0;
        		break;
        }
        nowCode = code;
        if (win == 1)
        {
	        item += "ħ������  = ";
	        item += acc;
	        int power = acc*10/count;
	        item += "\n������ = ";
	        item += power;
	        hit /= 100;
	        item += "\n������ = ";
	        item += hit;
	        item += "%\n";
        }
        return item;
    }
    
    public static String reverseString(String s) {
    	// ��ת�ַ����ķ���  
            String newStr = "" ;                  
    // �洢��ת��Ľ��  
            int len = s.length();                 
    // ԭ�ַ����ĳ���  
            for (int i = len - 1; i >= 0; i--) {  
    // ��������ַ���  
                newStr += s.charAt(i);            
    // ���ӷ�������Ľ��  
            }  
            return newStr;  
    }
    
    public final static String getFakeBy(int code)
    {
    	String fake = "";
    	fake += "\n";
    	for (int i = 0; i < 12; i++) {
    		if (code % 2 == 1) {
    			fake += " �� ";
    		}
    		else {
    			fake += " �� ";
    		}
    		if ((i % 3 == 2) && (i != 11)) {
    			fake += "\n";
    		}
    		code /= 2;
    	}
    	return reverseString(fake);
    }
    
    public final static String getItemBy(String fileName)
    {
    	Bitmap bitmap = BitmapFactory.decodeFile(fileName);
    	//�������������,�õ�����ͼ,���һ������Ϊ��������
    	Bitmap small = Bitmap.createScaledBitmap(bitmap, 3, 4, true);
    	//������ͼתΪ�Ҷ�ͼ
    	Bitmap grey = convertGreyImg(small);
    	//���ݻҶ�ͼ���㲿�ֵ�������
    	return outInfo(grey);
    }
    
    public void useit(View v){
		Intent intent= new Intent();
		intent.setClass(RecoActivity.this, MainMenu.class);
		startActivity(intent);
    }
    
    public void recam(View v){
		Intent intent= new Intent();
		intent.setClass(RecoActivity.this, CamActivity.class);
		startActivity(intent);
    }
    
    
	
}
