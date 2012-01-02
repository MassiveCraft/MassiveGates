package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;

public class CmdGateSelectDel extends GateCommand
{
	public CmdGateSelectDel()
	{
		this.addAliases("del");
	}

	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		
		if (gate == null)
		{
			this.msg("<i>No gate selected. Thus nothing to deselect.");
		}
		else
		{
			this.msg("<i>Deselected "+gate.getIdNameStringLong());
			gme.setSelectedGate(null);
		}
	}
}