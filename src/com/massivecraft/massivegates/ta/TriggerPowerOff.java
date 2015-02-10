package com.massivecraft.massivegates.ta;

public class TriggerPowerOff extends BaseTrigger
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static TriggerPowerOff instance = new TriggerPowerOff();
	public static TriggerPowerOff get() { return instance; }
	
	protected TriggerPowerOff()
	{
		super("mgcore_power_off", "PowerOff", "Frame redstone power off.");
	}
	
}
