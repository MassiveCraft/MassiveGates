package com.massivecraft.massivegates.event.abs;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import com.massivecraft.massivegates.P;

@SuppressWarnings("serial")
public class GateEvent extends Event implements Runnable
{
	public GateEvent(String name)
	{
		super(name);
	}

	@Override
	public void run()
	{
		Bukkit.getPluginManager().callEvent(this);
	}
	
	public void run(long delay)
	{
		Bukkit.getScheduler().scheduleSyncDelayedTask(P.p, this, delay);
	}
}
