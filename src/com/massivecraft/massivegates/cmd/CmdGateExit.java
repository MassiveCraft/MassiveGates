package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateExit extends GateCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	protected final static String firstHelpLine = "<i>The gate exit is the location for arrivers.";
	
	public CmdGateExitHere cmdMassiveGatesExitHere = new CmdGateExitHere();
	public CmdGateExitGoto cmdMassiveGatesExitGoto = new CmdGateExitGoto();
	public CmdGateExitRemove cmdMassiveGatesExitRemove = new CmdGateExitRemove();
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateExit()
	{
		// Aliases
		this.addAliases("exit");
		
		// Children
		this.addChild(this.cmdMassiveGatesExitHere);
		this.addChild(this.cmdMassiveGatesExitGoto);
		this.addChild(this.cmdMassiveGatesExitRemove);
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.EXIT.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getHelp()
	{
		List<String> ret = new ArrayList<String>(2);
		ret.add(firstHelpLine);
		
		if (Perm.TARGET_GET.has(sender))
		{
			Gate gate = gsender.getSelectedGate();
			ret.add("<i>Current exit: "+gate.getExitDesc());
		}
		
		return ret;
	}

}
