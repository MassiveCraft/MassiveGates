package com.massivecraft.massivegates;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

public class Conf
{
	public static final transient int hourTriggingTaskTicks = 60;
	public static final transient int visFrame = Material.JACK_O_LANTERN.getId();
	public static final transient int visContent = Material.GLOWSTONE.getId();
	public static final transient int visPower = Material.STATIONARY_LAVA.getId();
	
	public static List<String> baseCommandAliases = new ArrayList<String>();
	public static boolean disableVanillaGates = false;
	public static int floodFillLimit = 200;
	public static int lineOfSightLimit = 100;
	
	static
	{
		baseCommandAliases.add("g");
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
