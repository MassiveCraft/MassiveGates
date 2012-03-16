package com.massivecraft.massivegates.util;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.WorldCoord3;
import com.massivecraft.mcore2.util.SmokeUtil;
import com.massivecraft.mcore2.util.Txt;

public enum Fx
{	
	
	POTION_BREAK(true, true, true, Effect.POTION_BREAK, "Potion break sound and particles", "potion", "potionbreak"),
    STEP_SOUND(true, true, true, Effect.STEP_SOUND, "Step sound and particles", "step", "stepsound"),
	TNT(true, true, false, null, "Non-damaging TNT explosion", "tnt", "tntexplosion", "explostion"), // SELFMADE
	STRIKE(true, true, false, null, "Non-damaging lightning strike", "strike", "lightningstrike"), // SELFMADE
	SMOKE(true, false, false, null, "A smoke cloud", "smoke", "smokes"), // SELFMADE
	ENDER_SIGNAL(true, false, false, Effect.ENDER_SIGNAL, "Ender signal particles", "ender", "endersignal", "nethersignal", "neathersignal", "netherparticles", "neatherparticles"),
    MOBSPAWNER_FLAMES(true, false, false, Effect.MOBSPAWNER_FLAMES, "Mobspawner flame particles", "fire", "flames", "mobspawnerflames", "mobspawnerfire", "mobflames", "mobfire", "spawnerflames", "spawnerfire"),
    BOW(false, true, false, Effect.BOW_FIRE, "Fire bow sound", "bow", "bowfire"),
    CLICK1(false, true, false, Effect.CLICK1, "\"tick\" sound", "tick", "click"),
    CLICK2(false, true, false, Effect.CLICK2, "\"tock\" sound", "tock"),
    DOOR_TOGGLE(false, true, false, Effect.DOOR_TOGGLE, "Toggle door sound", "door", "doortoggle"),
    EXTINGUISH(false, true, false, Effect.EXTINGUISH, "Extinguish fire sound", "ext", "extinguish"),
    GHAST_SHRIEK(false, true, false, Effect.GHAST_SHRIEK, "Screaming ghast sound", "scream", "ghastshriek", "shriek", "schriek", "screem"),
    GHAST_SHOOT(false, true, false, Effect.GHAST_SHOOT, "Shoot fireball sound", "shoot", "ghastshoot"),
    BLAZE_SHOOT(false, true, false, Effect.BLAZE_SHOOT, "Blaze shoot sound", "blazeshoot"),
    RECORD_PLAY(false, true, true, Effect.RECORD_PLAY, "Play record sound", "record", "recordplay"),
	;
	
	protected final boolean hasVisual;
	public boolean getHasVisual() { return this.hasVisual; }
	
	protected final boolean hasSound;
	public boolean getHasSound() { return this.hasSound; }
	
	protected final boolean takesData;
	public boolean getTakesData() { return this.takesData; }
	
	protected final Effect bukkitEffect;
	
	protected final String desc;
	public String getDesc() { return this.desc; }
	
	protected final String[] aliases;
	public String[] getAliases() { return this.aliases; }
	public String getName() { return this.aliases[0]; }
	
	private Fx(boolean hasVisual, boolean hasSound, boolean takesData, Effect bukkitEffect, String desc, String... aliases)
	{
		this.hasVisual = hasVisual;
		this.hasSound = hasSound;
		this.takesData = takesData;
		this.bukkitEffect = bukkitEffect;
		this.desc = desc;
		this.aliases = aliases;
	}
	
	@Override public String toString() { return this.aliases[0]; }
	
	// -------------------------------------------- //
	// MATCH AND PARSE
	// -------------------------------------------- //
	
	public static List<Fx> match(String str)
	{
		List<Fx> ret = new ArrayList<Fx>();
		str = str.toLowerCase().replace("_", "").replace("-", "");
		for (Fx fx : Fx.values())
		{
			for (String alias : fx.aliases)
			{
				if (alias.startsWith(str))
				{
					ret.add(fx);
					break;
				}
			}
		}
		return ret;
	}
	
	public static String parseSingleError = null;
	public static Entry<Fx, String> parseSingleFxString(String singleFxString)
	{
		// Reset the error string
		parseSingleError = null;
				
		// Basic checks
		if (singleFxString == null)
		{
			parseSingleError = "<b>NPE!";
			return null;
		}
		if (singleFxString.length() == 0)
		{
			parseSingleError = "<b>No empty fx entries please.";
			return null;
		}
		
		// Step one is to separate the fxAlias from the dataString.
		String fxAlias = null;
		String dataString = null;
		Integer index = Txt.indexOfFirstDigit(singleFxString);
		if (index == null)
		{
			fxAlias = singleFxString;
		}
		else
		{
			fxAlias = singleFxString.substring(0, index);
			dataString = singleFxString.substring(index, singleFxString.length());
		}
		
		// Next we match the alias
		List<Fx> matches = match(fxAlias);
		if (matches.size() == 0)
		{
			parseSingleError = "<b>\"<h>"+fxAlias+"<b>\" does not match any FX.";
			return null;
		}
		else if (matches.size() > 1)
		{
			parseSingleError = "<b>\"<h>"+fxAlias+"<b>\" could match any of these FX: "+Txt.implodeCommaAndDot(matches, "<b>");
			return null;
		}
		Fx fx = matches.get(0);
		
		return new SimpleEntry<Fx, String>(fx, dataString);
	}
	
