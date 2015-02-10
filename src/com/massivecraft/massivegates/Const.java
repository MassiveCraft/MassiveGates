package com.massivecraft.massivegates;

import org.bukkit.Material;

public class Const
{
	// -------------------------------------------- //
	// COLLECTIONS
	// -------------------------------------------- //

	public static final String BASENAME = "massivegates";
	public static final String BASENAME_ = BASENAME+"_";
	
	public static transient String COLLECTION_SENDER = BASENAME_ + "sender";
	public static transient String COLLECTION_GATE = BASENAME_ + "gate";
	public static final String COLLECTION_MCONF = BASENAME_+"mconf";
	
	// -------------------------------------------- //
	// VISIBLES & TASKTICKS
	// -------------------------------------------- //
	
	public static final int hourTriggingTaskTicks = 60;
	
	public static final Material visFrame = Material.JACK_O_LANTERN;
	public static final Material visContent = Material.GLOWSTONE;
	public static final Material visPower = Material.STATIONARY_LAVA;
	
}
