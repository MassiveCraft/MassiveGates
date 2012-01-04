package com.massivecraft.massivegates.when;

public class TriggerPlayerEnter extends BaseTrigger
{
	protected static TriggerPlayerEnter instance = new TriggerPlayerEnter();
	public static TriggerPlayerEnter getInstance() { return instance; }
	protected TriggerPlayerEnter()
	{
		super("massivegates_core_player_enter", "PlayerEnter", "When a player walks into the content of the gate.");
	}
}