package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdarg.ARGate;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdGateSel extends GateCommand
{
	public CmdGateSel()
	{
		super();
		this.addAliases("sel");
		this.addOptionalArg("gate", "*get*");
		
		this.addRequirements(new ReqHasPerm(Permission.SELECT.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		
		if ( ! this.argIsSet(0))
		{
			if (gate == null)
			{
				this.msg("<i>No gate selected.");
			}
			else
			{
				this.msg("<i>Currently selected: "+gate.getIdNameStringLong());
			}
			return;
		}
		
		gate = this.arg(0, ARGate.get());
		if (gate == null) return;
		gme.setSelectedGate(gate);
		if (me != null)
		{
			gate.visualizeFor(me);
		}
		this.msg("<i>Selected gate "+gate.getIdNameStringLong());
	}
}