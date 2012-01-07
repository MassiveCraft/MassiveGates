package com.massivecraft.massivegates.when;

public class TriggerUse extends BaseTrigger
{
	protected static TriggerUse instance = new TriggerUse();
	public static TriggerUse getInstance() { return instance; }
	protected TriggerUse()
	{
		super("mgcore_use", "Use", "When the gate is used.");
	}
}