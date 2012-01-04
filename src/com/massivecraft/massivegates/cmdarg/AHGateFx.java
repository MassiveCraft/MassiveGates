package com.massivecraft.massivegates.cmdarg;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivegates.Gates;
import com.massivecraft.massivegates.fx.GateFx;
import com.massivecraft.mcore1.MPlugin;
import com.massivecraft.mcore1.cmd.arg.AHBase;

public class AHGateFx extends AHBase<GateFx>
{
	@Override
	public GateFx parse(String str, String style, CommandSender sender, MPlugin p)
	{
		this.error.clear();
		GateFx ret = Gates.i.getFx(str);
		
		if (ret == null)
		{
			this.error.add("<b>No fx matching \"<p>"+str+"<b>\".");
		}
		
		return ret;
	}
	
	private AHGateFx() {}
	private static AHGateFx instance = new AHGateFx();
	public static AHGateFx getInstance() { return instance; } 
}
