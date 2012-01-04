package com.massivecraft.massivegates.cmdarg;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivegates.fx.GateFxMoment;
import com.massivecraft.mcore1.MPlugin;
import com.massivecraft.mcore1.cmd.arg.AHBase;

public class AHGateFxMoment extends AHBase<GateFxMoment>
{
	@Override
	public GateFxMoment parse(String str, String style, CommandSender sender, MPlugin p)
	{
		this.error.clear();
		GateFxMoment ret = GateFxMoment.getGateFxMoment(str);
		
		if (ret == null)
		{
			this.error.add("<b>No fx-moment matching \"<p>"+str+"<b>\".");
			this.error.add("<i>Suggestion: use one of <h>"+GateFxMoment.getOrShorts()+"<i>.");
		}
		
		return ret;
	}
	
	private AHGateFxMoment() {}
	private static AHGateFxMoment instance = new AHGateFxMoment();
	public static AHGateFxMoment getInstance() { return instance; } 
}
