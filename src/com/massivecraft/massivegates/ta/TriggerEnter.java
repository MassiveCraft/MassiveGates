package com.massivecraft.massivegates.ta;

public class TriggerEnter extends BaseTrigger
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static TriggerEnter i = new TriggerEnter();
	public static TriggerEnter get() { return i; }
	
	protected TriggerEnter()
	{
		super("mgcore_enter", "Enter", "When a player walks into gate content.");
	}
	
}
