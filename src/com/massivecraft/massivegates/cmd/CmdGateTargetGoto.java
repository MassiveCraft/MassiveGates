package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore5.cmd.req.ReqHasPerm;
import com.massivecraft.mcore5.cmd.req.ReqIsPlayer;

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
		Gate gate = gme.getSelectedGate();
		
		if (!gate.getTarget().exists())
		{
			this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>does not have a target.");
		}
		else
		{
			this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>teleportet to target:");
			this.msg(gate.getTarget().getDesc());
			gate.getTarget().teleport(me);
		}
	}
}