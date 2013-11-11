package com.massivecraft.massivegates;

import org.bukkit.Material;

public class Const
{
	// Collections & Aspects
	public static final String BASENAME = "massivegates";
	public static final String BASENAME_ = BASENAME+"_";
	
	public static transient String COLLECTION_SENDER = BASENAME_ + "sender";
	public static transient String COLLECTION_GATE = BASENAME_ + "gate";
	
	public static final String COLLECTION_MCONF = BASENAME_+"mconf";
	
	public static final int hourTriggingTaskTicks = 60;
	
	@SuppressWarnings("deprecation")
	public static final int visFrame = Material.JACK_O_LANTERN.getId();
	
	@SuppressWarnings("deprecation")
	public static final int visContent = Material.GLOWSTONE.getId();
	
	@SuppressWarnings("deprecation")
	public static final int visPower = Material.STATIONARY_LAVA.getId();
	
}
