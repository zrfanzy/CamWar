package com.example.camwar;

import com.example.camwar.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class MainMenu extends Activity {
	
	public ImageButton makeitemBtn;
	public ImageButton battleBtn;
	public Button tst;
	public ImageView itemimg;
	public TextView itemname;
	public TextView itname;
	
	private Handler mHandler;
	
	
	/**
	 * Whether or not the system UI should be auto-hidden after
	 * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean AUTO_HIDE = true;

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
	 * user interaction before hiding the system UI.
	 */
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise,
	 * will show the system UI visibility upon interaction.
	 */
	private static final boolean TOGGLE_ON_CLICK = true;

	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
	private SystemUiHider mSystemUiHider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		makeitemBtn=(ImageButton)findViewById(R.id.imageButton1);
		battleBtn=(ImageButton)findViewById(R.id.imageButton2);
		//itemimg=(ImageView)findViewById(R.id.itemImageView);
		//itemname=(TextView)findViewById(R.id.itemnameView);
		//itname=(TextView)findViewById(R.id.itname);
		
/*		
        mHandler=new Handler();
		
		mHandler.post(new Runnable() {
            @Override
            public void run()
            {
                // TODO Auto-generated method stub
            	if(GameData.meitem==0){

                	
                }
            	else{
                	switch(GameData.meitem){
                	case 1:
                		itemimg.setImageDrawable(getResources().getDrawable(R.drawable.axeitem));
                		//GameData.me.currenthp=1000;
                		itemname.setText("¸«Í·");
                		itname.setText("¸«Í·");
                		break;
                	case 2:
                		itemimg.setImageDrawable(getResources().getDrawable(R.drawable.bladeitem));
                		itemname.setText("½£");
                		break;
                	case 3:
                		itemimg.setImageDrawable(getResources().getDrawable(R.drawable.shielditem));
                		itemname.setText("¶ÜÅÆ");
                		break;
                	case 4:
                		itemimg.setImageDrawable(getResources().getDrawable(R.drawable.diceitem));
                		itemname.setText("÷»×Ó");
                		break;
                	default:
                		break;
                	
                	}
            		
            	}

            }
        });
*/



		//Item meit=new Blade();
		Item opit=new Blade();
		//
		//meit.strength=5;
		opit.strength=6;
		

		
		GameData.me.hp=100;
		GameData.me.currenthp=100;
		GameData.me.IQ=100;
		GameData.me.power=100;
		//GameData.me.it=meit;
		GameData.me.name="ÎÒ";
				
		GameData.op.hp=100;
		GameData.op.currenthp=100;
		GameData.op.power=90;
		GameData.op.it=opit;
		GameData.op.name="¹·ÐÜ";
		
	
		
		setContentView(R.layout.activity_main_menu);


	}
	


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.
		
	}
	public void startbattlebtn(View v){
		Intent intent= new Intent();
		intent.setClass(MainMenu.this, BattleActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void startmakeitem(View v){
		Intent intent= new Intent();
		intent.setClass(MainMenu.this, CamActivity.class);
		startActivity(intent);
		finish();
	}


	
}
