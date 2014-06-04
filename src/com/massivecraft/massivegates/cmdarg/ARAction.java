package com.massivecraft.massivegates.cmdarg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.cmd.arg.ARAbstractSelect;
import com.massivecraft.massivegates.GateColl;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.ta.Action;

public class ARAction extends ARAbstractSelect<Action>
{
	@Override
	public String typename()
	{
		return "action";
	}

	@Override
	public Action select(String str, CommandSender sender)
	{
		return GateColl.i.getActionName(str);
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
		for (Action action : GateColl.i.getActions())
		{
			ret.add(action.getName());
		}
		return ret;
	}
	
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static ARAction i = new ARAction();
	public static ARAction get() { return i; }
	
}
