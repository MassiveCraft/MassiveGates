package com.massivecraft.massivegates.when;

public class TriggerOpen extends BaseTrigger
{
	protected static TriggerOpen instance = new TriggerOpen();
	public static TriggerOpen getInstance() { return instance; }
	protected TriggerOpen()
	{
		super("massivegates_core_open", "Open", "When the gate opens.");
	}
}