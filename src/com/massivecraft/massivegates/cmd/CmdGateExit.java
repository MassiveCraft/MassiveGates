package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore1.cmd.HelpCommand;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;

public class CmdGateExit extends GateCommand
{
	public CmdGateExit()
	{
		super();
		this.addAliases("exit");
		this.addSubCommand(new CmdGateExitHere());
		this.addSubCommand(new CmdGateExitGoto());
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
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
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
		
		
		/*// TODO: Many times the same prefix appears...
		
		Gate gate = gme.getSelectedGate();
		
		String action = this.arg(0);
		if (action == null) action = "get";
		
		if (action.equals("get"))
		{
			LocWrap exit = gate.getExit();
			if (exit == null)
			{
				this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>does not have an exit. You should set one.");
			}
			else
			{
				this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>exit is:");
				this.msg(exit.getDesc());
			}
		}
		else if (action.equals("set"))
		{
			gate.setExit(new LocWrap(me));
			this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>exit was set to:");
			this.msg(gate.getExit().getDesc());
		}
		else if (action.equals("goto"))
		{
			LocWrap exit = gate.getExit();
			if (exit == null)
			{
				this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>does not have an exit. You should set one.");
			}
			else
			{
				me.teleport(exit.getLocation());
				this.msg("<i>Teleported you to the exit for gate <h>"+gate.getIdNameStringShort()+"<i>.");
			}
		}
		else
		{
			this.msg("<b>Arg must be get|set|goto.");
		}*/
	}
}