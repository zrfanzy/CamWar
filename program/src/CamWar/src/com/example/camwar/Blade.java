package com.example.camwar;

public class Blade extends Item{

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
		if(me.power>op.power){
			damage=(me.power-op.power)*strength;
		}
		else
		{
			damage=strength;
		}
		
		op.currenthp=op.currenthp-damage;
		bt.nextCanDo=true;
		bt.hasact=true;
		bt.resultInfo=me.name+"使用长剑对"+op.name+"造成了"+damage+"点伤害！";
		
		return bt;
	}

}
