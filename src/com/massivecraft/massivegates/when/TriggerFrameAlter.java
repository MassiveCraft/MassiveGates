package com.massivecraft.massivegates.when;

public class TriggerFrameAlter extends BaseTrigger
{
	protected static TriggerFrameAlter instance = new TriggerFrameAlter();
	public static TriggerFrameAlter getInstance() { return instance; }
	protected TriggerFrameAlter()
	{
		super("massivegates_core_frame_alter", "FrameAlter", "When the gate frame is altered.");
	}
}