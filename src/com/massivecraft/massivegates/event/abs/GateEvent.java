package com.massivecraft.massivegates.event.abs;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import com.massivecraft.massivegates.MassiveGates;

public abstract class GateEvent extends Event implements Runnable
{
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void run()
	{
		Bukkit.getPluginManager().callEvent(this);
	}
	
	public void run(long delay)
	{
		Bukkit.getScheduler().scheduleSyncDelayedTask(MassiveGates.get(), this, delay);
	}
	
}
