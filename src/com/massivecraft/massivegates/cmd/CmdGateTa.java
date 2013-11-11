package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.GateCommand;

// In the sub commands we will often have rows like this:
// trigger index action arg desc
//
// We use the corresponding colors:
// <lime> <rose ><k> <v> <i>

public class CmdGateTa extends GateCommand
{
	public CmdGateTa()
	{
		this.addAliases("ta");
		this.addSubCommand(new CmdGateTaAlt());
		this.addSubCommand(new CmdGateTaList());
		this.addSubCommand(new CmdGateTaAdd());
		this.addSubCommand(new CmdGateTaDel());
		this.setDesc("manage gate trigger-actions");
	}

}