package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.cmd.arg.ARString;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;

public class CmdGateTargetRubberserver extends GateCommand
{
	public CmdGateTargetRubberserver()
	{
		this.addAliases("rubberserver");
		this.addRequiredArg("name");
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(new ReqHasPerm(Permission.TARGET_RUBBERSERVER.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		
		String name = this.arg(0, ARString.get());
		if (name == null) return;
		
		gate.getTarget().setRubberServer(name);
		this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>now has target rubberserver <h>"+name+"<i>.");
	}
}