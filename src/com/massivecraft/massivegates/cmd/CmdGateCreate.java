package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Gates;

public class CmdGateCreate extends GateCommand
{
	public CmdGateCreate()
	{
		this.addAliases("new", "create");
		this.addOptionalArg("name", "*none*");
		this.setErrorOnToManyArgs(false);
	}

	@Override
	public void perform()
	{
		Gate gate = Gates.i.create();
		String name = this.argConcatFrom(0);
		gate.setName(name);
		
		this.msg("<i>New gate created: "+gate.getIdNameStringLong());
	}
}
