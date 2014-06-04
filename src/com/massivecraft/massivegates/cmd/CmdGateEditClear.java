package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;

public class CmdGateEditClear extends GateCommand
{
	public CmdGateEditClear()
	{
		this.addAliases("clear");
		this.addRequiredArg("frame|content|all");
		this.addRequirements(ReqGateSelected.get());
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
			this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>all frame blocks was removed (but no content).");
		}
		else if (firstArgChar == 'c')
		{
			gate.clearContent();
			this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>all content blocks was removed (but no frame).");
		}
		else if (firstArgChar == 'a')
		{
			gate.clearFrame();
			gate.clearContent();
			this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>all blocks was removed (both frame and content).");
		}
		else
		{
			this.msg("<b>arg must be frame|content|all");
		}
	}
}