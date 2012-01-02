package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.mcore1.cmd.HelpCommand;

public class CmdGateSelect extends GateCommand
{
	public CmdGateSelect()
	{
		super();
		this.addAliases("sel", "selection");
		this.addSubCommand(new CmdGateSelectSet());
		this.addSubCommand(new CmdGateSelectGet());
		this.addSubCommand(new CmdGateSelectDel());
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}