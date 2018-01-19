package com.massivecraft.massivegates.ta;

import com.massivecraft.massivecore.collections.MassiveMap;

import java.util.LinkedHashMap;
import java.util.Map;

public class TriggerHour extends BaseTrigger
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected TriggerHour(Integer h)
	{
		super("mgcore_h_"+h, "H"+h, "Ingame hour "+h+" of 23.");
	}
	
	public static TriggerHour get(Integer h) { return triggerHours.get(h); }
	
	public static Map<Integer, TriggerHour> triggerHours;
	static
	{
		triggerHours = new MassiveMap<>();
		for (int h = 0; h <= 23; h++)
		{
			triggerHours.put(h, new TriggerHour(h));
		}
	}
	
}
