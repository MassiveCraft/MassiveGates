package com.massivecraft.massivegates.cmdarg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.cmd.arg.ARAbstractSelect;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.entity.GateColl;
import com.massivecraft.massivegates.ta.Trigger;

public class ARTrigger extends ARAbstractSelect<Trigger>
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static ARTrigger i = new ARTrigger();
	public static ARTrigger get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public String typename()
	{
		return "trigger";
	}

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
	
}
