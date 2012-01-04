package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.mcore1.cmd.HelpCommand;

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
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}