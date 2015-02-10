package com.massivecraft.massivegates.ta;

public class TriggerBtp extends BaseTrigger
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static TriggerBtp instance = new TriggerBtp();
	public static TriggerBtp get() { return instance; }
	
	protected TriggerBtp()
	{
		super("mgcore_btp", "BTP", "Right before the gate user is teleported.");
	}
	
}
