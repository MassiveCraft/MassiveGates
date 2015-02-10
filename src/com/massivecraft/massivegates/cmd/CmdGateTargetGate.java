package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdarg.ARGate;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateTargetGate extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateTargetGate()
	{
		// Aliases
		this.addAliases("gate");
		
		// Args
		this.addRequiredArg("targetgate");
		
		// Requirement
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.TARGET_GATE.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		Gate target = this.arg(0, ARGate.get());
		
		// Apply
		gate.getTarget().setGate(target);
		
		// Inform
		this.msg("<i>Gate %s<i>: Target is now gate %s<i>.", gate.getIdNameStringShort(), target.getIdNameStringShort());
	}
	
}
