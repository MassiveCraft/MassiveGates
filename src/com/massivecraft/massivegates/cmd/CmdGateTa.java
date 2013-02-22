package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.mcore.cmd.HelpCommand;

// In the sub commands we will often have rows like this:
// trigger index action arg desc
//
// We use the corresponding colors:
// <lime> <rose ><k> <v> <i>

public class CmdGateTa extends GateCommand
{
	public CmdGateTa()
	{
		super();
		this.addAliases("ta");
		this.addSubCommand(new CmdGateTaAlt());
		this.addSubCommand(new CmdGateTaList());
		this.addSubCommand(new CmdGateTaAdd());
		this.addSubCommand(new CmdGateTaDel());
		this.setDesc("manage gate trigger-actions");
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}