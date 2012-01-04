package com.massivecraft.massivegates.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.event.abs.DualGateEvent;

@SuppressWarnings("serial")
public class GatePlayerWalkEvent extends DualGateEvent implements Cancellable
{
	// FIELD: cancelled
	private boolean cancelled = false;
	@Override public boolean isCancelled() { return this.cancelled; }
	@Override public void setCancelled(boolean cancel) { this.cancelled = cancel; }
	
	// FIELD: walkType
	protected GatePlayerWalkType walkType;
	public GatePlayerWalkType getWalkType() { return this.walkType; }
	
	// FIELD: user
	private Player player;
	public Player getPlayer() { return this.player; }
	
	public GatePlayerWalkEvent(Player player, Gate gateFrom, Gate gateTo, GatePlayerWalkType walkType)
	{
		super("GatePlayerWalkEvent");
		this.player = player;
		this.gateFrom = gateFrom;
		this.gateTo = gateTo;
		this.walkType = walkType;
	}
}
