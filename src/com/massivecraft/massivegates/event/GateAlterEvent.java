package com.massivecraft.massivegates.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.event.abs.SingleGateEvent;

public class GateAlterEvent extends SingleGateEvent
{
	// -------------------------------------------- //
	// REQUIRED EVENT CODE
	// -------------------------------------------- //
	
	private static final HandlerList handlers = new HandlerList();
	public HandlerList getHandlers() { return handlers; }
	public static HandlerList getHandlerList() { return handlers; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //

	// FIELD: alterType
	private GateAlterType alterType;
	public GateAlterType getAlterType() { return this.alterType; }
	public boolean isPlayerInduced() { return this.alterType.isPlayerInduced(); }
	
	// FIELD: player
	private Player player;
	public Player getPlayer() { return this.player; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public GateAlterEvent(Gate gate, GateAlterType alterType, Player player)
	{
		super(gate);
		this.alterType = alterType;
		this.player = player;
	}
	
	// -------------------------------------------- //
	// ENUM
	// -------------------------------------------- //
	
	public enum GateAlterType
	{
		PLACE(true),
		BREAK(true),
		BUCKET_FILL(true),
		BUCKET_EMPTY(true),
		IGNITE(true),
		PHYSICS(false),
		FLOW(false),
		FORM(false),
		FADE(false),
		BURN(false),
		SPREAD(false),
		PISTON_EXTEND(false),
		PISTON_RETRACT(false),
		EXPLODE(false),
		
		// END OF LIST
		;
		
		private final boolean playerInduced;
		public boolean isPlayerInduced() { return this.playerInduced; }
		
		private GateAlterType(final boolean playerInduced)
		{
			this.playerInduced = playerInduced;
		}
	}
	
}
