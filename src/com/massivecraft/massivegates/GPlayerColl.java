package com.massivecraft.massivegates;

import com.massivecraft.mcore5.store.MStore;
import com.massivecraft.mcore5.store.PlayerColl;

public class GPlayerColl extends PlayerColl<GPlayer>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	public static GPlayerColl i = new GPlayerColl();
	
	private GPlayerColl()
	{
		super(MStore.getDb(ConfServer.dburi), P.p, Const.playerBasename, GPlayer.class);
	}

	// -------------------------------------------- //
	// EXTRAS
	// -------------------------------------------- //
	
	@Override
	public boolean isDefault(GPlayer entity)
	{
		return entity.getSelectedGate() == null;
	}
}
