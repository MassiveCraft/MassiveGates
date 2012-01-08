package com.massivecraft.massivegates.cmdarg;

import org.bukkit.command.CommandSender;
import com.massivecraft.massivegates.Gates;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.mcore1.MPlugin;
import com.massivecraft.mcore1.cmd.arg.AHBase;

public class AHAction extends AHBase<Action>
{
	@Override
	public Action parse(String str, String style, CommandSender sender, MPlugin p)
	{	
		this.error.clear();
		Action ret = Gates.i.getActionName(str);
		if (ret == null)
		{
			this.error.add("<b>No action matched \"<p>"+str+"<b>\".");
		}
		return ret;
	}

	private AHAction() {}
	private static AHAction instance = new AHAction();
	public static AHAction getInstance() { return instance; } 
}
