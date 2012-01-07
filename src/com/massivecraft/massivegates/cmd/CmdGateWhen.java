package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.mcore1.cmd.HelpCommand;

// In the sub commands we will often have rows like this:
// trigger action arg desc
//
// We use the corresponding colors:
// <lime> <k> <v> <i>

public class CmdGateWhen extends GateCommand
{
	public CmdGateWhen()
	{
		super();
		this.addAliases("when");
		this.addSubCommand(new CmdGateWhenAlt());
		this.addSubCommand(new CmdGateWhenList());
		this.addSubCommand(new CmdGateWhenAdd());
		this.addSubCommand(new CmdGateWhenDel());
		this.setDesc("manage gate actions");
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}