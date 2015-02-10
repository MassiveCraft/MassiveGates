package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARString;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateTargetRubberserver extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateTargetRubberserver()
	{
		// Aliases
		this.addAliases("rubberserver");
		
		// Args
		this.addRequiredArg("name");
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.TARGET_RUBBERSERVER.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		String name = this.arg(0, ARString.get());
		
		// Apply
		gate.getTarget().setRubberServer(name);
		
		// Inform
		this.msg("<i>Gate %s<i>: Target now is rubberserver <h>%s<i>.", gate.getIdNameStringShort(), name);
	}
	
}
