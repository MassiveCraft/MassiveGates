package com.massivecraft.massivegates.ta;

public class TriggerUse extends BaseTrigger
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static TriggerUse i = new TriggerUse();
	public static TriggerUse get() { return i; }
	
	protected TriggerUse()
	{
		super("mgcore_use", "Use", "When the gate is used.");
	}
	
}
