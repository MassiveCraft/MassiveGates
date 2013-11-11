package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;

public class CmdGateExit extends GateCommand
{
	public CmdGateExit()
	{
		this.addAliases("exit");
		this.addSubCommand(new CmdGateExitHere());
		this.addSubCommand(new CmdGateExitGoto());
		this.addSubCommand(new CmdGateExitRemove());
		this.addRequirements(ReqGateSelected.get());
		this.setDesc("manage gate exit");
	}
	
	protected final static String firstHelpLine = "<i>The gate exit is the location for arrivers.";
	@Override
	public List<String> getHelp()
	{
		List<String> ret = new ArrayList<String>(2);
		ret.add(firstHelpLine);
		
		if (Permission.TARGET_GET.has(sender))
		{
			Gate gate = gme.getSelectedGate();
			ret.add("<i>Current exit: "+gate.getExitDesc());
		}
		
		return ret;
	}

}