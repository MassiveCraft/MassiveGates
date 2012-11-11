package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore5.cmd.HelpCommand;
import com.massivecraft.mcore5.cmd.req.ReqIsPlayer;

public class CmdGateTarget extends GateCommand
{
	public CmdGateTarget()
	{
		super();
		this.addAliases("target");
		this.addSubCommand(new CmdGateTargetHere());
		this.addSubCommand(new CmdGateTargetGate());
		this.addSubCommand(new CmdGateTargetRubberserver());
		this.addSubCommand(new CmdGateTargetGoto());
		this.addSubCommand(new CmdGateTargetRemove());
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.setDesc("manage gate target");
	}
	
	protected final static String firstHelpLine = "<i>The gate will teleport users to the target.";
	@Override
	public List<String> getHelp()
	{
		List<String> ret = new ArrayList<String>(2);
		ret.add(firstHelpLine);
		
		if (Permission.TARGET_GET.has(sender))
		{
			Gate gate = gme.getSelectedGate();
			ret.add("<i>Current target: "+gate.getTarget().getDesc());
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