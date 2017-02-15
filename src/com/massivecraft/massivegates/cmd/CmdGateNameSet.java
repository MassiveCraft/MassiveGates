package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.MConf;

import java.util.List;

public class CmdGateNameSet extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateNameSet()
	{
		// Parameters
		this.addParameter(TypeString.get(), "name");
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(RequirementHasPerm.get(Perm.NAME_SET.id));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateNameSet;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		String name = this.readArg();
		
		// Apply
		gate.setName(name);
		
		// Inform
		this.msg("<i>Name updated: Gate %s<i>.", gate.getIdNameStringShort());
	}
	
}
