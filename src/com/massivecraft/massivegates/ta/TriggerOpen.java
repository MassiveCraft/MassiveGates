package com.massivecraft.massivegates.ta;

public class TriggerOpen extends BaseTrigger
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static TriggerOpen i = new TriggerOpen();
	public static TriggerOpen get() { return i; }
	
	protected TriggerOpen()
	{
		super("mgcore_open", "Open", "When the gate opens.");
	}
	
}
