package com.example.camwar;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BattleActivity extends Activity {

	
	public TextView btInfo;
	public TextView mehp;
	public TextView ophp;
	private Handler mHandler;
	Player p1;
	Player p2;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle);
		btInfo=(TextView)findViewById(R.id.itemText);
		mehp=(TextView)findViewById(R.id.mehptextView);
		ophp=(TextView)findViewById(R.id.ophptextView);
		btInfo.setText("hehe");
		GameData.i=0;
		GameData.endbattle=false;
		
		
		if(GameData.me.speed>=GameData.op.speed){
			p1=GameData.me;
			p2=GameData.op;
		}
		else
		{
			p2=GameData.me;
			p1=GameData.op;
		}
		
		
		mHandler=new Handler();
		
		mHandler.post(new Runnable() {
            @Override
            public void run()
            {
                // TODO Auto-generated method stub
            	
            	if(GameData.endbattle){
            		p1.currenthp=p1.hp;
            		p2.currenthp=p2.hp;
            		finish();
            		return;
            	}
            	
            	GameData.i++;
            	
            	if((p1.currenthp<=0)||(p2.currenthp<=0)){//战斗结束
            		
            		if(p1.currenthp<=0){
            			btInfo.setText(p2.name+"获胜！");
            		}
            		else{
            			btInfo.setText(p1.name+"获胜！");
            		}
            		
            		
            		
            		GameData.endbattle=true;
            		
            		
            		
            	}
            	
            	else{
            		
            		if(GameData.i==1){
                		btInfo.setText("战斗开始！    "+p1.name+"先出招！");
                		
                	}
                	else
                	{
                		if(GameData.i%2==0){
                			BattleInfo bi=p2.it.preset(p1, p2);
                			if(bi.hasact){
                				btInfo.setText(bi.resultInfo);
                			}
                			
                			if(bi.nextCanDo){
                				bi=p1.it.action(p2, p1);
                    			btInfo.setText(bi.resultInfo);
                			}
                			
                		}
                		else{
                			BattleInfo bi=p1.it.preset(p2, p1);
                			if(bi.hasact){
                				btInfo.setText(bi.resultInfo);
                			}
                			
                			if(bi.nextCanDo){
                				bi=p2.it.action(p1, p2);
                    			btInfo.setText(bi.resultInfo);
                			}
                		}
                		
                	}
            					
            	}
            	
            	
            	
            	
            	//btInfo.setText(String.valueOf(GameData.i) );
            	if(GameData.me.currenthp>0){
                      mehp.setText(String.valueOf(GameData.me.currenthp)+"/"+String.valueOf(GameData.me.hp));
                      
            	}
            	else
            	{
            		  mehp.setText(String.valueOf(0)+"/"+String.valueOf(GameData.me.hp));
            	}
            	
            	if(GameData.op.currenthp>0){
                    ophp.setText(String.valueOf(GameData.op.currenthp)+"/"+String.valueOf(GameData.op.hp));
                    
          	     }
          	     else
          	     {
          		    ophp.setText(String.valueOf(0)+"/"+String.valueOf(GameData.op.hp));
          	     }
            	
            	
                mHandler.postDelayed(this, 2000);
            }
        });
		
		//finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battle, menu);
		
		

		
		return true;
	}

}
