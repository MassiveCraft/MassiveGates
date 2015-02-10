package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;
import com.massivecraft.massivecore.mixin.Mixin;
import com.massivecraft.massivecore.mixin.TeleporterException;
import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateExitGoto extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateExitGoto()
	{
		// Aliases
		this.addAliases("goto");
		
		// Requirements
		this.addRequirements(ReqIsPlayer.get(), ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.EXIT_GOTO.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		// TODO: Unreachable location check.
		List<String> messages = new ArrayList<String>();
		
		// Args
		Gate gate = gsender.getSelectedGate();
		PS locw = gate.getExit();
		
		if (locw != null)
		{
			try
			{
				// Apply
				Mixin.teleport(me, locw, "to exit");
				
				messages.add(Txt.parse("<i>Gate %s<i>: Teleported to exit:", gate.getIdNameStringShort()));
				messages.add(gate.getExitDesc());
			}
			catch (TeleporterException e)
			{
				messages.add(e.getMessage());
			}
		}
		else
		{
			messages.add(Txt.parse("<i>Gate %s<i> does not have an exit.", gate.getIdNameStringShort()));
		}
		
		// Inform
		sendMessage(messages);
		
	}
	
}
