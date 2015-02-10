package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateNameRemove extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateNameRemove()
	{
		// Aliases
		this.addAliases("remove");
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.NAME_REMOVE.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		
		// Apply
		gate.setName(null);
		
		// Inform
		this.msg("<i>Gate %s<i>: The name was removed.", gate.getIdNameStringShort());
	}
	
}
