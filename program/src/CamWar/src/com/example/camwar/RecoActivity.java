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
		
		//显示当前形状矩阵
		TextView textViewFake = (TextView) findViewById(R.id.textViewFake);
		textViewFake.setText(fake);
		
		//显示对应的道具图片
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
		
		//显示当前武器属性值
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

	//转灰度
    public final static Bitmap convertGreyImg(Bitmap img) {    
        int width = img.getWidth();         //获取位图的宽    
        int height = img.getHeight();       //获取位图的高    
            
        int []pixels = new int[width * height]; //通过位图的大小创建像素点数组    
            
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
    
    //得到信息
    public final static String outInfo(Bitmap img) {
        int code = 0;
    	String item = "";
        int gray = 0;
        int acc = 0;
        int count = 0;
        int hit = 0;
            
        int width = img.getWidth();         //获取位图的宽    
        int height = img.getHeight();       //获取位图的高    
        int []pixels = new int[width * height]; //通过位图的大小创建像素点数组    
            
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
        	//	item += "你拿到了一把厚重神秘的烧火棍子!\n\n";
            //	break;
        	case 3474:
        		item += "你获得了一把气吞山河的开天神斧!\n\n";
        		GameData.me.it=new Axe();
        		GameData.me.it.strength=acc%10+1;
        		GameData.meitem=1;
        		break;
        	case 1210:
        		item += "你得到了一把削铁如泥的王者之剑!\n\n";
        		GameData.me.it=new Blade();
        		GameData.me.it.strength=acc%10+1;
        		GameData.meitem=2;
        		break;
        	case 1530:
        		item += "你发现了一块光芒万丈的宙斯之盾!\n\n";
        		GameData.me.it=new Shield();
        		GameData.me.it.strength=acc%10+1;
        		GameData.meitem=3;
        		break;
        	case 4088:
        		item += "你发现了一颗混沌的爱因斯坦之骰!\n\n";
        		GameData.me.it=new Dice();
        		GameData.me.it.strength=acc%10+1;
        		GameData.meitem=4;
        		break;
        	case 511:
        		item += "你发现了一颗混沌的爱因斯坦之骰!\n\n";
        		GameData.me.it=new Dice();
        		GameData.me.it.strength=acc%10+1;
        		GameData.meitem=4;
        		break;
        	default:
        		item += "啊!我的眼睛被真相蒙蔽了!\n\n";
        		win = 0;
        		break;
        }
        nowCode = code;
        if (win == 1)
        {
	        item += "魔法攻击  = ";
	        item += acc;
	        int power = acc*10/count;
	        item += "\n物理攻击 = ";
	        item += power;
	        hit /= 100;
	        item += "\n暴击率 = ";
	        item += hit;
	        item += "%\n";
        }
        return item;
    }
    
    public static String reverseString(String s) {
    	// 反转字符串的方法  
            String newStr = "" ;                  
    // 存储反转后的结果  
            int len = s.length();                 
    // 原字符串的长度  
            for (int i = len - 1; i >= 0; i--) {  
    // 反向遍历字符串  
                newStr += s.charAt(i);            
    // 连接反向遍历的结果  
            }  
            return newStr;  
    }
    
    public final static String getFakeBy(int code)
    {
    	String fake = "";
    	fake += "\n";
    	for (int i = 0; i < 12; i++) {
    		if (code % 2 == 1) {
    			fake += " ■ ";
    		}
    		else {
    			fake += " □ ";
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
    	//在这里完成缩放,得到缩略图,最后一个参数为开启过滤
    	Bitmap small = Bitmap.createScaledBitmap(bitmap, 3, 4, true);
    	//将缩略图转为灰度图
    	Bitmap grey = convertGreyImg(small);
    	//根据灰度图计算部分道具属性
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
