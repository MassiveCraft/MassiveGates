package com.massivecraft.massivegates.ta;

public class TriggerBtp extends BaseTrigger
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static TriggerBtp i = new TriggerBtp();
	public static TriggerBtp get() { return i; }
	
	protected TriggerBtp()
	{
		super("mgcore_btp", "BTP", "Right before the gate user is teleported.");
	}
	
}
