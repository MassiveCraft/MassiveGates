package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARString;
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
		this.addArg(ARString.get(), "name", "*none*", true);
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.NEW.node));
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Gate gate = GateColl.get().create();
		String name = this.readArg();
		
		// Apply
		gate.setName(name);
		gsender.setSelectedGate(gate);
		
		// Inform
		this.msg("<i>Created and selected the new gate: "+gate.getIdNameStringLong());
	}
	
}
