package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.LocWrap;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore1.cmd.req.ReqHasPerm;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;

public class CmdGateExitGoto extends GateCommand
{
	public CmdGateExitGoto()
	{
		super();
		this.addAliases("goto");
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.EXIT_GOTO.node));
	}
	
	@Override
	public void perform()
	{
		
		// TODO: Unreachable location check.
		
		Gate gate = gme.getSelectedGate();
		LocWrap locw = gate.getExit();
		if (locw != null)
		{
			me.teleport(locw.getLocation());
			this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>teleportet to exit:");
			this.msg(gate.getExitDesc());
		}
		else
		{
			this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>does not have an exit.");
		}
	}
}