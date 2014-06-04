package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;
import com.massivecraft.massivecore.mixin.Mixin;
import com.massivecraft.massivecore.mixin.TeleporterException;
import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;

public class CmdGateExitGoto extends GateCommand
{
	public CmdGateExitGoto()
	{
		this.addAliases("goto");
		this.addRequirements(ReqIsPlayer.get(), ReqGateSelected.get());
		this.addRequirements(new ReqHasPerm(Permission.EXIT_GOTO.node));
	}
	
	@Override
	public void perform()
	{
		
		// TODO: Unreachable location check.
		
		Gate gate = gme.getSelectedGate();
		PS locw = gate.getExit();
		if (locw != null)
		{
			try
			{
				Mixin.teleport(me, locw, "sdf");
				this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>teleported to exit:");
				this.msg(gate.getExitDesc());
			}
			catch (TeleporterException e)
			{
				me.sendMessage(e.getMessage());
			}
		}
		else
		{
			this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>does not have an exit.");
		}
	}
}