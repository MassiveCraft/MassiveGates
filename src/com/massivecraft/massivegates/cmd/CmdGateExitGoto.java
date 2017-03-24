package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.mixin.MixinTeleport;
import com.massivecraft.massivecore.mixin.TeleporterException;
import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivecore.teleport.DestinationSimple;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.MConf;

import java.util.ArrayList;
import java.util.List;

public class CmdGateExitGoto extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateExitGoto()
	{
		// Requirements
		this.addRequirements(RequirementIsPlayer.get(), ReqGateSelected.get());
		this.addRequirements(RequirementHasPerm.get(Perm.EXIT_GOTO.id));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateExitGoto;
	}
	
	@Override
	public void perform()
	{
		// TODO: Unreachable location check.
		List<String> messages = new ArrayList<>();
		
		// Args
		Gate gate = gsender.getSelectedGate();
		PS destinationPs = gate.getExit();
		if (destinationPs != null)
		{
			try
			{
				// Apply
				
				MixinTeleport.get().teleport(me, new DestinationSimple(destinationPs, "to exit"));
				
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
		message(messages);
		
	}
	
}
