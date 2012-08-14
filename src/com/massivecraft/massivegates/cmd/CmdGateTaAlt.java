package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Gates;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.Trigger;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;
import com.massivecraft.mcore4.util.Txt;

public class CmdGateTaAlt extends GateCommand
{
	public CmdGateTaAlt()
	{
		super();
		this.addAliases("alt");
		this.addRequiredArg("trigger|action");
		this.addOptionalArg("page", "1");
		this.addRequirements(new ReqHasPerm(Permission.TA_ALT.node));
	}
	
	@Override
	public void perform()
	{
		Integer pageHumanBased = this.argAs(1, Integer.class, 1);
		if (pageHumanBased == null) return;
		
		List<String> lines = new ArrayList<String>();
		
		String title = null;
		
		if (this.arg(0).charAt(0) == 't')
		{
			title = "Trigger Alternatives";
			for(Trigger trigger : Gates.i.getTriggers())
			{
				lines.add("<lime>"+trigger.getName()+" <i>"+trigger.getDesc());
			}
		}
		else
		{
			title = "Action Alternatives";
			for(Action action : Gates.i.getActions())
			{
				lines.add("<k>"+action.getName()+" <i>"+action.getDesc());
			}
		}
		
		lines = Txt.parseWrap(lines);
		this.sendMessage(Txt.getPage(lines, pageHumanBased, title));
	}
}