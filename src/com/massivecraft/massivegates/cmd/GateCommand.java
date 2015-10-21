package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.cmd.MassiveCommand;
import com.massivecraft.massivegates.entity.GSender;
import com.massivecraft.massivegates.entity.GSenderColl;

public abstract class GateCommand extends MassiveCommand
{	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public GSender gsender;
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void senderFields(boolean set)
	{
		this.gsender = set ? GSenderColl.get().get(this.sender) : null;
	}
	
}
