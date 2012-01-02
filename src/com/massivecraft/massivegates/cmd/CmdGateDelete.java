package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;

public class CmdGateDelete extends GateCommand
{
	public CmdGateDelete()
	{
		this.addAliases("delete", "remove");
		this.addRequiredArg("id");
		this.setErrorOnToManyArgs(false);
	}

	@Override
	public void perform()
	{
		Gate gate = this.argAs(0, Gate.class);
		if (gate == null) return;
		this.msg("<i>Gate deleted: "+gate.getIdNameStringLong());
		gate.detach();
	}
}
