package com.massivecraft.massivegates.when;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.WorldCoord3;
import com.massivecraft.mcore1.util.SmokeUtil;

public class ActionFxSmoke extends BaseAction
{
	protected static ActionFxSmoke instance = new ActionFxSmoke();
	public static ActionFxSmoke getInstance() { return instance; }
	protected ActionFxSmoke()
	{
		super("mgcore_fx_smoke", "FxSmoke", "Smoke effect.");
	}
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		List<Location> locations = new ArrayList<Location>();
		
		if (entity != null)
		{
			Location lower = entity.getLocation();
			locations.add(lower);
			locations.add(lower.getBlock().getRelative(BlockFace.UP).getLocation());
		}
		
		if (gate != null)
		{
			for (WorldCoord3 coord : gate.getContent())
			{
				locations.add(coord.getLocation());
			}
		}
		
		SmokeUtil.spawnCloudSimple(locations);
	}
}