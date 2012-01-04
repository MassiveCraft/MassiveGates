package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Gates;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;

public class CmdGateNew extends GateCommand
{
	public CmdGateNew()
	{
		this.addAliases("new");
		this.addOptionalArg("name", "*none*");
		this.setErrorOnToManyArgs(false);
		this.addRequirements(ReqIsPlayer.getInstance());
	}

	@Override
	public void perform()
	{
		Gate gate = Gates.i.create();
		String name = this.argConcatFrom(0);
		gate.setName(name);
		gme.setSelectedGate(gate);
		this.msg("<i>Created and selected the new gate: "+gate.getIdNameStringLong());
	}
	
}