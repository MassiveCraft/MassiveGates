package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.GateColl;

public class CmdGateNew extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateNew()
	{
		// Aliases
		this.addAliases("new", "create");
		
		// Args
		this.addOptionalArg("name", "*none*");
		this.setErrorOnToManyArgs(false);
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.NEW.node));
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		// Args
		Gate gate = GateColl.get().create();
		String name = this.argConcatFrom(0);
		
		// Apply
		gate.setName(name);
		gsender.setSelectedGate(gate);
		
		// Inform
		this.msg("<i>Created and selected the new gate: "+gate.getIdNameStringLong());
	}
	
}
