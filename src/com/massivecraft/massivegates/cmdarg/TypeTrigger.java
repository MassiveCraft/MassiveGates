package com.massivecraft.massivegates.cmdarg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.cmd.type.TypeAbstractSelect;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.entity.GateColl;
import com.massivecraft.massivegates.ta.Trigger;

public class TypeTrigger extends TypeAbstractSelect<Trigger>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TypeTrigger i = new TypeTrigger();
	public static TypeTrigger get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public Trigger select(String str, CommandSender sender)
	{
		return GateColl.get().getTriggerName(str);
	}
	
	@Override
	public boolean canList(CommandSender sender)
	{
		return Perm.TA_LIST.has(sender, false);
	}

	@Override
	public Collection<String> altNames(CommandSender sender)
	{
		List<String> ret = new ArrayList<String>();
		for (Trigger trigger : GateColl.get().getTriggers())
		{
			ret.add(trigger.getName());
		}
		return ret;
	}

	@Override
	public Collection<String> getTabList(CommandSender sender, String arg)
	{
		return this.altNames(sender);
	}
	
}
