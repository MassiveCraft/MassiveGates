package com.massivecraft.massivegates.engine;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.GateColl;
import com.massivecraft.massivegates.event.EventMassiveGatesAlter;
import com.massivecraft.massivegates.event.EventMassiveGatesAlter.GateAlterType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

import java.util.HashSet;
import java.util.Set;

public class EngineProtection extends Engine
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static EngineProtection i = new EngineProtection();
	public static EngineProtection get() { return i; }
	
	// -------------------------------------------- //
	// PROTECTION AND REACTION TO EDITS - BLOCKS
	// -------------------------------------------- //
	
	// We begin with some utility methods to be used often
	
	// The two parameters should be the same event. It's splitted for type safety only.
	// true means the event is now cancelled
	public boolean handlerNormalSingle(Block block, Cancellable event)
	{
		if (event.isCancelled()) return true;
		
		PS coord = PS.valueOf(block);
		
		Gate contentGate = GateColl.get().getGateAtContentCoord(coord);
		if (contentGate != null)
		{
			event.setCancelled(true);
			return true;
		}
		
		Gate frameGate = GateColl.get().getGateAtFrameCoord(coord);
		if (frameGate != null)
		{
			event.setCancelled(true);
			return true;
		}
				
		return false;
	}
			
	public void handlerMonitorSingle(Cancellable event, Block block, GateAlterType alterType, Player player)
	{
		if (event.isCancelled()) return;
		
		PS coord = PS.valueOf(block);
		Gate gate = GateColl.get().getGateAtCoord(coord);
		if (gate == null) return;
		
		new EventMassiveGatesAlter(gate, alterType, player).run();
	}
			
	@EventHandler(priority = EventPriority.NORMAL)
	public void normalBlockPlaceEvent(BlockPlaceEvent event)
	{
		this.handlerNormalSingle(event.getBlock(), event);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorBlockPlaceEvent(BlockPlaceEvent event)
	{
		this.handlerMonitorSingle(event, event.getBlock(), GateAlterType.PLACE, event.getPlayer());
	}
			
	@EventHandler(priority = EventPriority.NORMAL)
	public void normalBlockBreakEvent(BlockBreakEvent event)
	{
		this.handlerNormalSingle(event.getBlock(), event);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorBlockBreakEvent(BlockBreakEvent event)
	{
		this.handlerMonitorSingle(event, event.getBlock(), GateAlterType.BREAK, event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void normalBlockIgniteEvent(BlockIgniteEvent event)
	{
		this.handlerNormalSingle(event.getBlock(), event);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void normalBlockPhysicsEvent(BlockPhysicsEvent event)
	{
		this.handlerNormalSingle(event.getBlock(), event);
	}

	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorBlockIgniteEvent(BlockIgniteEvent event)
	{
		this.handlerMonitorSingle(event, event.getBlock(), GateAlterType.IGNITE, event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void normalBlockFormEvent(BlockFormEvent event)
	{
		this.handlerNormalSingle(event.getBlock(), event);
	}
			
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorBlockFormEvent(BlockFormEvent event)
	{
		this.handlerMonitorSingle(event, event.getBlock(), GateAlterType.FORM, null);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void normalBlockFadeEvent(BlockFadeEvent event)
	{
		this.handlerNormalSingle(event.getBlock(), event);
	}
			
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorBlockFadeEvent(BlockFadeEvent event)
	{
		this.handlerMonitorSingle(event, event.getBlock(), GateAlterType.FADE, null);
	}
			
	@EventHandler(priority = EventPriority.NORMAL)
 	public void normalBlockBurnEvent(BlockBurnEvent event)
 	{
		 this.handlerNormalSingle(event.getBlock(), event);
 	}
	 
 	@EventHandler(priority = EventPriority.MONITOR)
 	public void monitorBlockBurnEvent(BlockBurnEvent event)
 	{
		 this.handlerMonitorSingle(event, event.getBlock(), GateAlterType.BURN, null);
 	}
 	
 	@EventHandler(priority = EventPriority.NORMAL)
 	public void normalBlockSpreadEvent(BlockSpreadEvent event)
 	{
 		if (this.handlerNormalSingle(event.getSource(), event))
 		{
 			return;
 		}
 		this.handlerNormalSingle(event.getBlock(), event);
 	}
 	
 	@EventHandler(priority = EventPriority.MONITOR)
 	public void monitorBlockSpreadEvent(BlockSpreadEvent event)
 	{
 		this.handlerMonitorSingle(event, event.getSource(), GateAlterType.SPREAD, null);
 		this.handlerMonitorSingle(event, event.getBlock(), GateAlterType.SPREAD, null);
 	}
 	
 	@EventHandler(priority = EventPriority.NORMAL)
 	public void normalBlockPistonExtendEvent(BlockPistonExtendEvent event)
 	{
 		this.handlerNormalSingle(event.getBlock(), event);
 		for (Block block : event.getBlocks())
 		{
 			if (this.handlerNormalSingle(block, event))
 			{
 				return;
		 	}
		 }
	}
 	
 	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorBlockPistonExtendEvent(BlockPistonExtendEvent event)
 	{
 		this.handlerMonitorSingle(event, event.getBlock(), GateAlterType.PISTON_EXTEND, null);
 		for (Block block : event.getBlocks())
 		{
 			this.handlerMonitorSingle(event, block, GateAlterType.PISTON_EXTEND, null);
 		}
 	}
 	
 	@EventHandler(priority = EventPriority.NORMAL)
 	public void normalBlockPistonRetractEvent(BlockPistonRetractEvent event)
 	{
 		if (this.handlerNormalSingle(event.getBlock(), event))
 		{
 			return;
 		}
 		this.handlerNormalSingle(event.getBlock().getRelative(event.getDirection(), 2), event);
 	}
			
 	@EventHandler(priority = EventPriority.MONITOR)
 	public void monitorBlockPistonRetractEvent(BlockPistonRetractEvent event)
 	{
		this.handlerMonitorSingle(event, event.getBlock(), GateAlterType.PISTON_EXTEND, null);
		this.handlerMonitorSingle(event, event.getBlock().getRelative(event.getDirection(), 2), GateAlterType.PISTON_EXTEND, null);
	}
 	
 	// -------------------------------------------- //
 	// PROTECTION AND REACTION TO EDITS - ENTITIES
 	// -------------------------------------------- //
 	
 	@EventHandler(priority = EventPriority.NORMAL)
 	public void normalEntityExplodeEvent(EntityExplodeEvent event)
 	{
 		if (event.isCancelled()) return;
 		for (Block block : event.blockList())
 		{
 			if (this.handlerNormalSingle(block, event))
 			{
 				return;
		 	}
 		}
 	}
			
 	@EventHandler(priority = EventPriority.MONITOR)
 	public void monitorEntityExplodeEvent(EntityExplodeEvent event)
 	{
 		if (event.isCancelled()) return;
 		
 		Set<Gate> alteredGates = new HashSet<Gate>();
  
 		for (Block block : event.blockList())
 		{
 			Gate gate = GateColl.get().getGateAtCoord(PS.valueOf(block));
 			if (gate == null) continue;
 			alteredGates.add(gate);
 		}
 		
 		for (Gate alteredGate : alteredGates)
 		{
 			EventMassiveGatesAlter alterEvent = new EventMassiveGatesAlter(alteredGate, GateAlterType.EXPLODE, null);
 			alterEvent.run();
 		} 
	}
	 
 	// -------------------------------------------- //
 	// PROTECTION AND REACTION TO EDITS - PLAYERS
 	// -------------------------------------------- //
 	
 	@EventHandler(priority = EventPriority.NORMAL)
 	public void normalPlayerBucketFillEvent(PlayerBucketFillEvent event)
 	{
 		this.handlerNormalSingle(event.getBlockClicked(), event);
 	}
 	
 	@EventHandler(priority = EventPriority.MONITOR)
 	public void monitorPlayerBucketFillEvent(PlayerBucketFillEvent event)
 	{
		 this.handlerMonitorSingle(event, event.getBlockClicked(), GateAlterType.BUCKET_FILL, event.getPlayer());
 	}
 	
 	@EventHandler(priority = EventPriority.NORMAL)
 	public void normalPlayerBucketEmptyEvent(PlayerBucketEmptyEvent event)
 	{
 		this.handlerNormalSingle(event.getBlockClicked().getRelative(event.getBlockFace()), event);
 	}
 	
 	@EventHandler(priority = EventPriority.MONITOR)
 	public void monitorPlayerBucketEmptyEvent(PlayerBucketEmptyEvent event)
 	{
 		this.handlerMonitorSingle(event, event.getBlockClicked().getRelative(event.getBlockFace()), GateAlterType.BUCKET_EMPTY, event.getPlayer());
 	}
}
