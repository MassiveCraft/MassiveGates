package com.massivecraft.massivegates.ta;

public class TriggerOpen extends BaseTrigger
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static TriggerOpen instance = new TriggerOpen();
	public static TriggerOpen get() { return instance; }
	
	protected TriggerOpen()
	{
		super("mgcore_open", "Open", "When the gate opens.");
	}
	
}
