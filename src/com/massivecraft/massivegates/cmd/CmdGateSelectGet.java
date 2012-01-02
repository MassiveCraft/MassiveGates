package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;

public class CmdGateSelectGet extends GateCommand
{
	public CmdGateSelectGet()
	{
		this.addAliases("get");
	}

	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		if (gate == null)
		{
			this.msg("<i>No gate selected.");
		}
		else
		{
			this.msg("Currently selected "+gate.getIdNameStringLong());
		}
	}
}