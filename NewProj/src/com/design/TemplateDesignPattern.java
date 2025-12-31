package com.design;

abstract class TemplateMethods
{
	public abstract void players();
	public abstract void playerInfo();
	
	public final void completeTeam()
	{
		this.players();
		this.playerInfo();
	}
}

class PlayerInfoImpl extends TemplateMethods
{

	@Override
	public void players() {
		
		System.out.println("players data not added");
	}

	@Override
	public void playerInfo() {
		
		System.out.println("PlayerInfo overrided");
		
	}
	
}

class PlayersImpl extends TemplateMethods
{

	@Override
	public void players() {
		
		System.out.println("Players data from Impl2");
	}

	@Override
	public void playerInfo() {
		
		System.out.println("Playsers info from Impl2");
	}	
}

public class TemplateDesignPattern {
	
	public static void main(String[] args) {
		
		TemplateMethods obj1 = new PlayerInfoImpl();
		
		TemplateMethods obj2 = new PlayersImpl();
		
		obj1.completeTeam();
		obj2.completeTeam();
	}
}
