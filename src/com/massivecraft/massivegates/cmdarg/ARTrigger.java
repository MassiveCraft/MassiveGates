package com.massivecraft.massivegates.cmdarg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.massivecraft.massivegates.GateColl;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.ta.Trigger;
import com.massivecraft.mcore4.cmd.MCommand;
import com.massivecraft.mcore4.cmd.arg.ARAbstractSelect;

public class ARTrigger extends ARAbstractSelect<Trigger>
{
	@Override
	public String typename()
	{
		return "trigger";
	}

	@Override
	public Trigger select(String str, MCommand mcommand)
	{
		return GateColl.i.getTriggerName(str);
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
