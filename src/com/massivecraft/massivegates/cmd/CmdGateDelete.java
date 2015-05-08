package com.massivecraft.massivegates.cmd;

import org.bukkit.Material;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdarg.ARGate;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateDelete extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateDelete()
	{
		// Aliases
		this.addAliases("del", "delete", "rm", "remove");
		
		// Args
		this.addArg(ARGate.get(), "gate");
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.DELETE.node));
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
