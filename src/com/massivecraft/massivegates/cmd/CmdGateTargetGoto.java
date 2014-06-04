package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;

public class CmdGateTargetGoto extends GateCommand
{
	public CmdGateTargetGoto()
	{
		this.addAliases("goto");
		this.addRequirements(ReqIsPlayer.get(), ReqGateSelected.get());
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