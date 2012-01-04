package com.massivecraft.massivegates.event;

import org.bukkit.entity.Player;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.event.abs.SingleGateEvent;

@SuppressWarnings("serial")
public class GateAlterEvent extends SingleGateEvent
{
	// FIELD: alterType
	private GateAlterType alterType;
	public GateAlterType getAlterType() { return this.alterType; }
	public boolean isPlayerInduced() { return this.alterType.isPlayerInduced(); }
	
	// FIELD: player
	private Player player;
	public Player getPlayer() { return this.player; }
	
	public GateAlterEvent(Gate gate, GateAlterType alterType, Player player)
	{
		super("GateAlterEvent", gate);
		this.alterType = alterType;
		this.player = player;
	}
}
