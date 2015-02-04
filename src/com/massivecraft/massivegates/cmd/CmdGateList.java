package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivecore.cmd.MassiveCommandException;
import com.massivecraft.massivecore.cmd.arg.ARInteger;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.GateColl;
import com.massivecraft.massivegates.Permission;

public class CmdGateList extends GateCommand
{
	public CmdGateList()
	{
		this.addAliases("l","ls","list");
		this.addOptionalArg("page", "1");
		this.addRequirements(new ReqHasPerm(Permission.LIST.node));
	}

	@Override
	public void perform() throws MassiveCommandException
	{
		// Args
		Integer pageHumanBased = this.arg(0, ARInteger.get(), 1);
		
		// Create Lines
		List<String> lines = new ArrayList<String>(GateColl.i.getAll().size());
		for (Gate gate : GateColl.i.getAll())
		{
			lines.add(Txt.parse(gate.getIdNameStringShort()));
		}
		
		// Send Lines
		this.sendMessage(Txt.getPage(lines, pageHumanBased, "Gate List", sender));	
	}
}
