package com.massivecraft.massivegates.ta;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.MassiveGates;
import com.massivecraft.massivegates.entity.Gate;

public class ActionCmd extends BaseAction
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	public final static List<String> ERRORS_REQUIRED = Arrays.asList("<b>You must enter the command in the argument!");
	
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static ActionCmd i = new ActionCmd();
	public static ActionCmd get() { return i; }
	
	protected ActionCmd()
	{
		super("cmd", "cmd", "Console command. Replacing {p} with playername.");
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		// Remove all leading slashes and spaces from the command
		String cmd = arg.replaceAll("^[/\\s]+", "");
		
		// Fetch the player
		Player player = null;
		if (entity instanceof Player)
		{
			player = (Player) entity;
		}
		
		// Does the command contain the {p} tag?
		if (cmd.contains("{p}"))
		{
			if (player == null) return;
			cmd = cmd.replace("{p}", player.getName());
		}
		
		MassiveGates.get().log("CMD-Action: ", cmd);
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
	}
	
	@Override
	public List<String> checkArg(String arg)
	{
		if (arg == null || arg.trim().length() == 0)
		{
			return ERRORS_REQUIRED;
		}
		return null;
	}
	
}
