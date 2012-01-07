package com.massivecraft.massivegates.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import com.massivecraft.massivegates.P;

public class TeleportUtil
{
	public static void safeTeleport(Entity entity, Location location, long delay)
	{
		Bukkit.getScheduler().scheduleSyncDelayedTask(P.p, new TeleportTask(entity, location), delay);
	}
	
	public static void safeTeleport(Entity entity, Location location)
	{
		safeTeleport(entity, location, 0);
	}
}
