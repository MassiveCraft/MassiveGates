package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARInteger;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.GateColl;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.Trigger;

public class CmdGateTaList extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateTaList()
	{
		// Aliases
		this.addAliases("list");
		
		// Args
		this.addOptionalArg("page", "1");
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.TA_LIST.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		List<String> lines = new ArrayList<String>();
		
		// Args
		int pageHumanBased = this.arg(0, ARInteger.get(), 1);
		
		Gate gate = gsender.getSelectedGate();
		
		// Apply
		for(Trigger trigger : GateColl.get().getTriggers())
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
		this.sendMessage(Txt.getPage(lines, pageHumanBased, "TriggerActions For Gate "+gate.getIdNameStringShort(), sender));
	}

}
