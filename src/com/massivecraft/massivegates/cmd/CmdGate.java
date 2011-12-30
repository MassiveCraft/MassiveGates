package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Conf;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.mcore1.cmd.HelpCommand;

public class CmdGate extends GateCommand
{
	public CmdGate()
	{
		super();
		this.addAliases(Conf.baseCommandAliases);
		this.addSubCommand(new CmdGateCreate());
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}