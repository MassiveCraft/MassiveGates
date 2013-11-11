package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdGateExitRemove extends GateCommand
{
	public CmdGateExitRemove()
	{
		this.addAliases("rm","remove");
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(new ReqHasPerm(Permission.EXIT_REMOVE.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		gate.setExit(null);
		this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>exit was removed.");
	}
}