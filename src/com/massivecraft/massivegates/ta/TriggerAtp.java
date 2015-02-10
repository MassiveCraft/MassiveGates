package com.massivecraft.massivegates.ta;

public class TriggerAtp extends BaseTrigger
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static TriggerAtp instance = new TriggerAtp();
	public static TriggerAtp get() { return instance; }
	
	protected TriggerAtp()
	{
		super("mgcore_atp", "ATP", "Right after the gate user is teleported.");
	}
	
}
