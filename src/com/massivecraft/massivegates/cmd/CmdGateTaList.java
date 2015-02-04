package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.massivecraft.massivecore.cmd.MassiveCommandException;
import com.massivecraft.massivecore.cmd.arg.ARInteger;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.GateColl;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.Trigger;

public class CmdGateTaList extends GateCommand
{
	public CmdGateTaList()
	{
		this.addAliases("list");
		this.addOptionalArg("page", "1");
		
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(new ReqHasPerm(Permission.TA_LIST.node));
	}
	
	@Override
	public void perform() throws MassiveCommandException
	{
		Integer pageHumanBased = this.arg(0, ARInteger.get(), 1);
		
		Gate gate = gme.getSelectedGate();
		
		List<String> lines = new ArrayList<String>();
		
		for(Trigger trigger : GateColl.i.getTriggers())
		{
			int i = 0;
			for (Entry<Action, String> actionArg : gate.getActionArgs(trigger))
			{
				i += 1;
				Action action = actionArg.getKey();
				String arg = actionArg.getValue();
				lines.add("<lime>"+trigger.getName()+" <rose>"+i+" <k>"+action.getName()+(arg==null?"":(" <v>"+arg))+" <i>"+action.getDesc());
			}
		}
		
		if (lines.size() == 0)
		{
			lines.add("<i>This gate has NO actions added. What a lame gate! :O");
		}
		
		lines = Txt.parseWrap(lines);
		this.sendMessage(Txt.getPage(lines, pageHumanBased, "TriggerActions For Gate "+Txt.parse(gate.getIdNameStringShort()), sender));
	}
}