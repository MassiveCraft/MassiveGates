package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARInteger;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdarg.ARAction;
import com.massivecraft.massivegates.cmdarg.ARTrigger;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.Trigger;

public class CmdGateTaDel extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateTaDel()
	{
		// Aliases
		this.addAliases("del");
		
		// Args
		this.addRequiredArg("trigger|all");
		this.addOptionalArg("index|action|all","all");
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.TA_DEL.node));
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		
		if (this.arg(0).equalsIgnoreCase("all"))
		{
			gate.delActions();
			this.msg("<i>Gate %s<i>: All actions were deleted.", gate.getIdNameStringShort());
			return;
		}
		
		// Fetch the Trigger
		Trigger trigger = this.arg(0, ARTrigger.get());
		
		if ( ! this.argIsSet(1) || this.arg(1).equalsIgnoreCase("all"))
		{
			int count = gate.delActions(trigger);
			this.msg("<i>Gate %s<i>: All (<h>%s<i>) actions for trigger <lime>%s<i> were deleted.", gate.getIdNameStringShort(), count, trigger.getName());
			return;
		}
		
		// The action may either be an index or an action id
		if (isNumeric(this.arg(1)))
		{
			// Delete by index
			Integer index = this.arg(1, ARInteger.get());
			
			// Make it zero-based while deleting
			index -= 1;
			boolean success = gate.delAction(trigger, index);
			index += 1;
			
			if (success)
			{
				this.msg("<i>Gate %s<i>: Action index <rose>%s <i>for trigger <lime>%s<i> was deleted.", gate.getIdNameStringShort(), index, trigger.getName());
			}
			else
			{
				this.msg("<i>Gate %s<i>: There is no index <rose>%s <i>for trigger <lime>%s<i>.", gate.getIdNameStringShort(), index, trigger.getName());
			}
		}
		else
		{
			// Delete by actionId
			// Fetch the Action
			Action action = this.arg(1, ARAction.get());
			
			int count = gate.delActions(trigger, action);
			this.msg("<i>Gate %s<i>: %s <pink>%s-actions <i>for trigger <lime>%s<i> were deleted.", gate.getIdNameStringShort(), count, action.getName(), trigger.getName());
		}
		
	}
	
	// -------------------------------------------- //
	// PRIVATE
	// -------------------------------------------- //
	
	private boolean isNumeric(String s)
	{
		if (s == null) return false;
		for (int i = 0; i < s.length(); i++)
		{
			if ( ! Character.isDigit(s.charAt(i))) return false;
		}
		return true;
	}
	
}
