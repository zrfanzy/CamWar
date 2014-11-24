package com.example.camwar;

public abstract class Item {
	public int strength;
	
	public abstract BattleInfo preset(Player op,Player me);//"nothing" can move
	public abstract BattleInfo action(Player op,Player me);	
	

}
