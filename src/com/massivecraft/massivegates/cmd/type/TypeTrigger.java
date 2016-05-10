package com.massivecraft.massivegates.cmd.type;

import java.util.Collection;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.command.type.TypeAbstractChoice;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.entity.GateColl;
import com.massivecraft.massivegates.ta.Trigger;

public class TypeTrigger extends TypeAbstractChoice<Trigger>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TypeTrigger i = new TypeTrigger();
	public static TypeTrigger get() { return i; }
	public TypeTrigger() { super(Trigger.class); }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public String getIdInner(Trigger value)
	{
		return value.getId();
	}

	@Override
	public Collection<Trigger> getAll()
	{
		return GateColl.get().getTriggers();
	}
	
	@Override
	public boolean canList(CommandSender sender)
	{
		return Perm.TA_LIST.has(sender, false);
	}	
	
}
