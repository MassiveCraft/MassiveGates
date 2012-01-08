package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore1.cmd.HelpCommand;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;

public class CmdGateName extends GateCommand
{
	public CmdGateName()
	{
		super();
		this.addAliases("name");
		this.addSubCommand(new CmdGateNameSet());
		this.addSubCommand(new CmdGateNameRemove());
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.setDesc("manage gate name");
	}
	
	protected final static String firstHelpLine = "<i>Gates can have a name for easier selection.";
	@Override
	public List<String> getHelp()
	{
		List<String> ret = new ArrayList<String>(2);
		ret.add(firstHelpLine);
		
		if (Permission.NAME_GET.has(sender))
		{
			Gate gate = gme.getSelectedGate();
			ret.add("<i>Current name: <v>"+(gate.getName()==null?"*NONE*":gate.getName()));
		}
		
		return ret;
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}