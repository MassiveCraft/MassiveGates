package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateTargetGoto extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateTargetGoto()
	{
		// Aliases
		this.addAliases("goto");
		
		// Requirements
		this.addRequirements(RequirementIsPlayer.get(), ReqGateSelected.get());
		this.addRequirements(RequirementHasPerm.get(Perm.TARGET_GOTO.id));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		List<String> lines = new ArrayList<String>();
		
		// Args
		Gate gate = gsender.getSelectedGate();
		
		// Apply
		if ( ! gate.getTarget().exists())
		{
			lines.add(Txt.parse("<i>Gate %s<i>: It doesn't have a target.", gate.getIdNameStringShort()));
		}
		else
		{
			gate.getTarget().teleport(me);
			lines.add(Txt.parse("<i>Gate %s<i>: Teleportet to target:", gate.getIdNameStringShort()));
			lines.add(gate.getTarget().getDesc());
		}
		
		// Inform
		this.message(lines);
	}
	
}
