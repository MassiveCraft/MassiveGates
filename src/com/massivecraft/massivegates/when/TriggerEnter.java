package com.massivecraft.massivegates.when;

public class TriggerEnter extends BaseTrigger
{
	protected static TriggerEnter instance = new TriggerEnter();
	public static TriggerEnter getInstance() { return instance; }
	protected TriggerEnter()
	{
		super("mgcore_enter", "Enter", "When a player walks into gate content.");
	}
}