package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Gates;

public class CmdGateSelect extends GateCommand
{
	public CmdGateSelect()
	{
		this.addAliases("select", "sel");
		this.addOptionalArg("idOrName", "*looking at*");
		this.setErrorOnToManyArgs(false);
	}

	@Override
	public void perform()
	{
		if (this.argIsSet(0))
		{
			String idOrName = this.argConcatFrom(0);
			
		}
		
		
		if (idOrName)
		
		Gate gate = Gates.i.create();
		
		gate.setName(name);
		
		this.msg("<i>You created a new gate.");
		this.msg("<i>Id: <h>%s", gate.getId());
		this.msg("<i>Name: <h>%s", gate.getName() == null ? "*this gate has no name*" : gate.getName());
	}
}
