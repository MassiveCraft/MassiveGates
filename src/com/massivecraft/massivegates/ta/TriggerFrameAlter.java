package com.massivecraft.massivegates.ta;

public class TriggerFrameAlter extends BaseTrigger
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static TriggerFrameAlter instance = new TriggerFrameAlter();
	public static TriggerFrameAlter get() { return instance; }
	
	protected TriggerFrameAlter()
	{
		super("mgcore_frame_alter", "FrameAlter", "When the gate frame is altered.");
	}
	
}
