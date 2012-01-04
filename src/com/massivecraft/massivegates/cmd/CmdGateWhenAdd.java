package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.when.Action;
import com.massivecraft.massivegates.when.Trigger;
import com.massivecraft.mcore1.cmd.req.ReqHasPerm;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;

public class CmdGateWhenAdd extends GateCommand
{
	public CmdGateWhenAdd()
	{
		super();
		this.addAliases("add");
		this.addRequiredArg("trigger");
		this.addRequiredArg("action");
		
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.WHEN_ADD.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		
		// Fetch Trigger
		Trigger trigger = this.argAs(0, Trigger.class);
		if (trigger == null) return;
		
		// Fetch the Action
		Action action = this.argAs(1, Action.class);
		if (action == null) return;
		
		gate.addAction(trigger, action);
		this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>added:");
		this.msg("<lime>"+trigger.getName()+" <pink>"+action.getName()+" <i>"+action.getDesc());
	}
}