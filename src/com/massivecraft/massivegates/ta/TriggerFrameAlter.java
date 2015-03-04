package com.massivecraft.massivegates.ta;

public class TriggerFrameAlter extends BaseTrigger
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static TriggerFrameAlter i = new TriggerFrameAlter();
	public static TriggerFrameAlter get() { return i; }
	
	protected TriggerFrameAlter()
	{
		super("mgcore_frame_alter", "FrameAlter", "When the gate frame is altered.");
	}
	
}
