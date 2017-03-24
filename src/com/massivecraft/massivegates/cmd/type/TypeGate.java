package com.massivecraft.massivegates.cmd.type;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.collections.MassiveList;
import com.massivecraft.massivecore.command.type.TypeAbstract;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.entity.GSender;
import com.massivecraft.massivegates.entity.GSenderColl;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.GateColl;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeGate extends TypeAbstract<Gate>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TypeGate i = new TypeGate();
	public static TypeGate get() { return i; }
	public TypeGate() { super(Gate.class); }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Gate read(String str, CommandSender sender) throws MassiveException
	{
		Gate ret;
		
		// Is this by chance a "that" request
		if (str.equalsIgnoreCase("that"))
		{
			if (sender instanceof Player)
			{
				Player me = (Player)sender;
				GSender gme = GSenderColl.get().get(me);
				ret = gme.getThatGate(false);
				if (ret != null) return ret;
				throw new MassiveException().addMsg("<b>No gate in sight.");
			}
			else
			{
				throw new MassiveException().addMsg("<b>You must be ingame player to use \"that\" gate detection.");
			}
		}
		
		// Then we attempt to get by id.
		ret = GateColl.get().get(str);
		if (ret != null) return ret;
		
		// No matching id huh?... Lets test against the gate's name then:
		// Build a name to gate map:
		Map<String, Gate> name2gate = new HashMap<>();
		for (Gate g : GateColl.get().getAll())
		{
			if (g.getName() == null) continue;
			name2gate.put(g.getName(), g);
		}
		
		// Find the best name
		String bestName = Txt.getBestCIStart(name2gate.keySet(), str);
		if (bestName != null)
		{
			ret = name2gate.get(bestName);
		}
		
		if (ret != null)
		{
			return ret;
		}
		
		throw new MassiveException().addMsg("<b>No gate matching \"<p>%s<b>\".", str);
	}

	@Override
	public Collection<String> getTabList(CommandSender sender, String arg)
	{
		List<String> ret = new MassiveList<>();
		
		for (Gate gate : GateColl.get().getAll())
		{
			ret.add(gate.getName());
		}
		
		return ret;
	}
	
}
