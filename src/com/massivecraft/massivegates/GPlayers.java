package com.massivecraft.massivegates;

import java.io.File;

import com.massivecraft.mcore3.persist.gson.GsonPlayerEntityManager;

public class GPlayers extends GsonPlayerEntityManager<GPlayer>
{
	@Override public Class<GPlayer> getManagedClass() { return GPlayer.class; }
	public static GPlayers i = new GPlayers();
	private GPlayers()
	{
		super(P.p.gson, new File(P.p.getDataFolder(), "player"), true, true);
		P.p.persist.setManager(GPlayer.class, this);
		P.p.persist.setSaveInterval(GPlayer.class, 1000*60*30);
	}

	@Override
	public boolean shouldBeSaved(GPlayer entity)
	{
		return false;
	}
}
