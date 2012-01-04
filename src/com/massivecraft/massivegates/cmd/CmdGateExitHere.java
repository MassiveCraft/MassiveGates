package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.LocWrap;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore1.cmd.req.ReqHasPerm;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;

public class CmdGateExitHere extends GateCommand
{
	public CmdGateExitHere()
	{
		super();
		this.addAliases("here");
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.EXIT_HERE.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		gate.setExit(new LocWrap(me));
		this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>now has this as exit location.");
	}
}