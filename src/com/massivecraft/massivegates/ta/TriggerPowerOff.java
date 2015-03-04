package com.massivecraft.massivegates.ta;

public class TriggerPowerOff extends BaseTrigger
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static TriggerPowerOff i = new TriggerPowerOff();
	public static TriggerPowerOff get() { return i; }
	
	protected TriggerPowerOff()
	{
		super("mgcore_power_off", "PowerOff", "Frame redstone power off.");
	}
	
}
