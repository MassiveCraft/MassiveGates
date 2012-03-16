package com.massivecraft.massivegates.cmdarg;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.massivecraft.massivegates.GPlayer;
import com.massivecraft.massivegates.GPlayers;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.Gates;
import com.massivecraft.mcore2.MPlugin;
import com.massivecraft.mcore2.cmd.arg.AHBase;
import com.massivecraft.mcore2.persist.Persist;

public class AHGate extends AHBase<Gate>
{
	@Override
	public Gate parse(String str, String style, CommandSender sender, MPlugin p)
	{	
		this.error.clear();
		Gate ret = null;
		// Is this by chance a "that" request
		if (str.equalsIgnoreCase("that"))
		{
			if ( ! (sender instanceof Player))
			{
				this.error.add("<b>You must be ingame player to use \"that\" gate detection.");
				return null;
			}
			else
			{
				Player me = (Player)sender;
				GPlayer gme = GPlayers.i.get(me);
				ret = gme.getThatGate(false);
				if (ret == null)
				{
					this.error.add("<b>No gate in sight.");
				}
				return ret;
			}
		}
		
		// Then we attempt to get by id.
		ret = Gates.i.get(str);
		if ( ret != null) return ret;
		
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
		}
		
		if (ret == null)
		{
			this.error.add("<b>No gate matching \"<p>"+str+"<b>\".");
		}
		
		return ret;
	}

	private AHGate() {}
	private static AHGate instance = new AHGate();
	public static AHGate getInstance() { return instance; } 
}
