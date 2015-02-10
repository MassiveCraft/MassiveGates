package com.massivecraft.massivegates.ta;

public class TriggerPowerOn extends BaseTrigger
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static TriggerPowerOn instance = new TriggerPowerOn();
	public static TriggerPowerOn get() { return instance; }
	
	protected TriggerPowerOn()
	{
		super("mgcore_power_on", "PowerOn", "Frame redstone power on.");
	}
	
}
