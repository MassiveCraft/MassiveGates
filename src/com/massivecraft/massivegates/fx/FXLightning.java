package com.massivecraft.massivegates.fx;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import com.massivecraft.massivegates.Gate;

public class FXLightning extends GateFxBase
{
	@Override public String getName() { return "Lightning"; }
	@Override public String getUsagePattern() { return "Lightning"; }
	@Override public String getDesc() { return "Harmless lightning strike."; }
	@Override public String getDesc(Map<String, Object> fxargs) {	return this.getDesc(); }

	@Override
	public void run(Map<String, Object> fxargs, Gate gate, Entity entity)
	{
		Location location = null;
		if (entity != null)
		{
			location = entity.getLocation();
		}
		else if (gate != null)
		{
			location = gate.calcGateCenter();
		}
		
		if (location != null)
		{
			location.getWorld().strikeLightningEffect(location);
		}
	}

	@Override
	public Map<String, Object> innerParse(String parsie)
	{
		if (parsie.equalsIgnoreCase("lightning"))
		{
			return new HashMap<String, Object>(0);
		}
		return null;
	}
	
	protected FXLightning() {}
	protected static FXLightning instance = new FXLightning();
	public static FXLightning getInstance() { return instance; }
}
