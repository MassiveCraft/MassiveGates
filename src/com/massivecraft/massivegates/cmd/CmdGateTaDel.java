package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.Trigger;
import com.massivecraft.mcore3.cmd.req.ReqHasPerm;
import com.massivecraft.mcore3.cmd.req.ReqIsPlayer;

public class CmdGateTaDel extends GateCommand
{
	public CmdGateTaDel()
	{
		super();
		this.addAliases("del");
		this.addRequiredArg("trigger|all");
		this.addOptionalArg("index|action|all","all");
		
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.TA_DEL.node));
	}
	
	public boolean isNumeric(String s)
	{
		if (s == null) return false;
		for (int i = 0; i < s.length(); i++)
		{
			if ( ! Character.isDigit(s.charAt(i))) return false;
		}
		return true;
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
		
		if ( ! this.argIsSet(1) || this.arg(1).equalsIgnoreCase("all"))
		{
			int count = gate.delActions(trigger);
			this.msg("<i>Gate "+gate.getIdNameStringShort()+"<i> deleted all (<h>"+count+"<i>) actions for trigger <lime>"+trigger.getName()+"<i>.");
			return;
		}
		
		// The action may either be an index or an action id
		if (isNumeric(this.arg(1)))
		{
			// Delete by index
			Integer index = this.argAs(1, Integer.class);
			if (index == null) return;
			
			// Make it zero-based while deleting
			index -= 1;
			boolean success = gate.delAction(trigger, index);
			index += 1;
			
			if (success)
			{
				this.msg("<i>Gate "+gate.getIdNameStringShort()+"<i> deleted action index <rose>"+index+" <i>for trigger <lime>"+trigger.getName()+"<i>.");
			}
			else
			{
				this.msg("<b>Gate "+gate.getIdNameStringShort()+"<b> there is no index <rose>"+index+" <i>for trigger <lime>"+trigger.getName()+"<i>.");
			}
		}
		else
		{
			// Delete by actionId
			// Fetch the Action
			Action action = this.argAs(1, Action.class);
			if (action == null) return;
			
			int count = gate.delActions(trigger, action);
			this.msg("<i>Gate "+gate.getIdNameStringShort()+"<i> deleted "+count+" <pink>"+action.getName()+"-action <i>for trigger <lime>"+trigger.getName()+"<i>.");
		}
		
		
	}
}