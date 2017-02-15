package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.command.MassiveCommandVersion;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivegates.MassiveGates;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.entity.MConf;

import java.util.List;

public class CmdGateVersion extends MassiveCommandVersion
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateVersion()
	{
		super(MassiveGates.get());
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.VERSION));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateVersion;
	}

}
