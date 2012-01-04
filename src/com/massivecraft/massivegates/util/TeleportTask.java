package com.massivecraft.massivegates.util;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class TeleportTask implements Runnable
{
	protected Entity entity;
	protected Location location;
	public TeleportTask(Entity entity, Location location)
	{
		this.entity = entity;
		this.location = location;
	}

	@Override
	public void run()
	{
		this.entity.teleport(location);
	}
}
