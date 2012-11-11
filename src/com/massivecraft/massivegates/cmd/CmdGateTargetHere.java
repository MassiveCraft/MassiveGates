package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore5.cmd.req.ReqHasPerm;
import com.massivecraft.mcore5.cmd.req.ReqIsPlayer;

public class CmdGateTargetHere extends GateCommand
{
	public CmdGateTargetHere()
	{
		super();
		this.addAliases("here", "set");
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.TARGET_HERE.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		gate.getTarget().setLocation(me.getLocation());
		this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>now has this as target location.");
	}
}