package com.massivecraft.massivegates;

import com.massivecraft.mcore.store.MStore;
import com.massivecraft.mcore.store.SenderColl;

public class GSenderColl extends SenderColl<GSender>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	public static GSenderColl i = new GSenderColl();
	
	public GSenderColl()
	{
		super(Const.COLLECTION_SENDER, GSender.class, MStore.getDb(), P.p);
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
