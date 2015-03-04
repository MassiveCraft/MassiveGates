package com.massivecraft.massivegates.ta;

public class TriggerClose extends BaseTrigger
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static TriggerClose i = new TriggerClose();
	public static TriggerClose get() { return i; }
	
	protected TriggerClose()
	{
		super("mgcore_close", "Close", "When the gate closes.");
	}
	
}
