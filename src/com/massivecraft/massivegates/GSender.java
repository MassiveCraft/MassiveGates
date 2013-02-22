package com.massivecraft.massivegates;

import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

import com.massivecraft.massivegates.util.FloodOrientation;
import com.massivecraft.massivegates.util.FloodUtil;
import com.massivecraft.mcore.PS;
import com.massivecraft.mcore.store.SenderEntity;

/**
 * The VPlayer is a "skin" for a normal player.
 * Through this skin we can reach the player plus extra plugin specific data and functionality.
 */
public class GSender extends SenderEntity<GSender>
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// FIELD: selectedGateId
	protected String selectedGateId = null;
	public Gate getSelectedGate()
	{
		return this.selectedGateId == null ? null : GateColl.i.get(this.selectedGateId);
	}
	
	public void setSelectedGate(Gate val)
	{
		this.selectedGateId = (val == null ? null : val.getId());
	}
	
	// -------------------------------------------- //
	// UTILS
	// -------------------------------------------- //
	
	public Block getThatBlock(boolean verboose)
	{
		Player me = this.getPlayer();
		if (me == null) return null;
		
		Block block = null;
		
		Iterator<Block> itr = new BlockIterator(me, ConfServer.lineOfSightLimit);
		while (itr.hasNext())
		{
			block = itr.next();
			if (block.getTypeId() != 0)
			{
				return block;
			}
		}
		
		if (verboose)
		{
			this.msg("<b>No block in sight.");
		}
		
		return null;
	}
	
	public Gate getThatGate(boolean verboose)
	{
		Player me = this.getPlayer();
		if (me == null) return null;
		
		Gate ret = null;
		
		PS coord = new PS();
		Iterator<Block> itr = new BlockIterator(me, ConfServer.lineOfSightLimit);
		while (itr.hasNext())
		{
			coord.setBlock(itr.next());
			ret = GateColl.i.getGateAtCoord(coord);
			if (ret != null) break;
		}
		
		if (verboose && ret == null)
		{
			this.msg("<b>No gate in sight.");
		}
		
		return ret;
	}
	
	public Entry<FloodOrientation, Set<Block>> getBestFloodHere(boolean verboose)
	{
		Player me = this.getPlayer();
		if (me == null) return null;
		
		Block startBlock = me.getLocation().getBlock();
		if (startBlock.getType() != Material.AIR)
		{
			startBlock = startBlock.getRelative(BlockFace.UP);
		}
		
		Entry<FloodOrientation, Set<Block>> ret = FloodUtil.getBestFlood(startBlock);
		
		if (ret == null && verboose)
		{
			this.msg("<b>No frame found. It might be broken or to large.");
		}
		
		return ret;
	}
	
}
