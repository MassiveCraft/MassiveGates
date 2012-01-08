package com.massivecraft.massivegates.ta;

public class TriggerBtp extends BaseTrigger
{
	protected static TriggerBtp instance = new TriggerBtp();
	public static TriggerBtp getInstance() { return instance; }
	protected TriggerBtp()
	{
		super("mgcore_btp", "BTP", "Right before the gate user is teleported.");
	}
}