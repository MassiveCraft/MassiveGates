package com.massivecraft.massivegates.cmdreq;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.cmd.MassiveCommand;
import com.massivecraft.massivecore.cmd.req.ReqAbstract;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.entity.GSender;
import com.massivecraft.massivegates.entity.GSenderColl;

public class ReqGateSelected extends ReqAbstract
{
	// -------------------------------------------- //
	// STATIC
	// -------------------------------------------- //
	
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
		GSender gsender = GSenderColl.get().get(sender);
		if (gsender == null) return false;
		
		return gsender.getSelectedGate() != null;
	}
	
	@Override
	public String createErrorMessage(CommandSender sender, MassiveCommand command)
	{
		return Txt.parse("<b>You must select a gate before you %s.", getDesc(command));
	}
	
}
