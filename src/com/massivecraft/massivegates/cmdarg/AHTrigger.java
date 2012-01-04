package com.massivecraft.massivegates.cmdarg;

import org.bukkit.command.CommandSender;
import com.massivecraft.massivegates.Gates;
import com.massivecraft.massivegates.when.Trigger;
import com.massivecraft.mcore1.MPlugin;
import com.massivecraft.mcore1.cmd.arg.AHBase;

public class AHTrigger extends AHBase<Trigger>
{
	@Override
	public Trigger parse(String str, String style, CommandSender sender, MPlugin p)
	{	
		this.error.clear();
		Trigger ret = Gates.i.getTriggerName(str);
		if (ret == null)
		{
			this.error.add("<b>No trigger matched \"<p>"+str+"<b>\".");
		}
		return ret;
	}

	private AHTrigger() {}
	private static AHTrigger instance = new AHTrigger();
	public static AHTrigger getInstance() { return instance; } 
}
