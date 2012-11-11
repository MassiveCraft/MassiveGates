package com.massivecraft.massivegates;

import com.massivecraft.mcore5.cmd.MCommand;

public abstract class GateCommand extends MCommand
{
	public P p;
	public GateCommand()
	{
		super();
		this.p = P.p;
	}
	
	@Override
	public P p()
	{
		return P.p;
	}
	
	public GPlayer gme;
	@Override
	public void fixSenderVars()
	{
		this.gme = GPlayerColl.i.get(this.me);
	}
	
}
