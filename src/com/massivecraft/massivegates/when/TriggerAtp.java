package com.massivecraft.massivegates.when;

public class TriggerAtp extends BaseTrigger
{
	protected static TriggerAtp instance = new TriggerAtp();
	public static TriggerAtp getInstance() { return instance; }
	protected TriggerAtp()
	{
		super("mgcore_atp", "ATP", "Right after the gate user is teleported.");
	}
}