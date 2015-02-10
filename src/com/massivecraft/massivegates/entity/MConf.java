package com.massivecraft.massivegates.entity;

import java.util.List;

import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;

public class MConf extends Entity<MConf>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	protected static transient MConf i;
	public static MConf get() { return i; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public List<String> aliasesG = MUtil.list("g");
	
	public boolean disableVanillaGates = false;
	
	public int floodFillLimit = 200;
	
	public int lineOfSightLimit = 100;

}
