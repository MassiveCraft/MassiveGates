package com.massivecraft.massivegates.cmdreq;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivegates.GPlayer;
import com.massivecraft.massivegates.GPlayerColl;
import com.massivecraft.mcore4.cmd.MCommand;
import com.massivecraft.mcore4.cmd.req.IReq;

public class ReqGateSelected implements IReq
{
	@Override
	public boolean test(CommandSender sender, MCommand command)
	{
		GPlayer gplayer = GPlayerColl.i.get(sender);
		return gplayer.getSelectedGate() != null;
	}

	@Override
	public String createErrorMessage(CommandSender sender, MCommand command)
	{
		return "<b>You must select a gate before you "+command.getDesc()+".";
	}
	
	protected static ReqGateSelected instance = new ReqGateSelected();
	public static ReqGateSelected getInstance()
	{
		return instance;
	}
}
