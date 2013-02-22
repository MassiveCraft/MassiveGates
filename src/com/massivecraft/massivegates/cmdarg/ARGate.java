package com.massivecraft.massivegates.cmdarg;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.massivecraft.massivegates.GSender;
import com.massivecraft.massivegates.GSenderColl;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateColl;
import com.massivecraft.mcore.cmd.arg.ArgReader;
import com.massivecraft.mcore.cmd.arg.ArgResult;
import com.massivecraft.mcore.util.Txt;

public class ARGate implements ArgReader<Gate>
{
	@Override
	public ArgResult<Gate> read(String str, CommandSender sender)
	{
		ArgResult<Gate> result = new ArgResult<Gate>();
		
		// Is this by chance a "that" request
		if (str.equalsIgnoreCase("that"))
		{
			if ( ! (sender instanceof Player))
			{
				result.getErrors().add("<b>You must be ingame player to use \"that\" gate detection.");
				return null;
			}
			else
			{
				Player me = (Player)sender;
				GSender gme = GSenderColl.i.get(me);
				result.setResult(gme.getThatGate(false));
				if (result.hasResult() == false)
				{
					result.getErrors().add("<b>No gate in sight.");
				}
				return result;
			}
		}
		
		// Then we attempt to get by id.
		result.setResult(GateColl.i.get(str));
		if (result.hasResult()) return result;
		
		// No matching id huh?... Lets test against the gate's name then:
		// Build a name to gate map:
		Map<String, Gate> name2gate = new HashMap<String, Gate>();
		for (Gate g : GateColl.i.getAll())
		{
			if (g.getName() == null) continue;
			name2gate.put(g.getName(), g);
		}
		
		// Find the best name
		String bestName = Txt.getBestCIStart(name2gate.keySet(), str);
		if (bestName != null)
		{
			result.setResult(name2gate.get(bestName));
		}
		
		if (result.hasResult() == false)
		{
			result.getErrors().add("<b>No gate matching \"<p>"+str+"<b>\".");
		}
		
		return result;
	}
	
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static ARGate i = new ARGate();
	public static ARGate get() { return i; }
}
