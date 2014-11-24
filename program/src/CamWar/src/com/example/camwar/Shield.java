package com.example.camwar;

import java.util.Random;

public class Shield extends Item{

	@Override
	public BattleInfo preset(Player op,Player me) {
		BattleInfo bt=new BattleInfo();
		Random random=new Random();
		int p=Math.abs(random.nextInt()%15);
		
		bt.nextCanDo=true;
		bt.resultInfo="";
		bt.hasact=false;
		
		if(p<=me.it.strength){
			bt.nextCanDo=false;
			bt.resultInfo=me.name+"ʹ�ö��Ƶֵ���һ�ι���!";
			bt.hasact=true;
		}
		
		
		
		return bt;
	}

	@Override
	public BattleInfo action(Player op,Player me) {
		// TODO Auto-generated method stub
		BattleInfo bt=new BattleInfo();
		int damage=0; 
		
		damage=me.currenthp*strength/20;
		
		
		
		
		op.currenthp=op.currenthp-damage;
		bt.nextCanDo=true;
		bt.hasact=true;
		bt.resultInfo=me.name+"ʹ�ö���ײ��"+op.name+"�����"+damage+"���˺���";
		
		return bt;
	}

}
