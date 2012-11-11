package com.massivecraft.massivegates.cmd;

import org.bukkit.Material;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdarg.ARGate;
import com.massivecraft.mcore5.cmd.req.ReqHasPerm;

public class CmdGateDelete extends GateCommand
{
	public CmdGateDelete()
	{
		this.addAliases("del", "delete", "rm", "remove");
		this.addRequiredArg("gate");
		
		this.addRequirements(new ReqHasPerm(Permission.DELETE.node));
	}

	@Override
	public void perform()
	{
		Gate gate = this.arg(0, ARGate.get());
		if (gate == null) return;
		this.msg("<i>Gate deleted: "+gate.getIdNameStringLong());
		gate.setOpen(false);
		gate.fillContent(Material.AIR);
		gate.detach();
	}
}
