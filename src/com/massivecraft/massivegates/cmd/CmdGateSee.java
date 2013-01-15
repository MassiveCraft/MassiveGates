package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore5.cmd.req.ReqHasPerm;
import com.massivecraft.mcore5.cmd.req.ReqIsPlayer;

public class CmdGateSee extends GateCommand
{
	public CmdGateSee()
	{
		super();
		this.addAliases("see");
		
		this.addRequirements(ReqIsPlayer.get(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.SEE.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		gate.visualizeFor(me);
		this.msg("<i>Visualized <h>"+gate.getIdNameStringShort()+"<i>.");
	}
}