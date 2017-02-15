package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.MConf;

import java.util.List;

public class CmdGateExitRemove extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateExitRemove()
	{
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(RequirementHasPerm.get(Perm.EXIT_REMOVE.id));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateExitRemove;
	}
	
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
