package com.massivecraft.massivegates.when;

public class TriggerBtp extends BaseTrigger
{
	protected static TriggerBtp instance = new TriggerBtp();
	public static TriggerBtp getInstance() { return instance; }
	protected TriggerBtp()
	{
		super("massivegates_core_btp", "BTP", "Right before the gate user is teleported.");
	}
}