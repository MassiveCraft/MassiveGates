package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdGateNameRemove extends GateCommand
{
	public CmdGateNameRemove()
	{
		this.addAliases("remove");
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(new ReqHasPerm(Permission.NAME_REMOVE.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		gate.setName(null);
		this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>name was removed.");
	}
}