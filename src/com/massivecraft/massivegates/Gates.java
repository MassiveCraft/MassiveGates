package com.massivecraft.massivegates;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.massivecraft.mcore1.persist.gson.GsonClassManager;

public class Gates extends GsonClassManager<Gate>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	public static Gates i = new Gates();
	
	private Gates()
	{
		super(P.p.gson, new File(P.p.getDataFolder(), "gate"), false, false);
		P.p.persist.setManager(Gate.class, this);
		P.p.persist.setSaveInterval(Gate.class, 1000*60*30);
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
	// LOOKUP MAPS
	// -------------------------------------------- //
	
	// FIELD: contentCoordToGate
	private Map<WorldCoord3, Gate> contentCoordToGate = new HashMap<WorldCoord3, Gate>();
	public Map<WorldCoord3, Gate> getContentCoordToGate() { return this.contentCoordToGate; }
	public Gate getGateAtContentCoord(WorldCoord3 coord) { return this.contentCoordToGate.get(coord); }
	public void rebuildContentCoordToGate()
	{
		this.contentCoordToGate.clear();
		for (Gate gate : this.getAll())
		{
			for (WorldCoord3 coord : gate.getContent())
			{
				this.contentCoordToGate.put(coord, gate);
			}
		}
	}
	
	// FIELD: frameCoordToGate
	private Map<WorldCoord3, Gate> frameCoordToGate = new HashMap<WorldCoord3, Gate>();
	public Map<WorldCoord3, Gate> getFrameCoordToGate() { return this.frameCoordToGate; }
	public Gate getGateAtFrameCoord(WorldCoord3 coord) { return this.frameCoordToGate.get(coord); }
	public void rebuildFrameCoordToGate()
	{
		this.frameCoordToGate.clear();
		for (Gate gate : this.getAll())
		{
			for (WorldCoord3 coord : gate.getFrame())
			{
				this.frameCoordToGate.put(coord, gate);
			}
		}
	}
	
	public Gate getGateAtCoord(WorldCoord3 coord)
	{
		Gate gate = this.getGateAtFrameCoord(coord);
		if (gate != null) return gate;
		return this.getGateAtContentCoord(coord);
	}
	
	// Rebuild both of the maps
	public void rebuildCoordToGate()
	{
		this.rebuildContentCoordToGate();
		this.rebuildFrameCoordToGate();
	}
	
}
