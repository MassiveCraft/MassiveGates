package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore5.cmd.req.ReqHasPerm;
import com.massivecraft.mcore5.cmd.req.ReqIsPlayer;

public class CmdGateTargetRemove extends GateCommand
{
	public CmdGateTargetRemove()
	{
		super();
		this.addAliases("remove");
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.TARGET_REMOVE.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		gate.getTarget().remove();
		this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>target was removed.");
	}
}