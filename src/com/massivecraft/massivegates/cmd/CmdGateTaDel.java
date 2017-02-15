package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Parameter;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeInteger;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmd.type.TypeAction;
import com.massivecraft.massivegates.cmd.type.TypeTrigger;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.MConf;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.Trigger;

import java.util.List;

public class CmdGateTaDel extends GateCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private Parameter parameter = new Parameter(TypeAction.get(), false, "index|action|all", "all");
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateTaDel()
	{
		// Parameters
		this.addParameter(TypeTrigger.get(), "trigger|all");
		this.addParameter(parameter);
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(RequirementHasPerm.get(Perm.TA_DEL.id));
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateTaDel;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		
		if (this.argAt(0).equalsIgnoreCase("all"))
		{
			gate.delActions();
			this.msg("<i>Gate %s<i>: All actions were deleted.", gate.getIdNameStringShort());
			return;
		}
		
		// Fetch the Trigger
		Trigger trigger = this.readArgAt(0);
		
		if ( ! this.argIsSet(1) || this.argAt(1).equalsIgnoreCase("all"))
		{
			int count = gate.delActions(trigger);
			this.msg("<i>Gate %s<i>: All (<h>%s<i>) actions for trigger <lime>%s<i> were deleted.", gate.getIdNameStringShort(), count, trigger.getName());
			return;
		}
		
		// The action may either be an index or an action id
		if (isNumeric(this.argAt(1)))
		{
			// Delete by index
			this.parameter.setType(TypeInteger.get());
			int index = this.readArgAt(1);
			
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
			this.parameter.setType(TypeAction.get());
			Action action = this.readArgAt(1);
			
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
