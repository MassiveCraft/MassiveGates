package com.massivecraft.massivegates;

import com.massivecraft.mcore5.cmd.MCommand;

public abstract class GateCommand extends MCommand
{	
	public GSender gme;
	
	@Override
	public void fixSenderVars()
	{
		this.gme = GSenderColl.i.get(this.sender);
	}
}
