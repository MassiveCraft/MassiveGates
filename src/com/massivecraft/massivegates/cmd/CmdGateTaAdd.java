package com.massivecraft.massivegates.cmd;

import java.util.List;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARString;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.PermUtil;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdarg.ARAction;
import com.massivecraft.massivegates.cmdarg.ARTrigger;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.Trigger;

public class CmdGateTaAdd extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateTaAdd()
	{
		// Aliases
		this.addAliases("add");
		
		// Args
		this.addArg(ARTrigger.get(), "trigger");
		this.addArg(ARAction.get(), "action");
		this.addArg(ARString.get(), "argument", "", true);
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.TA_ADD.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		Trigger trigger = this.readArg();
		Action action = this.readArg();
		String arg = this.readArg("");
		
		// TODO: merge this into the arg-check?
		// Do you have the permission to add this action?
		String perm = "massivegates.action."+action.getId();;
		if ( ! sender.hasPermission(perm))
		{
			this.msg(PermUtil.getDeniedMessage(perm));
			return;
		}
		
		// Check the arg
		List<String> errors = action.checkArg(arg);
		if (errors != null && errors.size() > 0)
		{
			gsender.msg(errors);
			return;
		}
		
		// Apply
		gate.addAction(trigger, action, arg);
		
		// Inform
		this.msg("<i>Gate %s<i>: added <lime>trigger <k>action <v>arg", gate.getIdNameStringShort());
		this.msg("<lime>%s <i>| <k>%s <i>| <v>%s", trigger.getName(), action.getName(), (arg==null?"*none*":arg));
	}
	
}
