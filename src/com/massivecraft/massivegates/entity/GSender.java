package com.massivecraft.massivegates.entity;

import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivecore.store.SenderEntity;
import com.massivecraft.massivegates.util.FloodOrientation;
import com.massivecraft.massivegates.util.FloodUtil;

public class GSender extends SenderEntity<GSender>
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	protected String selectedGateId = null;
	
	public Gate getSelectedGate()
	{
		return this.selectedGateId == null ? null : GateColl.get().get(this.selectedGateId);
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
		Player player = this.getPlayer();
		if (player == null) return null;
		
		Block block = null;
		
		Iterator<Block> itr = new BlockIterator(player, MConf.get().lineOfSightLimit);
		while (itr.hasNext())
		{
			block = itr.next();
			if (block.getType() != Material.AIR)
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
		Player player = this.getPlayer();
		if (player == null) return null;
		
		Gate ret = null;
		
		Iterator<Block> itr = new BlockIterator(player, MConf.get().lineOfSightLimit);
		while (itr.hasNext())
		{
			PS coord = PS.valueOf(itr.next());
			
			System.out.println("PS coord = " + coord.toString());
			System.out.println("GateColl.get().getContentToGate().size() = " + GateColl.get().getContentToGate().size());
			
			ret = GateColl.get().getGateAtCoord(coord);
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
		Player player = this.getPlayer();
		if (player == null) return null;
		
		Block startBlock = player.getLocation().getBlock();
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
