package com.massivecraft.massivegates.cmd;

import java.util.List;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.Trigger;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;
import com.massivecraft.mcore4.cmd.req.ReqIsPlayer;
import com.massivecraft.mcore4.util.Perm;

public class CmdGateTaAdd extends GateCommand
{
	public CmdGateTaAdd()
	{
		super();
		this.addAliases("add");
		this.addRequiredArg("trigger");
		this.addRequiredArg("action");
		this.addOptionalArg("argument", "");
		this.setErrorOnToManyArgs(false);
		
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.TA_ADD.node));
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
		
		// Fetch the arg
		String arg = this.argConcatFrom(2);
		
		// Check the arg
		List<String> errors = action.checkArg(arg);
		if (errors != null && errors.size() > 0)
		{
			gme.msg(errors);
			return;
		}
		
		// Do you have the permission to add this action?
		String perm = "massivegates.action."+action.getId();
		System.out.println(perm);
		System.out.println(sender);
		if ( ! sender.hasPermission(perm))
		{
			this.msg(Perm.getForbiddenMessage(perm));
			return;
		}
		
		// Cool :) All is OK. Lets add it ^^
		gate.addAction(trigger, action, arg);
		this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>added: <lime>trigger <k>action <v>arg");
		this.msg("<lime>"+trigger.getName()+" <k>"+action.getName()+" <v>"+(arg==null?"*none*":arg));
	}
}