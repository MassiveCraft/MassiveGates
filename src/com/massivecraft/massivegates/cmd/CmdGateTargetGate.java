package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdarg.TypeGate;
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
		
		// Parameters
		this.addParameter(TypeGate.get(), "targetgate");
		
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
		Gate target = this.readArg( );
		
		// Apply
		gate.getTarget().setGate(target);
		
		// Inform
		this.msg("<i>Gate %s<i>: Target is now gate %s<i>.", gate.getIdNameStringShort(), target.getIdNameStringShort());
	}
	
}
