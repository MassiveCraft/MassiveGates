package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.GateCommand;

public class CmdGateEdit extends GateCommand
{
	public CmdGateEdit()
	{
		this.addAliases("e", "edit");
		this.addSubCommand(new CmdGateEditThat());
		this.addSubCommand(new CmdGateEditFlood());
		this.addSubCommand(new CmdGateEditClear());
		this.setDesc("edit gate shape");
	}

}