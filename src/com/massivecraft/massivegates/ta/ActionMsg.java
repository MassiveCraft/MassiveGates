package com.massivecraft.massivegates.ta;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.entity.Gate;

public class ActionMsg extends BaseAction
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static ActionMsg instance = new ActionMsg();
	public static ActionMsg get() { return instance; }
	
	protected ActionMsg()
	{
		super("msg", "Msg", "msg:message to the player.");
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public final static List<String> errors = Arrays.asList("<b>You must enter a message to be sent!");
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		// Only for players
		if ( ! (entity instanceof Player)) return;
		Player player = (Player) entity;
		
		player.sendMessage(Txt.parse(arg));
	}
	
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
