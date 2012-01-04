package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.mcore1.cmd.req.ReqHasPerm;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;

public class CmdGateSel extends GateCommand
{
	public CmdGateSel()
	{
		super();
		this.addAliases("sel");
		this.addOptionalArg("gate", "*get*");
		
		this.addRequirements(ReqIsPlayer.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.SEL.node));
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
		
		gate = this.argAs(0, Gate.class);
		if (gate == null) return;
		gme.setSelectedGate(gate);
		gate.visualizeFor(me);
		this.msg("<i>Selected gate "+gate.getIdNameStringLong());
	}
}