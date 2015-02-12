package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdarg.ARGate;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;

public class CmdGateTargetGate extends GateCommand
{
	public CmdGateTargetGate()
	{
		this.addAliases("gate");
		this.addRequiredArg("targetgate");
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(new ReqHasPerm(Permission.TARGET_GATE.node));
	}
	
	@Override
	public void perform() throws MassiveException
	{
		Gate gate = gme.getSelectedGate();
		
		Gate target = this.arg(0, ARGate.get());
		
		gate.getTarget().setGate(target);
		this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>now has target gate "+target.getIdNameStringShort()+"<i>.");
	}
}