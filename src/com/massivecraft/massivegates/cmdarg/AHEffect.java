package com.massivecraft.massivegates.cmdarg;

import org.bukkit.Effect;
import org.bukkit.command.CommandSender;

import com.massivecraft.mcore3.MPlugin;
import com.massivecraft.mcore3.cmd.arg.AHBase;

public class AHEffect extends AHBase<Effect>
{
	@Override
	public Effect parse(String str, String style, CommandSender sender, MPlugin p)
	{	
		this.error.clear();
		Effect ret = Effect.valueOf(str);
		if (ret == null)
		{
			this.error.add("<b>No effect matched \"<p>"+str+"<b>\".");
		}
		return ret;
	}

	private AHEffect() {}
	private static AHEffect instance = new AHEffect();
	public static AHEffect getInstance() { return instance; } 
}
