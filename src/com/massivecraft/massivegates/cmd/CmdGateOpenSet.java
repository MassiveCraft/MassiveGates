package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARBoolean;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
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
		
		// Args
		this.addRequiredArg("flag");
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.OPEN_SET.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		Boolean newState = this.arg(0, ARBoolean.get());
		
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
