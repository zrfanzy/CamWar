package com.example.camwar;

import java.util.Random;

public class Dice extends Item {
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
		int p=Math.abs(random.nextInt()%6)+1;
		
		damage=p*strength*2;
		
		op.currenthp=op.currenthp-damage;
		bt.nextCanDo=true;
		bt.hasact=true;
		bt.resultInfo=me.name+"使用骰子掷出了"+p+"点，对"+op.name+"造成了"+damage+"点伤害！";
		
		return bt;
	}

}
