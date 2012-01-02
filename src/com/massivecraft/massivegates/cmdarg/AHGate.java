package com.massivecraft.massivegates.cmdarg;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.Gates;
import com.massivecraft.mcore1.MPlugin;
import com.massivecraft.mcore1.cmd.arg.AHBase;

public class AHGate extends AHBase<Gate>
{
	@Override
	public Gate parse(String str, String style, CommandSender sender, MPlugin p)
	{	
		// First we attempt to get by id.
		Gate ret = Gates.i.get(str);
		/*if ( ret != null) return ret;
		
		
		// No matching id huh?... Lets test against the gate's name then:
		// Build a name to gate map:
		Map<String, Gate> name2gate = new HashMap<String, Gate>();
		for (Gate g : Gates.i.getAll())
		{
			if (g.getName() == null) continue;
			name2gate.put(g.getName(), g);
		}
		
		// Find the best name
		String bestName = Persist.getBestCIStart(name2gate.keySet(), str);
		if (bestName != null)
		{
			ret = name2gate.get(bestName);
		}*/
		
		if (ret == null)
		{
			this.error = "<b>No gate id \"<p>"+str+"<b>\".";
		}
		
		return ret;
	}

	private AHGate() {}
	private static AHGate instance = new AHGate();
	public static AHGate getInstance() { return instance; } 
}
