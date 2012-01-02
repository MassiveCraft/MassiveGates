package com.massivecraft.massivegates;

import com.massivecraft.mcore1.persist.IClassManager;
import com.massivecraft.mcore1.persist.PlayerEntity;
import com.massivecraft.mcore1.util.Txt;

/**
 * The VPlayer is a "skin" for a normal player.
 * Through this skin we can reach the player plus extra plugin specific data and functionality.
 */
public class GPlayer extends PlayerEntity<GPlayer>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	@Override public IClassManager<GPlayer> getManager() { return GPlayers.i; }
	@Override protected GPlayer getThis() { return this; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// FIELD: selectedGateId
	private String selectedGateId = null;
	public Gate getSelectedGate()
	{
		return this.selectedGateId == null ? null : Gates.i.get(this.selectedGateId);
	}
	public void setSelectedGate(Gate val)
	{
		this.selectedGateId = (val == null ? null : val.getId());
	}
	
	public void msg(String str, Object... args)
	{
		this.sendMessage(Txt.parse(str, args));
	}
	
}
