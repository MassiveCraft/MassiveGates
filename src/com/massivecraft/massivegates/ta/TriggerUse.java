package com.massivecraft.massivegates.ta;

public class TriggerUse extends BaseTrigger
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static TriggerUse instance = new TriggerUse();
	public static TriggerUse get() { return instance; }
	
	protected TriggerUse()
	{
		super("mgcore_use", "Use", "When the gate is used.");
	}
	
}
