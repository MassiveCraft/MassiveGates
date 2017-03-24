package com.massivecraft.massivegates.entity;

import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivecore.store.Coll;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivecore.xlib.gson.JsonObject;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.Trigger;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GateColl extends Coll<Gate>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static GateColl i = new GateColl();
	public static GateColl get() { return i; }

	// -------------------------------------------- //
	// STACK TRACEABILITY
	// -------------------------------------------- //
	
	@Override
	public void onTick()
	{
		super.onTick();
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// PSToGate
	protected Map<PS, Gate> contentToGate = new HashMap<>();
	protected Map<PS, Gate> frameToGate = new HashMap<>();
	
	// Trigger
	protected Set<Trigger> triggers = new LinkedHashSet<>();
	protected Map<String, Trigger> id2trigger = new HashMap<>();
	protected Map<String, Trigger> name2trigger = new HashMap<>();
	
	// Action
	protected Set<Action> actions = new LinkedHashSet<>();
	protected Map<String, Action> id2action = new HashMap<>();
	protected Map<String, Action> name2action = new HashMap<>();
	
	// -------------------------------------------- //
	// FIELDS: ...toGate
	// -------------------------------------------- //
	
	// Raw
	public Map<PS, Gate> getContentToGate() { return this.contentToGate; }
	public Map<PS, Gate> getFrameCoordToGate() { return this.frameToGate; }
	
	// Finer
	public Gate getGateAtContentCoord(PS coord) { return this.contentToGate.get(coord); }
	public Gate getGateAtFrameCoord(PS coord) { return this.frameToGate.get(coord); }
	public Gate getGateAtCoord(PS coord)
	{
		Gate gate = this.getGateAtFrameCoord(coord);
		if (gate != null) return gate;
		return this.getGateAtContentCoord(coord);
	}
	
	public void clearIndexFor(Gate gate)
	{
		for (PS coord : gate.getContent())
		{
			this.contentToGate.remove(coord);
		}
		for (PS coord : gate.getFrame())
		{
			this.frameToGate.remove(coord);
		}
	}
	
	public void buildIndexFor(Gate gate)
	{
		for (PS coord : gate.getContent())
		{
			this.contentToGate.put(coord, gate);
		}
		for (PS coord : gate.getFrame())
		{
			this.frameToGate.put(coord, gate);
		}
	}
	
	// -------------------------------------------- //
	// FIELDS: action
	// -------------------------------------------- //
	
	public void registerAction(Action action)
	{
		this.actions.add(action);
		this.id2action.put(action.getId(), action);
		this.name2action.put(action.getName(), action);
	}
	
	public Set<Action> getActions()
	{
		return this.actions;
	}
	
	public Action getActionId(String id)
	{
		return this.id2action.get(id);
	}
	
	// -------------------------------------------- //
	// FIELD: triggers
	// -------------------------------------------- //
	
	public <T extends Trigger> void registerTrigger(T trigger)
	{
		this.triggers.add(trigger);
		this.id2trigger.put(trigger.getId(), trigger);
		this.name2trigger.put(trigger.getName(), trigger);
	}
	
	public void registerTriggers(Collection<? extends Trigger> triggers)
	{
		for (Trigger trigger : triggers)
		{
			this.registerTrigger(trigger);
		}
	}
	
	public Set<Trigger> getTriggers()
	{
		return this.triggers;
	}
	
	public Trigger getTriggerId(String id)
	{
		return this.id2trigger.get(id);
	}
	
	public Trigger getTriggerName(String name)
	{
		Trigger ret = null;
		String bestName = Txt.getBestCIStart(name2trigger.keySet(), name);
		if (bestName != null) ret = name2trigger.get(bestName);
		return ret;
	}
	
	// -------------------------------------------- //
	// BUILT IN EVENTS
	// -------------------------------------------- //
	
	@Override
	public synchronized Gate removeAtLocalFixed(String id)
	{
		Gate entity = this.id2entity.get(id);
		
		this.clearIndexFor(entity);
		
		return super.removeAtLocalFixed(id);
	}
	
	@Override
	public synchronized void loadFromRemoteFixed(String id, Entry<JsonObject, Long> remoteEntry)
	{
		Gate gate = this.id2entity.get(id);
		if (gate == null)
		{
			super.loadFromRemoteFixed(id, remoteEntry);
			gate = this.id2entity.get(id);
			this.buildIndexFor(gate);
		}
		else
		{
			this.clearIndexFor(gate);
			super.loadFromRemoteFixed(id, remoteEntry);
			this.buildIndexFor(gate);
		}
	}
	
	/*
	Convert to this?
	@Override
	public void preDetach(Gate entity, String id)
	{
		super.preDetach(entity, id);
		
		this.clearIndexFor(entity);
		
		// Run event
		new GateDetachEvent(entity).run();
	}

	@Override
	protected synchronized String attach(Gate entity, Object oid, boolean noteChange)
	{
		String ret = super.attach(entity, oid, noteChange);
		if (ret != null)
		{
			// The attach was successful
			
			// Index
			for (PS coord : entity.getContent())
			{
				this.contentToGate.put(coord, entity);
			}
			for (PS coord : entity.getFrame())
			{
				this.frameToGate.put(coord, entity);
			}
			
			// Run event
			new GateAttachEvent(entity).run(); // TODO
		}
		return ret;
	}*/
	
}
