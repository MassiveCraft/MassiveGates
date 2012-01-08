package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore1.cmd.req.ReqHasPerm;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;

public class CmdGateOpenSet extends GateCommand
{
	public CmdGateOpenSet()
	{
		super();
		this.addAliases("set");
		this.addRequiredArg("flag");
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.OPEN_SET.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		
		Boolean newState = this.argAs(0, Boolean.class);
		if (newState == null) return;
		
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