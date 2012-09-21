package com.massivecraft.massivegates.ta;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.P;

public class ActionChat extends BaseAction
{
	protected static ActionChat instance = new ActionChat();
	public static ActionChat getInstance() { return instance; }
	protected ActionChat()
	{
		super("mgcore_chat", "Chat", "chat/command as player. Replacing {p} with playername.");
	}
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if ( ! (entity instanceof Player)) return;
		Player player = (Player) entity;
		String chat = arg.replace("{p}", player.getName());
		P.p.log("Chat-Action as "+player.getName()+": "+ chat);
		player.chat(chat);
	}
	
	public final static List<String> errorsRequired = Arrays.asList("<b>You must enter the chat string in the argument!");
	@Override
	public List<String> checkArg(String arg)
	{
		if (arg == null || arg.trim().length() == 0)
		{
			return errorsRequired;
		}
		return null;
	}
}