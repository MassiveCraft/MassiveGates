package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.MConf;

import java.util.List;

public class CmdGateTargetRemove extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateTargetRemove()
	{
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(RequirementHasPerm.get(Perm.TARGET_REMOVE.id));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateTargetRemove;
	}
	
	@Override
	public void perform()
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		
		// Apply
		gate.getTarget().remove();
		
		// Inform
		this.msg("<i>Gate %s<i>: The target was removed.", gate.getIdNameStringShort());
	}
	
}
