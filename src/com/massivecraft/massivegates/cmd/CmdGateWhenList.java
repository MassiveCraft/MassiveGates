package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Gates;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.when.Action;
import com.massivecraft.massivegates.when.Trigger;
import com.massivecraft.mcore1.cmd.req.ReqHasPerm;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;
import com.massivecraft.mcore1.util.Txt;

public class CmdGateWhenList extends GateCommand
{
	public CmdGateWhenList()
	{
		super();
		this.addAliases("list");
		this.addOptionalArg("page", "1");
		
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.WHEN_LIST.node));
	}
	
	@Override
	public void perform()
	{
		Integer pageHumanBased = this.argAs(0, Integer.class, 1);
		if (pageHumanBased == null) return;
		
		Gate gate = gme.getSelectedGate();
		
		List<String> lines = new ArrayList<String>();
		
		for(Trigger trigger : Gates.i.getTriggers())
		{
			for (Entry<Action, String> actionArg : gate.getActionArgs(trigger))
			{
				Action action = actionArg.getKey();
				String arg = actionArg.getValue();
				lines.add("<lime>"+trigger.getName()+" <k>"+action.getName()+(arg==null?"":(" <v>"+arg))+" <i>"+action.getDesc());
			}
		}
		
		if (lines.size() == 0)
		{
			lines.add("<i>This gate has NO actions added. What a lame gate! :O");
		}
		
		lines = Txt.parseWrap(lines);
		this.sendMessage(Txt.getPage(lines, pageHumanBased, "TriggerActions For Gate "+Txt.parse(gate.getIdNameStringShort())));
	}
}