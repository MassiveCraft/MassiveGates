package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmd.type.TypeGate;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.MConf;

import java.util.List;

public class CmdGateSel extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateSel()
	{
		// Parameters
		this.addParameter(TypeGate.get(), "gate", "*get*");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.SELECT.id));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateSel;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		
		if ( ! this.argIsSet(0))
		{
			if (gate == null)
			{
				this.msg("<i>No gate selected.");
			}
			else
			{
				this.msg("<i>Currently selected: "+gate.getIdNameStringLong());
			}
			return;
		}
		
		gate = this.readArg();
		
		// Apply
		gsender.setSelectedGate(gate);
		if (me != null)
		{
			gate.visualizeFor(me);
		}
		
		// Inform
		this.msg("<i>Selected gate %s", gate.getIdNameStringLong());
	}
	
}
