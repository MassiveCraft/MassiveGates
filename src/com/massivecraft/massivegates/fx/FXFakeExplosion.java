package com.massivecraft.massivegates.fx;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Entity;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.mcore1.util.SmokeUtil;

public class FXFakeExplosion extends GateFxBase
{
	public final static String RADIUS = "radius";
	public final static Integer defaultRadius = 4;
	
	@Override public String getName() { return "FakeExplosion"; }
	@Override public String getUsagePattern() { return "FakeExplosion:radius"; }
	@Override public String getDesc() { return "Fake TNT explosion."; }
	@Override public String getDesc(Map<String, Object> fxArgs)
	{
		Object oradius = fxArgs.get(RADIUS);
		if ( ! (oradius instanceof Integer))
		{
			return this.getDesc();
		}
		int radius = (Integer) oradius;
		return "Fake TNT explosion (radius "+radius+").";
	}

	@Override
	public void run(Map<String, Object> fxArgs, Gate gate, Entity entity)
	{
		int radius = defaultRadius;
		if (fxArgs.containsKey(RADIUS))
		{
			radius = (int) fxArgs.get(RADIUS);
		}
		
		if (entity != null)
		{
			SmokeUtil.fakeExplosion(entity.getLocation(), radius);
		}
		else if (gate != null)
		{
			SmokeUtil.fakeExplosion(gate.calcGateCenter(), radius);
		}
	}

	@Override
	public Map<String, Object> innerParse(String parsie)
	{
		if ( ! parsie.toLowerCase().startsWith("fakeex")) return null;
		
		Map<String, Object> ret = new HashMap<String, Object>(1);
		
		Integer radius = defaultRadius;
		String[] parts = parsie.split(":");
		if (parts.length >= 2)
		{
			try
			{
				radius = Integer.parseInt(parts[1]);
			}
			catch (Exception e)
			{
				this.parseErrors.add("<b>Invalid radius.");
			}
			
			if (radius < 0) radius = -radius;
		}
		ret.put(RADIUS, radius);
		return ret;
	}
	
	protected FXFakeExplosion() {}
	protected static FXFakeExplosion instance = new FXFakeExplosion();
	public static FXFakeExplosion getInstance() { return instance; }
}
