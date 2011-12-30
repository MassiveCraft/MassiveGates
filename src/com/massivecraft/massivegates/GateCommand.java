package com.massivecraft.massivegates;

import com.massivecraft.mcore1.cmd.MCommand;

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
}
