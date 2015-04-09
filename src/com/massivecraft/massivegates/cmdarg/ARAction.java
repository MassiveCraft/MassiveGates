package com.massivecraft.massivegates.cmdarg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.cmd.arg.ARAbstractSelect;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.entity.GateColl;
import com.massivecraft.massivegates.ta.Action;

public class ARAction extends ARAbstractSelect<Action>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static ARAction i = new ARAction();
	public static ARAction get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public Action select(String str, CommandSender sender)
	{
		return GateColl.get().getActionName(str);
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
		for (Action action : GateColl.get().getActions())
		{
			ret.add(action.getName());
		}
		return ret;
	}

	@Override
	public Collection<String> getTabList(CommandSender sender, String arg)
	{
		return this.altNames(sender);
	}
	
}
