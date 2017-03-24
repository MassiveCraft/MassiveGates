package com.massivecraft.massivegates.ta;

import com.massivecraft.massivegates.MassiveGates;
import com.massivecraft.massivegates.entity.Gate;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

import java.util.Collections;
import java.util.List;

public class ActionChat extends BaseAction
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static ActionChat i = new ActionChat();
	public static ActionChat get() { return i; }
	
	protected ActionChat()
	{
		super("mgcore_chat", "Chat", "chat/command as player. Replacing {p} with playername.");
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public final static List<String> errorsRequired = Collections.singletonList("<b>You must enter the chat string in the argument!");
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		// Only for players
		if ( ! (entity instanceof Player)) return;
		Player player = (Player) entity;
		
		String chat = arg.replace("{p}", player.getName());
		MassiveGates.get().log("Chat-Action as " + player.getName()+": " + chat);
		player.chat(chat);
	}
	
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
