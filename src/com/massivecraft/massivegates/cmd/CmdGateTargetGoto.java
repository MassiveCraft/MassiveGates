package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.LocWrap;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;
import com.massivecraft.mcore4.cmd.req.ReqIsPlayer;

public class CmdGateTargetGoto extends GateCommand
{
	public CmdGateTargetGoto()
	{
		super();
		this.addAliases("goto");
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.TARGET_GOTO.node));
	}
	
	@Override
	public void perform()
	{
		
		// TODO: Unreachable location check.
		
		Gate gate = gme.getSelectedGate();
		LocWrap locw = gate.getTarget();
		if (locw != null)
		{
			me.teleport(locw.getLocation());
			this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>teleportet to target:");
			this.msg(gate.getTargetDesc());
		}
		else
		{
			this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>does not have a target.");
		}
	}
}