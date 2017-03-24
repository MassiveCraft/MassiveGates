package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.MConf;

import java.util.ArrayList;
import java.util.List;

public class CmdGateName extends GateCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	protected final static String firstHelpLine = "<i>Gates can have a name for easier selection.";
	
	public CmdGateNameSet cmdMassiveGatesNameSet = new CmdGateNameSet();
	public CmdGateNameRemove cmdMassiveGatesNameRemove = new CmdGateNameRemove();
	
	// -------------------------------------------- //
	// CONSTRUCTOR
	// -------------------------------------------- //
	
	public CmdGateName()
	{
		// Children
		this.addChild(this.cmdMassiveGatesNameSet);
		this.addChild(this.cmdMassiveGatesNameRemove);
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(RequirementHasPerm.get(Perm.NAME.id));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getHelp()
	{
		List<String> ret = new ArrayList<>(2);
		ret.add(firstHelpLine);
		
		if (Perm.NAME_GET.has(sender))
		{
			Gate gate = gsender.getSelectedGate();
			ret.add("<i>Current name: <v>"+(gate.getName()==null? "*NONE*" : gate.getName()));
		}
		
		return ret;
	}
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateName;
	}

}
