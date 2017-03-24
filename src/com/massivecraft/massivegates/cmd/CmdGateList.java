package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Parameter;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.pager.Pager;
import com.massivecraft.massivecore.pager.Stringifier;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.GateColl;
import com.massivecraft.massivegates.entity.MConf;

import java.util.List;

public class CmdGateList extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCTOR
	// -------------------------------------------- //
	
	public CmdGateList()
	{
		// Parameters
		this.addParameter(Parameter.getPage());
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.LIST.id));
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateList;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		int page = this.readArg();
		
		// Pager Create
		final Pager<Gate> pager = new Pager<>(this, "List Of Gates", page, GateColl.get().getAll(), new Stringifier<Gate>()
		{
			@Override
			public String toString(Gate gate, int index)
			{
				return gate.getIdNameStringShort();
			}
		});
		
		// Pager Message
		pager.message();
	}
	
}
