package com.massivecraft.massivegates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;

import com.massivecraft.massivegates.event.GateAlterType;

public class Conf
{
	public static final transient int lookupMapRebuildTicksDelay = 20;
	public static final transient int visFrame = Material.JACK_O_LANTERN.getId();
	public static final transient int visContent = Material.GLOWSTONE.getId();
	
	public static List<String> baseCommandAliases = new ArrayList<String>();
	public static boolean disableVanillaGates = false;
	public static int floodFillLimit = 200;
	public static int lineOfSightLimit = 100;
	public static Map<GateAlterType, Boolean> canContent = new HashMap<GateAlterType, Boolean>();
	public static Map<GateAlterType, Boolean> canFrame = new HashMap<GateAlterType, Boolean>();
	
	static
	{
		baseCommandAliases.add("g");
		
		canContent.put(GateAlterType.PLACE, false);
		canContent.put(GateAlterType.BREAK, false);
		canContent.put(GateAlterType.BUCKET_FILL, false);
		canContent.put(GateAlterType.BUCKET_EMPTY, false);
		canContent.put(GateAlterType.IGNITE, false);
		canContent.put(GateAlterType.PHYSICS, false);
		canContent.put(GateAlterType.FLOW, false);
		canContent.put(GateAlterType.FORM, false);
		canContent.put(GateAlterType.FADE, false);
		canContent.put(GateAlterType.BURN, false);
		canContent.put(GateAlterType.SPREAD, false);
		canContent.put(GateAlterType.PISTON_EXTEND, false);
		canContent.put(GateAlterType.PISTON_RETRACT, false);
		canContent.put(GateAlterType.EXPLODE, false);
		
		canFrame.put(GateAlterType.PLACE, true);
		canFrame.put(GateAlterType.BREAK, true);
		canFrame.put(GateAlterType.BUCKET_FILL, true);
		canFrame.put(GateAlterType.BUCKET_EMPTY, true);
		canFrame.put(GateAlterType.IGNITE, true);
		canFrame.put(GateAlterType.PHYSICS, true);
		canFrame.put(GateAlterType.FLOW, true);
		canFrame.put(GateAlterType.FORM, true);
		canFrame.put(GateAlterType.FADE, true);
		canFrame.put(GateAlterType.BURN, true);
		canFrame.put(GateAlterType.SPREAD, true);
		canFrame.put(GateAlterType.PISTON_EXTEND, true);
		canFrame.put(GateAlterType.PISTON_RETRACT, false);
		canFrame.put(GateAlterType.EXPLODE, true);
	}
	
	// -------------------------------------------- //
	// Persistance
	// -------------------------------------------- //
	private static transient Conf i = new Conf();
	public static void load()
	{
		P.p.one.loadOrSaveDefault(i, Conf.class);
	}
	public static void save()
	{
		P.p.one.save(i);
	}
}
