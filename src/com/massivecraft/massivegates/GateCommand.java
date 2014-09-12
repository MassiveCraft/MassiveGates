package com.massivecraft.massivegates;

import com.massivecraft.massivecore.cmd.MassiveCommand;

public abstract class GateCommand extends MassiveCommand
{	
	public GSender gme;
	
	@Override
	public void fixSenderVars()
	{
		this.gme = GSenderColl.i.get(this.sender);
	}
	
	@Override
	public void unsetSenderVars()
	{
		this.gme = null;
	}
	
}
