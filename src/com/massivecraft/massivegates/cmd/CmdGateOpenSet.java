package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeBooleanTrue;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateOpenSet extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateOpenSet()
	{
		// Aliases
		this.addAliases("set");
		
		// Parameters
		this.addParameter(TypeBooleanTrue.get(), "flag", true);
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(RequirementHasPerm.get(Perm.OPEN_SET.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		boolean newState = this.readArg();
		
		// Check for state
		boolean currentState = gate.isOpen();
		String newStateStr = newState ? Txt.parse("<g>OPEN") : Txt.parse("<b>CLOSED");
		
		if (currentState == newState)
		{
			this.msg("<i>The gate <h>%s <i>is already %s<i>.", gate.getIdNameStringShort(), newStateStr);
			return;
		}
		
		// Apply
		gate.setOpen(newState);
		
		// Inform
		this.msg("<i>The gate <h>%s <i>is now %s<i>.", gate.getIdNameStringShort(), newStateStr);
	}
	
}
