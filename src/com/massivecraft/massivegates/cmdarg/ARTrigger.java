package com.massivecraft.massivegates.cmdarg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivegates.GateColl;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.ta.Trigger;
import com.massivecraft.mcore5.cmd.arg.ARAbstractSelect;

public class ARTrigger extends ARAbstractSelect<Trigger>
{
	@Override
	public String typename()
	{
		return "trigger";
	}

	@Override
	public Trigger select(String str, CommandSender sender)
	{
		return GateColl.i.getTriggerName(str);
	}
	
	@Override
	public boolean canList(CommandSender sender)
	{
		return Permission.TA_LIST.has(sender, false);
	}

	@Override
	public Collection<String> altNames(CommandSender sender)
	{
		List<String> ret = new ArrayList<String>();
		for (Trigger trigger : GateColl.i.getTriggers())
		{
			ret.add(trigger.getName());
		}
		return ret;
	}
	
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static ARTrigger i = new ARTrigger();
	public static ARTrigger get() { return i; }
	
}
