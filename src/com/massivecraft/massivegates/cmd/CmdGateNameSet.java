package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateNameSet extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateNameSet()
	{
		// Aliases
		this.addAliases("set");
		
		// Args
		this.addRequiredArg("name");
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.NAME_SET.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		String name = this.arg(0);
		
		// Apply
		gate.setName(name);
		
		// Inform
		this.msg("<i>Name updated: Gate %s<i>.", gate.getIdNameStringShort());
	}
	
}
