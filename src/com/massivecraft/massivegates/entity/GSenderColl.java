package com.massivecraft.massivegates.entity;

import com.massivecraft.massivecore.store.MStore;
import com.massivecraft.massivecore.store.SenderColl;
import com.massivecraft.massivegates.Const;
import com.massivecraft.massivegates.MassiveGates;

public class GSenderColl extends SenderColl<GSender>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static GSenderColl i = new GSenderColl();
	public static GSenderColl get() { return i; }
	private GSenderColl()
	{
		super(Const.COLLECTION_SENDER, GSender.class, MStore.getDb(), MassiveGates.get());
	}

	// -------------------------------------------- //
	// STACK TRACEABILITY
	// -------------------------------------------- //
	
	@Override
	public void onTick()
	{
		super.onTick();
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
