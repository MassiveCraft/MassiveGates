package com.massivecraft.massivegates;

import java.util.List;

import com.massivecraft.mcore4.SimpleConfig;
import com.massivecraft.mcore4.util.MUtil;

public class ConfServer extends SimpleConfig
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public static List<String> baseCommandAliases = MUtil.list("g");
	public static boolean disableVanillaGates = false;
	public static int floodFillLimit = 200;
	public static int lineOfSightLimit = 100;
	
	// -------------------------------------------- //
	// Persistance
	// -------------------------------------------- //
	public static transient ConfServer i = new ConfServer();
	private ConfServer()
	{
		super(P.p);
	}
}
