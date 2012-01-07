package com.massivecraft.massivegates.when;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.Gate;

public class ActionFxStrike extends BaseAction
{
	protected static ActionFxStrike instance = new ActionFxStrike();
	public static ActionFxStrike getInstance() { return instance; }
	protected ActionFxStrike()
	{
		super("mgcore_fx_strike", "FxStrike", "Non damaging lightning strike effect.");
	}
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
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
}