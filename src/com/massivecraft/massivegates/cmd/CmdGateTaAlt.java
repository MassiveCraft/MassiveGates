package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.Parameter;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.type.TypeString;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.entity.GateColl;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.Trigger;

public class CmdGateTaAlt extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateTaAlt()
	{
		// Aliases
		this.addAliases("alt");
		
		// Parameters
		this.addParameter(TypeString.get(), "trigger|action");
		this.addParameter(Parameter.getPage());
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.TA_ALT.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		List<String> lines = new ArrayList<String>();
		String title = null;
		
		// Apply
		if (this.argAt(0).startsWith("t"))
		{
			title = "Trigger Alternatives";
			for(Trigger trigger : GateColl.get().getTriggers())
			{
				lines.add(Txt.parse("<lime>%s<i>: %s", trigger.getName(), trigger.getDesc()));
			}
		}
		else
		{
			title = "Action Alternatives";
			for(Action action : GateColl.get().getActions())
			{
				lines.add(Txt.parse("<k>%s<i>: %s",action.getName(), action.getDesc()));
			}
		}
		
		// Args
		int page = this.readArg();
		
		// Inform
		lines = Txt.parseWrap(lines);
		this.message(Txt.getPage(lines, page, title, this));
	}
	
}
