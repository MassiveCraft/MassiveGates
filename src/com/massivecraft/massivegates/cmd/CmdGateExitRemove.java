package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateExitRemove extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateExitRemove()
	{
		// Aliases
		this.addAliases("rm", "remove");
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.EXIT_REMOVE.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		// Apply
		Gate gate = gsender.getSelectedGate();
		gate.setExit(null);
		
		// Inform
		message(Txt.parse("<i>Gate %s<i>: The exit was removed.", gate.getIdNameStringShort()));
	}
}
