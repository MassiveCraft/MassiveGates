package com.massivecraft.massivegates.cmdreq;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivegates.GSender;
import com.massivecraft.massivegates.GSenderColl;
import com.massivecraft.mcore.cmd.MCommand;
import com.massivecraft.mcore.cmd.req.ReqAbstract;

public class ReqGateSelected extends ReqAbstract
{
	private static final long serialVersionUID = 1L;
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static ReqGateSelected i = new ReqGateSelected();
	public static ReqGateSelected get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean apply(CommandSender sender, MCommand command)
	{
		GSender gsender = GSenderColl.i.get(sender);
		if (gsender == null) return false;
		return gsender.getSelectedGate() != null;
	}
	
	@Override
	public String createErrorMessage(CommandSender sender, MCommand command)
	{
		return "<b>You must select a gate before you "+(command == null ? "do that" : command.getDesc())+".";
	}
	
}
