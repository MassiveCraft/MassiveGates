package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.mcore1.cmd.HelpCommand;

public class CmdGateFx extends GateCommand
{
	public CmdGateFx()
	{
		super();
		this.addAliases("fx");
		this.addSubCommand(new CmdGateFxAlt());
		this.addSubCommand(new CmdGateFxList());
		this.addSubCommand(new CmdGateFxAdd());
		this.addSubCommand(new CmdGateFxDel());
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}