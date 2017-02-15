package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.GateColl;
import com.massivecraft.massivegates.entity.MConf;

import java.util.List;

public class CmdGateNew extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateNew()
	{
		// Parameters
		this.addParameter(TypeString.get(), "name", "*none*", true);
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.NEW.id));
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateNew;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Gate gate = GateColl.get().create();
		String name = this.readArg();
		
		// Apply
		gate.setName(name);
		gsender.setSelectedGate(gate);
		
		// Inform
		this.msg("<i>Created and selected the new gate: "+gate.getIdNameStringLong());
	}
	
}
