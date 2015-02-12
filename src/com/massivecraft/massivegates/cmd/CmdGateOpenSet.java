package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARBoolean;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;

public class CmdGateOpenSet extends GateCommand
{
	public CmdGateOpenSet()
	{
		this.addAliases("set");
		this.addRequiredArg("flag");
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(new ReqHasPerm(Permission.OPEN_SET.node));
	}
	
	@Override
	public void perform() throws MassiveException
	{
		Gate gate = gme.getSelectedGate();
		
		Boolean newState = this.arg(0, ARBoolean.get());
		
		boolean currentState = gate.isOpen();
		String newStateStr = newState ? "<g>OPEN" : "<b>CLOSED";
		
		if (currentState == newState)
		{
			this.msg("<i>The gate <h>"+gate.getIdNameStringShort()+" <i>is already "+newStateStr+"<i>.");
			return;
		}
		
		gate.setOpen(newState);
		
		this.msg("<i>The gate <h>"+gate.getIdNameStringShort()+" <i>is now "+newStateStr+"<i>.");
	}
}