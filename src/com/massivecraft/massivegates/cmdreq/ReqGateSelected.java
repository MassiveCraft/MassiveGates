package com.massivecraft.massivegates.cmdreq;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivegates.GSender;
import com.massivecraft.massivegates.GSenderColl;
import com.massivecraft.mcore.cmd.MCommand;
import com.massivecraft.mcore.cmd.req.IReq;

public class ReqGateSelected implements IReq
{
	@Override
	public boolean test(CommandSender sender, MCommand command)
	{
		GSender gsender = GSenderColl.i.get(sender);
		return gsender.getSelectedGate() != null;
	}

	@Override
	public String createErrorMessage(CommandSender sender, MCommand command)
	{
		return "<b>You must select a gate before you "+command.getDesc()+".";
	}
	
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	protected static ReqGateSelected i = new ReqGateSelected();
	public static ReqGateSelected get() { return i; }
	
}
