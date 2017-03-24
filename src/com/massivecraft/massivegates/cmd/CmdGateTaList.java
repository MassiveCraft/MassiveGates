package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Parameter;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.GateColl;
import com.massivecraft.massivegates.entity.MConf;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.Trigger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class CmdGateTaList extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateTaList()
	{
		// Parameters
		this.addParameter(Parameter.getPage());
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(RequirementHasPerm.get(Perm.TA_LIST.id));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateTaList;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		List<String> lines = new ArrayList<>();
		
		// Args
		int page = this.readArg();
		
		Gate gate = gsender.getSelectedGate();
		
		// Apply
		for (Trigger trigger : GateColl.get().getTriggers())
		{
			int i = 0;
			for (Entry<Action, String> actionArg : gate.getActionArgs(trigger))
			{
				i += 1;
				Action action = actionArg.getKey();
				String arg = actionArg.getValue();
				lines.add(Txt.parse("<lime>%s <rose>%s <k>%s %s <i>%s", trigger.getName(), action.getName(), i, (arg==null ? "":Txt.parse((" <v>"+arg))), action.getDesc()));
			}
		}
		
		if (lines.size() == 0)
		{
			lines.add(Txt.parse("<i>This gate has <h>NO <i>actions added. What a lame gate! :O"));
		}
		
		// Inform
		this.message(Txt.getPage(lines, page, "TriggerActions For Gate "+gate.getIdNameStringShort(), this));
	}

}
