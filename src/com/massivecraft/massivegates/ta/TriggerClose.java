package com.massivecraft.massivegates.ta;

public class TriggerClose extends BaseTrigger
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static TriggerClose instance = new TriggerClose();
	public static TriggerClose get() { return instance; }
	
	protected TriggerClose()
	{
		super("mgcore_close", "Close", "When the gate closes.");
	}
	
}
