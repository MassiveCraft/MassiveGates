package com.massivecraft.massivegates.cmd;

import org.bukkit.Material;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmd.type.TypeGate;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateDelete extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateDelete()
	{
		// Aliases
		this.addAliases("delete", "rm", "remove");
		
		// Parameters
		this.addParameter(TypeGate.get(), "gate");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.DELETE.id));
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Gate gate = this.readArg();
		
		// Infrom
		this.msg("<i>Gate %s<i> was deleted.", gate.getIdNameStringLong());
		
		// Apply
		gate.setOpen(false);
		gate.fillContent(Material.AIR);
		gate.detach();
	}
	
}
