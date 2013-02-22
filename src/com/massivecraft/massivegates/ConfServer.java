package com.massivecraft.massivegates;

import java.util.List;

import com.massivecraft.mcore.SimpleConfig;
import com.massivecraft.mcore.util.MUtil;

public class ConfServer extends SimpleConfig
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public static String dburi = "default";
	
	public static List<String> baseCommandAliases = MUtil.list("g");
	public static boolean disableVanillaGates = false;
	public static int floodFillLimit = 200;
	public static int lineOfSightLimit = 100;
	
	// -------------------------------------------- //
	// Persistence
	// -------------------------------------------- //
	public static transient ConfServer i = new ConfServer();
	public ConfServer() { super(P.p); }
}
