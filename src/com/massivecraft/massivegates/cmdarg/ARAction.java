package com.massivecraft.massivegates.cmdarg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.massivecraft.massivegates.GateColl;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.mcore5.cmd.MCommand;
import com.massivecraft.mcore5.cmd.arg.ARAbstractSelect;

public class ARAction extends ARAbstractSelect<Action>
{
	@Override
	public String typename()
	{
		return "action";
	}

	@Override
	public Action select(String str, MCommand mcommand)
	{
		return GateColl.i.getActionName(str);
	}
	
	@Override
	public boolean canList(MCommand mcommand)
	{
		return Permission.TA_LIST.has(mcommand.sender, false);
	}

	@Override
	public Collection<String> altNames(MCommand mcommand)
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
