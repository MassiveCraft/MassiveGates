package com.massivecraft.massivegates.fx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.WorldCoord3;
import com.massivecraft.mcore1.util.SmokeUtil;

public class FXSmoke extends GateFxBase
{
	@Override public String getName() { return "Smoke"; }
	@Override public String getUsagePattern() { return "Smoke"; }
	@Override public String getDesc() { return "Emitt smoke."; }
	@Override public String getDesc(Map<String, Object> fxArgs) { return this.getDesc(); }

	@Override
	public void run(Map<String, Object> fxArgs, Gate gate, Entity entity)
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

	@Override
	public Map<String, Object> innerParse(String parsie)
	{
		if (parsie.equalsIgnoreCase("smoke"))
		{
			return new HashMap<String, Object>(0);
		}
		return null;
	}
	
	protected FXSmoke() {}
	protected static FXSmoke instance = new FXSmoke();
	public static FXSmoke getInstance() { return instance; }
}
