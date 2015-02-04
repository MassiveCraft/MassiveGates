package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.cmd.MassiveCommandException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdarg.ARGate;

public class CmdGateSel extends GateCommand
{
	public CmdGateSel()
	{
		this.addAliases("sel");
		this.addOptionalArg("gate", "*get*");
		
		this.addRequirements(new ReqHasPerm(Permission.SELECT.node));
	}
	
	@Override
	public void perform() throws MassiveCommandException
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
		gme.setSelectedGate(gate);
		if (me != null)
		{
			gate.visualizeFor(me);
		}
		this.msg("<i>Selected gate "+gate.getIdNameStringLong());
	}
}