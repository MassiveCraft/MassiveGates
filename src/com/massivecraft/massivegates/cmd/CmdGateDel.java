package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.mcore1.cmd.req.ReqHasPerm;

public class CmdGateDel extends GateCommand
{
	public CmdGateDel()
	{
		this.addAliases("del", "delete", "rem", "remove");
		this.addRequiredArg("gate");
		
		this.addRequirements(new ReqHasPerm(Permission.DELETE.node));
	}

	@Override
	public void perform()
	{
		Gate gate = this.argAs(0, Gate.class);
		if (gate == null) return;
		this.msg("<i>Gate deleted: "+gate.getIdNameStringLong());
		gate.setOpen(false);
		gate.detach();
	}
}
