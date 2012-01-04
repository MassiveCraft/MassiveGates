package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Gates;
import com.massivecraft.massivegates.fx.GateFx;
import com.massivecraft.mcore1.util.Txt;

public class CmdGateFxAlt extends GateCommand
{
	public CmdGateFxAlt()
	{
		super();
		this.addAliases("alt");
		this.addOptionalArg("page", "1");
	}
	
	@Override
	public void perform()
	{
		Integer pageHumanBased = this.argAs(0, Integer.class, 1);
		if (pageHumanBased == null) return;
		
		List<String> lines = new ArrayList<String>();
		
		for(GateFx fx : Gates.i.getFxs())
		{
			lines.add("<h>"+fx.getUsagePattern()+" <i>"+fx.getDesc());
		}
		
		lines = Txt.parseWrap(lines);
		this.sendMessage(Txt.getPage(lines, pageHumanBased, "FX Alternatives"));
	}
}