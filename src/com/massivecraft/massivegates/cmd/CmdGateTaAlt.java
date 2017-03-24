package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Parameter;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.entity.GateColl;
import com.massivecraft.massivegates.entity.MConf;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.Trigger;

import java.util.ArrayList;
import java.util.List;

public class CmdGateTaAlt extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateTaAlt()
	{
		// Parameters
		this.addParameter(TypeString.get(), "trigger|action");
		this.addParameter(Parameter.getPage());
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.TA_ALT.id));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateTaAlt;
	}
	
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
