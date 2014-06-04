package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;

public class CmdGateTargetHere extends GateCommand
{
	public CmdGateTargetHere()
	{
		this.addAliases("here", "set");
		this.addRequirements(ReqIsPlayer.get(), ReqGateSelected.get());
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