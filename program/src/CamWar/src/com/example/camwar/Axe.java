package com.example.camwar;

import java.util.Random;


public class Axe extends Item{

	@Override
	public BattleInfo preset(Player op,Player me) {
		BattleInfo bt=new BattleInfo();
		bt.nextCanDo=true;
		bt.resultInfo="";
		bt.hasact=false;
		return bt;
	}

	@Override
	public BattleInfo action(Player op,Player me) {
		// TODO Auto-generated method stub
		BattleInfo bt=new BattleInfo();
		int damage=0; 
		Random random=new Random();
		
		
		int p=Math.abs(random.nextInt()%15);
		if(me.it.strength<p){
			damage=me.power*strength/15;
			bt.resultInfo=me.name+"使用斧头对"+op.name+"造成了"+damage+"点伤害！";
		}
		else
		{
			damage=me.power*strength/7;
			bt.resultInfo=me.name+"使用斧头造成暴击！对"+op.name+"造成了"+damage+"点伤害！";
		}
		
		op.currenthp=op.currenthp-damage;
		bt.nextCanDo=true;
		bt.hasact=true;
		
		
		return bt;
	}
}
