package com.massivecraft.massivegates;

import com.massivecraft.mcore5.store.MStore;
import com.massivecraft.mcore5.store.SenderColl;

public class GSenderColl extends SenderColl<GSender>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	public static GSenderColl i = new GSenderColl();
	
	public GSenderColl()
	{
		super(MStore.getDb(ConfServer.dburi), P.p, Const.senderBasename, GSender.class, DEFAULT_CREATIVE, DEFAULT_LOWERCASING, null, null);
	}

	// -------------------------------------------- //
	// EXTRAS
	// -------------------------------------------- //
	
	@Override
	public boolean isDefault(GSender entity)
	{
		return entity.getSelectedGate() == null;
	}
}