	public static List<String> parseMultiErrors = new ArrayList<String>();
	public static List<Entry<Fx, String>> parseMultiFxString(String multiFxString)
	{
		// Clear the errors
		parseMultiErrors.clear();
		
		List<Entry<Fx, String>> ret = new ArrayList<Entry<Fx, String>>();
		
		String[] singleFxStrings = multiFxString.split(",\\s*");
		for (String singleFxString : singleFxStrings)
		{
			Entry<Fx, String> entry = parseSingleFxString(singleFxString);
			if (parseSingleError == null)
			{
				ret.add(entry);
			}
			else
			{
				parseMultiErrors.add(parseSingleError);
			}
		}
		
		return ret;
	}
	
	// -------------------------------------------- //
	// PERFORM CORE
	// -------------------------------------------- //
	
	public static void perform(Fx fx, String dataString, Collection<Location> locations)
	{
		if (fx == TNT)
		{
			for (Location location : locations)
			{
				if (location == null) continue;
				SmokeUtil.fakeExplosion(location);
			}
			return;
		}
		else if (fx == STRIKE)
		{
			for (Location location : locations)
			{
				if (location == null) continue;
				location.getWorld().strikeLightningEffect(location);
			}
			return;
		}
		else if (fx == SMOKE)
		{
			SmokeUtil.spawnCloudSimple(locations);
			return;
		}
		
		// Decide the data
		Integer data = 0;
		try
		{
			data = Integer.parseInt(dataString);
		}
		catch (Exception e){}
		
		for (Location location : locations)
		{
			if (location == null) continue;
			location.getWorld().playEffect(location, fx.bukkitEffect, data);
		}
	}
	
	// -------------------------------------------- //
	// PERFORM SINGLE PARSED
	// -------------------------------------------- //
	
	public static void perform(Fx fx, String dataString, Entity entity)
	{
		Collection<Location> locations;
		if (! fx.hasSound && (entity instanceof HumanEntity))
		{
			locations = Arrays.asList(entity.getLocation(), entity.getLocation().getBlock().getRelative(BlockFace.UP).getLocation());
		}
		else
		{
			locations = Arrays.asList(entity.getLocation());
		}
		perform(fx, dataString, locations);
	}
	
	public static void perform(Fx fx, String dataString, Gate gate)
	{
		Collection<Location> locations;
		if (fx.hasSound)
		{
			locations = Arrays.asList(gate.calcGateCenter());
		}
		else
		{
			locations = new ArrayList<Location>(gate.getContent().size());
			for (WorldCoord3 coord : gate.getContent())
			{
				locations.add(coord.getLocation());
			}
		}
		perform(fx, dataString, locations);
	}
	
	public static void perform (Fx fx, String dataString, Location location)
	{
		perform(fx, dataString, Arrays.asList(location));
	}
	
	// -------------------------------------------- //
	// PERFORM MULTI NON-PARSED
	// -------------------------------------------- //
	
	public static boolean perform(String multiFxString, Entity entity)
	{
		List<Entry<Fx, String>> fxDatas = Fx.parseMultiFxString(multiFxString);
		if (Fx.parseMultiErrors.size() != 0) return false;
		for (Entry<Fx, String> fxData : fxDatas)
		{
			Fx.perform(fxData.getKey(), fxData.getValue(), entity);
		}
		return true;
	}
	
	public static boolean perform(String multiFxString, Gate gate)
	{
		List<Entry<Fx, String>> fxDatas = Fx.parseMultiFxString(multiFxString);
		if (Fx.parseMultiErrors.size() != 0) return false;
		for (Entry<Fx, String> fxData : fxDatas)
		{
			Fx.perform(fxData.getKey(), fxData.getValue(), gate);
		}
		return true;
	}
	
	public static boolean perform(String multiFxString, Location location)
	{
		List<Entry<Fx, String>> fxDatas = Fx.parseMultiFxString(multiFxString);
		if (Fx.parseMultiErrors.size() != 0) return false;
		for (Entry<Fx, String> fxData : fxDatas)
		{
			Fx.perform(fxData.getKey(), fxData.getValue(), location);
		}
		return true;
	}
	
}
