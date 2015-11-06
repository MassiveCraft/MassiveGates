package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateSee extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateSee()
	{
		// Aliases
		this.addAliases("see");
		
		// Requirements
		this.addRequirements(RequirementIsPlayer.get(), ReqGateSelected.get());
		this.addRequirements(RequirementHasPerm.get(Perm.SEE.node));
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
		gate.visualizeFor(me);
		
		// Inform
		this.msg("<i>Visualized <h>%s<i>.", gate.getIdNameStringShort());
	}
	
}
