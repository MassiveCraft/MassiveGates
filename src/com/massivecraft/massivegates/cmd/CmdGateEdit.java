package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.mcore1.cmd.HelpCommand;

public class CmdGateEdit extends GateCommand
{
	public CmdGateEdit()
	{
		super();
		this.addAliases("e", "edit");
		this.addSubCommand(new CmdGateEditThat());
		this.addSubCommand(new CmdGateEditFlood());
		this.addSubCommand(new CmdGateEditClear());
		this.setDesc("edit gate shape");
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}