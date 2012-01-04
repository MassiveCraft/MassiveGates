package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;

public class CmdGateOpen extends GateCommand
{
	public CmdGateOpen()
	{
		super();
		this.addAliases("open");
		this.addOptionalArg("state", "get");
		
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		boolean currentState = gate.isOpen();
		String currentStateStr = currentState ? "<g>OPEN" : "<b>CLOSED";
		
		if ( ! this.argIsSet(0))
		{
			this.msg("<i>The gate <h>"+gate.getIdNameStringShort()+" <i>is currently "+currentStateStr+"<i>.");
			return;
		}
		
		Boolean newState = this.argAs(0, Boolean.class);
		if (newState == null) return;
		if (currentState == newState)
		{
			this.msg("<i>The gate <h>"+gate.getIdNameStringShort()+" <i>is already "+currentStateStr+"<i>.");
			return;
		}
		
		String newStateStr = newState ? "<g>OPEN" : "<b>CLOSED";
		
		gate.setOpen(newState);
		
		this.msg("<i>The gate <h>"+gate.getIdNameStringShort()+" <i>is now "+newStateStr+"<i>.");
	}
}