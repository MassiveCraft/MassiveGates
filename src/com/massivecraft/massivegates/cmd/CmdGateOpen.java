package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore3.cmd.HelpCommand;
import com.massivecraft.mcore3.cmd.req.ReqIsPlayer;

public class CmdGateOpen extends GateCommand
{
	public CmdGateOpen()
	{
		super();
		this.addAliases("open");
		this.addSubCommand(new CmdGateOpenSet());
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
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
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}