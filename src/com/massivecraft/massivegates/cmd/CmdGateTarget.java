package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateTarget extends GateCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	protected final static String firstHelpLine = "<i>The gate will teleport users to the target.";
	
	public CmdGateTargetHere cmdMassiveGatesTargetHere = new CmdGateTargetHere();
	public CmdGateTargetGate cmdMassiveGatesTargetGate = new CmdGateTargetGate();
	public CmdGateTargetGoto cmdMassiveGatesTargetGoto = new CmdGateTargetGoto();
	public CmdGateTargetRemove cmdMassiveGatesTargetRemove = new CmdGateTargetRemove();
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateTarget()
	{
		// Aliases
		this.addAliases("target");
		
		// Children
		this.addChild(this.cmdMassiveGatesTargetHere);
		this.addChild(this.cmdMassiveGatesTargetGate);
		this.addChild(this.cmdMassiveGatesTargetGoto);
		this.addChild(this.cmdMassiveGatesTargetRemove);
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.TARGET.node));
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
			ret.add("<i>Current target: "+gate.getTarget().getDesc());
		}
		
		return ret;
	}

}
