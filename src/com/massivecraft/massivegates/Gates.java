package com.massivecraft.massivegates;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.massivecraft.massivegates.event.GateAttachEvent;
import com.massivecraft.massivegates.event.GateDetachEvent;
import com.massivecraft.massivegates.event.GateSaveEvent;
import com.massivecraft.massivegates.fx.FXFakeExplosion;
import com.massivecraft.massivegates.fx.FXLightning;
import com.massivecraft.massivegates.fx.FXSmoke;
import com.massivecraft.massivegates.fx.GateFx;
import com.massivecraft.mcore1.persist.gson.GsonClassManager;

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
		
		// Register inhouse fx
		this.registerFx(FXLightning.getInstance());
		this.registerFx(FXSmoke.getInstance());
		this.registerFx(FXFakeExplosion.getInstance());
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
	
	protected Map<WorldCoord3, Gate> contentToGate = new HashMap<WorldCoord3, Gate>();
	protected Map<WorldCoord3, Gate> frameToGate = new HashMap<WorldCoord3, Gate>();
	
	public Map<WorldCoord3, Gate> getContentToGate() { return this.contentToGate; }
	public Map<WorldCoord3, Gate> getFrameCoordToGate() { return this.frameToGate; }
	
	public Gate getGateAtContentCoord(WorldCoord3 coord) { return this.contentToGate.get(coord); }
	public Gate getGateAtFrameCoord(WorldCoord3 coord) { return this.frameToGate.get(coord); }
	public Gate getGateAtCoord(WorldCoord3 coord)
	{
		Gate gate = this.getGateAtFrameCoord(coord);
		if (gate != null) return gate;
		return this.getGateAtContentCoord(coord);
	}
	
	/*public void rawReindex()
	{
		this.frameToGate.clear();
		this.contentToGate.clear();
		for (Gate gate : this.getAll())
		{
			for (WorldCoord3 coord : gate.getFrame())
			{
				this.frameToGate.put(coord, gate);
			}
			for (WorldCoord3 coord : gate.getContent())
			{
				this.contentToGate.put(coord, gate);
			}
		}
	}

	protected boolean shouldReindex;
	public void reindex() { this.shouldReindex = true; }*/
	
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
			
			for (WorldCoord3 coord : entity.getContent())
			{
				this.contentToGate.put(coord, entity);
			}
			for (WorldCoord3 coord : entity.getFrame())
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
		for (WorldCoord3 coord : entity.getContent())
		{
			this.contentToGate.remove(coord);
		}
		for (WorldCoord3 coord : entity.getFrame())
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
	// FX SECTION
	// -------------------------------------------- //
	
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
	}
	
}
