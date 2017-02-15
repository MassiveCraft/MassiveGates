package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.MConf;

public class CmdGateOpen extends GateCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	protected final static String firstHelpLine = "<i>The content material is decided by the open-state.";
	
	public CmdGateOpenSet cmdMassiveGatesOpenSet = new CmdGateOpenSet();
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateOpen()
	{
		// Children
		this.addChild(this.cmdMassiveGatesOpenSet);
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(RequirementHasPerm.get(Perm.OPEN.id));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateOpen;
	}
	
	@Override
	public List<String> getHelp()
	{
		List<String> ret = new ArrayList<String>(2);
		ret.add(firstHelpLine);
		
		if (Perm.OPEN_GET.has(sender))
		{
			Gate gate = gsender.getSelectedGate();
			boolean currentState = gate.isOpen();
			String currentStateStr = currentState ? "<g>OPEN" : "<b>CLOSED";
			ret.add(Txt.parse("<i>The gate is currently %s<i>.", currentStateStr));
		}
		
		return ret;
	}

}
