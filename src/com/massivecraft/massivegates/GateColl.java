package com.massivecraft.massivegates;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.massivecraft.massivegates.event.GateAttachEvent;
import com.massivecraft.massivegates.event.GateDetachEvent;
import com.massivecraft.massivegates.event.GateSaveEvent;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.Trigger;
import com.massivecraft.mcore.ps.PS;
import com.massivecraft.mcore.store.Coll;
import com.massivecraft.mcore.store.MStore;
import com.massivecraft.mcore.store.ModificationState;
import com.massivecraft.mcore.util.Txt;

public class GateColl extends Coll<Gate, String>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	public static GateColl i = new GateColl();
	private GateColl()
	{
		super(MStore.getDb(ConfServer.dburi), P.p, "ai", Const.gateBasename, Gate.class, String.class, false);
	}
	
	// -------------------------------------------- //
	// INDEX
	// -------------------------------------------- //
	
	protected Map<PS, Gate> contentToGate = new HashMap<PS, Gate>();
	protected Map<PS, Gate> frameToGate = new HashMap<PS, Gate>();
	
	public Map<PS, Gate> getContentToGate() { return this.contentToGate; }
	public Map<PS, Gate> getFrameCoordToGate() { return this.frameToGate; }
	
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
	// BUILT IN EVENTS
	// -------------------------------------------- //
	
	@Override
	public ModificationState syncId(Object oid)
	{
		String id = this.fixId(oid);
		
		Gate gate = this.id2entity.get(id);
		
		if (gate != null)
		{
			// Run event
			new GateSaveEvent(gate).run();
		}
		
		return super.syncId(id);
	}
	
	@Override
	public synchronized Gate removeAtLocal(Object oid)
	{
		String id = this.fixId(oid);
		
		Gate entity = this.id2entity.get(id);
		
		this.clearIndexFor(entity);
		
		// Run event
		new GateDetachEvent(entity).run();
		
		return super.removeAtLocal(id);
	}
	
	@Override
	public synchronized void loadFromRemote(Object oid)
	{
		String id = this.fixId(oid);
		
		Gate gate = this.id2entity.get(id);
		if (gate == null)
		{
			super.loadFromRemote(id);
			gate = this.id2entity.get(id);
			this.buildIndexFor(gate);
			new GateAttachEvent(gate).run();
		}
		else
		{
			this.clearIndexFor(gate);
			super.loadFromRemote(id);
			this.buildIndexFor(gate);
		}
	}
	
	/*
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
	
	// -------------------------------------------- //
	// WHEN ACTION
	// -------------------------------------------- //
	
	protected Set<Action> actions = new LinkedHashSet<Action>();
	protected Map<String, Action> id2action = new HashMap<String, Action>();
	protected Map<String, Action> name2action = new HashMap<String, Action>();
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
	public Action getActionName(String name)
	{
		Action ret = null;
		String bestName = Txt.getBestCIStart(name2action.keySet(), name);
		if (bestName != null) ret = name2action.get(bestName);
		return ret;
	}
	
	// -------------------------------------------- //
	// WHEN TRIGGER
	// -------------------------------------------- //
	
	protected Set<Trigger> triggers = new LinkedHashSet<Trigger>();
	protected Map<String, Trigger> id2trigger = new HashMap<String, Trigger>();
	protected Map<String, Trigger> name2trigger = new HashMap<String, Trigger>();
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
	
}
