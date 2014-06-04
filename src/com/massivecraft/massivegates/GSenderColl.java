package com.massivecraft.massivegates;

import com.massivecraft.massivecore.store.MStore;
import com.massivecraft.massivecore.store.SenderColl;

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
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean isDefault(GSender entity)
	{
		return entity.getSelectedGate() == null;
	}
	
}
