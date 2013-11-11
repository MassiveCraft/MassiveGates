package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;

public class CmdGateOpen extends GateCommand
{
	public CmdGateOpen()
	{
		this.addAliases("open");
		this.addSubCommand(new CmdGateOpenSet());
		this.addRequirements(ReqGateSelected.get());
		this.setDesc("manage gate open/closed state");
	}
	
	protected final static String firstHelpLine = "<i>The content material is decided by the open-state.";
	@Override
	public List<String> getHelp()
	{
		List<String> ret = new ArrayList<String>(2);
		ret.add(firstHelpLine);
		
		if (Permission.OPEN_GET.has(sender))
		{
			Gate gate = gme.getSelectedGate();
			boolean currentState = gate.isOpen();
			String currentStateStr = currentState ? "<g>OPEN" : "<b>CLOSED";
			ret.add("<i>The gate is currently "+currentStateStr+"<i>.");
		}
		
		return ret;
	}

}