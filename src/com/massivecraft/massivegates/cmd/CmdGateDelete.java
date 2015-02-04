package com.massivecraft.massivegates.cmd;

import org.bukkit.Material;

import com.massivecraft.massivecore.cmd.MassiveCommandException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdarg.ARGate;

public class CmdGateDelete extends GateCommand
{
	public CmdGateDelete()
	{
		this.addAliases("del", "delete", "rm", "remove");
		this.addRequiredArg("gate");
		
		this.addRequirements(new ReqHasPerm(Permission.DELETE.node));
	}

	@Override
	public void perform() throws MassiveCommandException
	{
		Gate gate = this.arg(0, ARGate.get());
		this.msg("<i>Gate deleted: "+gate.getIdNameStringLong());
		gate.setOpen(false);
		gate.fillContent(Material.AIR);
		gate.detach();
	}
}
