package com.massivecraft.massivegates.cmdreq;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.cmd.MassiveCommand;
import com.massivecraft.massivecore.cmd.req.ReqAbstract;
import com.massivecraft.massivegates.GSender;
import com.massivecraft.massivegates.GSenderColl;

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
	public boolean apply(CommandSender sender, MassiveCommand command)
	{
		GSender gsender = GSenderColl.i.get(sender);
		if (gsender == null) return false;
		return gsender.getSelectedGate() != null;
	}
	
	@Override
	public String createErrorMessage(CommandSender sender, MassiveCommand command)
	{
		return "<b>You must select a gate before you "+(command == null ? "do that" : command.getDesc())+".";
	}
	
}
