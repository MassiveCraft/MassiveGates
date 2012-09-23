package com.massivecraft.massivegates;

import java.io.File;
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
import com.massivecraft.mcore4.PS;
import com.massivecraft.mcore4.persist.gson.GsonClassManager;
import com.massivecraft.mcore4.util.Txt;

public class Gates extends GsonClassManager<Gate>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	public static Gates i = new Gates();
	
	private Gates()
	{
		super(P.p.gson, new File(P.p.getDataFolder(), "gate"), false, true);
		P.p.persist.setManager(Gate.class, this);
		P.p.persist.setSaveInterval(Gate.class, 1000*60*30);
		
		this.loadAll();
	}

	@Override
	public Class<Gate> getManagedClass() { return Gate.class; }

	@Override
	public String idFix(Object oid)
	{
		if (oid == null) return null;
		if (oid instanceof String) return (String) oid;
		return null;
	}

	@Override
	public boolean idCanFix(Class<?> clazz) { return clazz.equals(String.class); }
	
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
	
	// -------------------------------------------- //
	// BUILT IN EVENTS
	// -------------------------------------------- //
	
	@Override
	protected synchronized String attach(Gate entity, Object oid, boolean allowExistingIdUsage)
	{
		String ret = super.attach(entity, oid, allowExistingIdUsage);
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
			new GateAttachEvent(entity).run();
		}
		return ret;
		
	}
	
	@Override
	protected synchronized void detach(Gate entity, String id)
	{
		for (PS coord : entity.getContent())
		{
			this.contentToGate.remove(coord);
		}
		for (PS coord : entity.getFrame())
		{
			this.frameToGate.remove(coord);
		}
		
		// Run event
		new GateDetachEvent(entity).run();
		
		super.detach(entity, id);
	}
	
	@Override
	protected boolean save(String id, Gate entity)
	{
		// Run event
		new GateSaveEvent(entity).run();
		
		return super.save(id, entity);
	}
	
	// -------------------------------------------- //
	// WHEN ACTION
	// -------------------------------------------- //
	
	/*
	protected Set<GateFx> fxs = new HashSet<GateFx>();
	public Set<GateFx> getFxs() { return this.fxs; }
	public void registerFx(GateFx fx) {this.fxs.add(fx); }
	public GateFx getFx(String parsie)
	{
		for (GateFx gfx : this.getFxs())
		{
			if (gfx.parse(parsie) != null) return gfx; 
		}
		return null;
	}*/
	
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
