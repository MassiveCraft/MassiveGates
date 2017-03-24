package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Parameter;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.entity.MConf;
import com.massivecraft.massivegates.util.Fx;

import java.util.ArrayList;
import java.util.List;

public class CmdGateFxAlt extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCTOR
	// -------------------------------------------- //
	
	public CmdGateFxAlt()
	{
		// Parameters
		this.addParameter(Parameter.getPage());
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.FX_ALT.id));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateFxAlt;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		int page = this.readArg();
		
		// Create Messages
		List<String> messages = new ArrayList<String>();
		
		messages.add(Txt.parse("<a># <i>There is one FX per line in this list."));
		messages.add(Txt.parse("<a># <i>S = Sound, V = Visual, D = Data"));
		
		for (Fx fx: Fx.values())
		{
			StringBuilder sb = new StringBuilder();
			
			sb.append(fx.getHasVisual() ? "<g>" : "<b>");
			sb.append("V ");
			
			sb.append(fx.getHasSound() ? "<g>" : "<b>");
			sb.append("S ");
			
			sb.append(fx.getTakesData() ? "<g>" : "<b>");
			sb.append("D ");
			
			sb.append("<h>");
			sb.append(fx.getName());
			
			sb.append(" <i>");
			sb.append(fx.getDesc());
			messages.add(Txt.parse(sb.toString()));
		}
		
		// Send Messages
		this.message(Txt.getPage(messages, page, "Available Special FX", this));
	}
	
}
