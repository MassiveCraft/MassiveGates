package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore1.cmd.req.ReqHasPerm;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;

public class CmdGateEditClear extends GateCommand
{
	public CmdGateEditClear()
	{
		super();
		this.addAliases("clear");
		this.addRequiredArg("frame|content|all");
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.EDIT_CLEAR.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		
		char firstArgChar = this.arg(0).toLowerCase().charAt(0);
		
		if (firstArgChar == 'f')
		{
			gate.clearFrame();
		}
		else if (firstArgChar == 'c')
		{
			gate.clearContent();
		}
		else if (firstArgChar == 'a')
		{
			gate.clearFrame();
			gate.clearContent();
		}
		else
		{
			this.msg("<b>arg must be frame|content|all");
		}
	}
}