package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARInteger;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.util.Fx;

public class CmdGateFxAlt extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCTOR
	// -------------------------------------------- //
	
	public CmdGateFxAlt()
	{
		// Aliases
		this.addAliases("alt");
		
		// Args
		this.addOptionalArg("page", "1");
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.FX_ALT.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		List<String> messages = new ArrayList<String>();
		
		// Args
		Integer pageHumanBased = this.arg(0, ARInteger.get(), 1);
		
		messages.add(Txt.parse("<a># <i>There is one FX per line in this list."));
		messages.add(Txt.parse("<a># <i>S = Sound, V = Visual, D = Data"));
		
		for(Fx fx: Fx.values())
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
		
		// Inform
		this.sendMessage(Txt.getPage(messages, pageHumanBased, "Available Special FX", sender));
	}
	
}
