package com.massivecraft.massivegates;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.World;

import com.massivecraft.massivegates.ta.TriggerHour;

public class HourTriggingTask implements Runnable
{
	public static final int ticksAtMidnight = 18000;
	public static final int ticksPerDay = 24000;
	public static final int ticksPerHour = 1000;
	public static final double ticksPerMinute = 1000d / 60d;
	public static final double ticksPerSecond = 1000d / 60d / 60d;
	
	protected Map<String, Integer> worldName2Time;
	
	public HourTriggingTask()
	{
		this.worldName2Time = new HashMap<String, Integer>();
	}
	
	@Override
	public void run()
	{
		for (World world : Bukkit.getWorlds())
		{
			this.runWorld(world);
		}
	}
	
	public void runWorld(World world)
	{
		// Fetch ticks now and before.
		int ticksNow = (int) world.getTime();
		int ticksBefore = ticksNow;
		Integer ticksStored = this.worldName2Time.get(world.getName());
		if (ticksStored != null)
		{
			ticksBefore = ticksStored;
		}
		
		// What would that mean in terms of hours?
		int hNow = ticks2hour(ticksNow);
		int hBefore = ticks2hour(ticksBefore);
		
		// Ensure hNow >= hBefore
		if (hNow < hBefore)
		{
			hNow += 24;
		}
		
		// Trigger all hours we passed
		for (int h = hBefore; h < hNow; h++)
		{
			this.triggerWorldHour(world, h+1);
		}
		
		// Store now as before
		this.worldName2Time.put(world.getName(), ticksNow);
	}
	
	public void triggerWorldHour(World world, int h)
	{
		h = h % 24;
		for (Gate gate : Gates.i.getAll())
		{
			World gateWorld = gate.calcGateWorld();
			if ( ! world.equals(gateWorld)) continue;
			gate.trigger(TriggerHour.getInstance(h), null, null);
		}
	}
	
	public static int hour2ticks(int hour)
	{
		int ret = ticksAtMidnight;
		ret += hour * ticksPerHour;
		ret %= ticksPerDay;
		return ret;
	}
	
	public static int ticks2hour(int ticks)
	{
		int ret = ticks;
		ret += ticksPerDay;
		ret -= ticksAtMidnight;
		ret %= ticksPerDay;
		ret /= ticksPerHour;
		return ret;
	}
}