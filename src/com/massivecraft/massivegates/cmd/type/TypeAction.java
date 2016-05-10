package com.massivecraft.massivegates.cmd.type;

import java.util.Collection;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.command.type.TypeAbstractChoice;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.entity.GateColl;
import com.massivecraft.massivegates.ta.Action;

public class TypeAction extends TypeAbstractChoice<Action>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TypeAction i = new TypeAction();
	public static TypeAction get() { return i; }
	public TypeAction() { super(Action.class); }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public String getIdInner(Action value)
	{
		return value.getId();
	}

	@Override
	public Collection<Action> getAll()
	{
		return GateColl.get().getActions();
	}
	
	@Override
	public boolean canList(CommandSender sender)
	{
		return Perm.TA_LIST.has(sender, false);
	}
	
}
