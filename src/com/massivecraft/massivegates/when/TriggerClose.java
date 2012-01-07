package com.massivecraft.massivegates.when;

public class TriggerClose extends BaseTrigger
{
	protected static TriggerClose instance = new TriggerClose();
	public static TriggerClose getInstance() { return instance; }
	protected TriggerClose()
	{
		super("mgcore_close", "Close", "When the gate closes.");
	}
}