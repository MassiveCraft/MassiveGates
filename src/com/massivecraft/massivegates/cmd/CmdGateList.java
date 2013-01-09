package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.GateColl;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.mcore5.cmd.arg.ARInteger;
import com.massivecraft.mcore5.cmd.req.ReqHasPerm;
import com.massivecraft.mcore5.util.Txt;

public class CmdGateList extends GateCommand
{
	public CmdGateList()
	{
		this.addAliases("l","ls","list");
		this.addOptionalArg("page", "1");
		this.addRequirements(new ReqHasPerm(Permission.LIST.node));
	}

	@Override
	public void perform()
	{
		Integer pageHumanBased = this.arg(0, ARInteger.get(), 1);
		if (pageHumanBased == null) return;
		List<String> gateInfos = new ArrayList<String>(GateColl.i.getAll().size());
		for (Gate gate : GateColl.i.getAll())
		{
			gateInfos.add("<white>"+gate.getIdNameStringShort());
		}
		
		this.sendMessage(Txt.getPage(Txt.parseWrap(Txt.implodeCommaAndDot(gateInfos, "<i>")), pageHumanBased, "Gate List", sender));	
	}
}
