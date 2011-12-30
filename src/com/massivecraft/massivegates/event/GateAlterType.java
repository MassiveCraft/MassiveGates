package com.massivecraft.massivegates.event;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.Listener;

import com.massivecraft.massivegates.Conf;
import com.massivecraft.massivegates.P;

public enum GateAlterType
{
	PLACE(true, Type.BLOCK_PLACE),
	BREAK(true, Type.BLOCK_BREAK),
	BUCKET_FILL(true, Type.PLAYER_BUCKET_FILL),
	BUCKET_EMPTY(true, Type.PLAYER_BUCKET_EMPTY),
	IGNITE(true, Type.BLOCK_IGNITE),
	PHYSICS(false, Type.BLOCK_PHYSICS),
	FLOW(false, Type.BLOCK_FROMTO),
	FORM(false, Type.BLOCK_FORM),
	FADE(false, Type.BLOCK_FADE),
	BURN(false, Type.BLOCK_BURN),
	SPREAD(false, Type.BLOCK_SPREAD),
	PISTON_EXTEND(false, Type.BLOCK_PISTON_EXTEND),
	PISTON_RETRACT(false, Type.BLOCK_PISTON_RETRACT),
	EXPLODE(false, Type.ENTITY_EXPLODE),
	;
	
	private final boolean playerInduced;
	public boolean isPlayerInduced() { return this.playerInduced; }
	
	private final Type eventType;
	public Type getEventType() { return this.eventType; }
	
	private GateAlterType(final boolean playerInduced, final Type eventType)
	{
		this.playerInduced = playerInduced;
		this.eventType = eventType;
	}
	
	public boolean canFrame()
	{
		return Conf.canFrame.get(this) == null ? false : Conf.canFrame.get(this);
	}
	
	public boolean canContent()
	{
		return Conf.canContent.get(this) == null ? false : Conf.canContent.get(this);
	}
	
	public static void registerListeners()
	{
		for (GateAlterType alterType : GateAlterType.values())
		{
			if (alterType.canContent())
			{
				// Register listener content monitor
				P.p.registerEvent(alterType.getEventType(), alterType.getListener(false, true), Priority.Monitor);
			}
			else
			{
				// Register listener content
				P.p.registerEvent(alterType.getEventType(), alterType.getListener(false, false), Priority.Normal);
			}
			
			if (alterType.canFrame())
			{
				// Register listener frame monitor
				P.p.registerEvent(alterType.getEventType(), alterType.getListener(true, true), Priority.Monitor);
			}
			else
			{
				// Register listener frame
				P.p.registerEvent(alterType.getEventType(), alterType.getListener(true, false), Priority.Normal);
			}
		}
	}
	
	public Listener getListener(boolean frame, boolean monitor)
	{
		if (frame)
		{
			if (monitor)
			{
				// FRAME MONITOR
				if (this == BUCKET_FILL) return P.p.gateAlterMonitorFramePlayerListener;
				if (this == BUCKET_EMPTY) return P.p.gateAlterMonitorFramePlayerListener;
				if (this == EXPLODE) return P.p.gateAlterMonitorFrameEntityListener;
				return P.p.gateAlterMonitorFrameBlockListener;
			}
			else
			{
				// FRAME NORMAL
				if (this == BUCKET_FILL) return P.p.gateAlterCancelFramePlayerListener;
				if (this == BUCKET_EMPTY) return P.p.gateAlterCancelFramePlayerListener;
				if (this == EXPLODE) return P.p.gateAlterCancelFrameEntityListener;
				return P.p.gateAlterCancelFrameBlockListener;
			}
		}
		else
		{
			if (monitor)
			{
				// CONTENT MONITOR
				if (this == BUCKET_FILL) return P.p.gateAlterMonitorContentPlayerListener;
				if (this == BUCKET_EMPTY) return P.p.gateAlterMonitorContentPlayerListener;
				if (this == EXPLODE) return P.p.gateAlterMonitorContentEntityListener;
				return P.p.gateAlterMonitorContentBlockListener;
			}
			else
			{
				// CONTENT NORMAL
				if (this == BUCKET_FILL) return P.p.gateAlterCancelContentPlayerListener;
				if (this == BUCKET_EMPTY) return P.p.gateAlterCancelContentPlayerListener;
				if (this == EXPLODE) return P.p.gateAlterCancelContentEntityListener;
				return P.p.gateAlterCancelContentBlockListener;
			}
		}
	}
}
