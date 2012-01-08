package com.massivecraft.massivegates.ta;

import java.util.LinkedHashMap;
import java.util.Map;

public class TriggerHour extends BaseTrigger
{
	public static Map<Integer, TriggerHour> triggerHours;
	static
	{
		triggerHours = new LinkedHashMap<Integer, TriggerHour>();
		for (int h = 0; h <= 23; h++)
		{
			triggerHours.put(h, new TriggerHour(h));
		}
	}
	public static TriggerHour getInstance(Integer h) { return triggerHours.get(h); }
	
	protected TriggerHour(Integer h)
	{
		super("mgcore_h_"+h, "H"+h, "Ingame hour "+h+" of 23.");
	}
}