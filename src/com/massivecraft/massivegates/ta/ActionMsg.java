package com.massivecraft.massivegates.ta;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.mcore2.util.Txt;

public class ActionMsg extends BaseAction
{
	protected static ActionMsg instance = new ActionMsg();
	public static ActionMsg getInstance() { return instance; }
	protected ActionMsg()
	{
		super("mgcore_msg", "Msg", "msg:message to the player.");
	}
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if ( ! (entity instanceof Player)) return;
		Player player = (Player) entity;
		player.sendMessage(Txt.parse(arg));
	}
	
	public final static List<String> errors = Arrays.asList("<b>You must enter a message to be sent!");
	@Override
	public List<String> checkArg(String arg)
	{
		if (arg == null || arg.trim().length() == 0)
		{
			return errors;
		}
		return null;
	}
}