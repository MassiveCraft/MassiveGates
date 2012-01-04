package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.when.Action;
import com.massivecraft.massivegates.when.Trigger;
import com.massivecraft.mcore1.cmd.req.ReqHasPerm;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;

public class CmdGateWhenDel extends GateCommand
{
	public CmdGateWhenDel()
	{
		super();
		this.addAliases("del");
		this.addRequiredArg("trigger|all");
		this.addRequiredArg("action|all");
		
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.WHEN_DEL.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		
		if (this.arg(0).equalsIgnoreCase("all"))
		{
			gate.delActions();
			this.msg("<i>Gate "+gate.getIdNameStringShort()+"<i> deleted all actions.");
			return;
		}
		
		// Fetch the Trigger
		Trigger trigger = this.argAs(0, Trigger.class);
		if (trigger == null) return;
		
		if (this.arg(1).equalsIgnoreCase("all"))
		{
			gate.delActions(trigger);
			this.msg("<i>Gate "+gate.getIdNameStringShort()+"<i> deleted all actions for trigger <lime>"+trigger.getName()+"<i>.");
			return;
		}
		
		// Fetch the Action
		Action action = this.argAs(1, Action.class);
		if (action == null) return;
		
		gate.delAction(trigger, action);
		this.msg("<i>Gate "+gate.getIdNameStringShort()+"<i> deleted action <pink>"+action.getName()+" <i>for trigger <lime>"+trigger.getName()+"<i>.");
	}
}