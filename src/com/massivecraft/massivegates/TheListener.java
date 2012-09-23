package com.massivecraft.massivegates;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;

import com.massivecraft.massivegates.event.GateAfterTeleportEvent;
import com.massivecraft.massivegates.event.GateAlterEvent;
import com.massivecraft.massivegates.event.GateBeforeTeleportEvent;
import com.massivecraft.massivegates.event.GateOpenChangeEvent;
import com.massivecraft.massivegates.event.GatePlayerWalkEvent;
import com.massivecraft.massivegates.event.GatePowerChangeEvent;
import com.massivecraft.massivegates.event.GateUseEvent;
import com.massivecraft.massivegates.event.GateAlterEvent.GateAlterType;
import com.massivecraft.massivegates.event.GatePlayerWalkEvent.GatePlayerWalkType;
import com.massivecraft.massivegates.ta.TriggerAtp;
import com.massivecraft.massivegates.ta.TriggerBtp;
import com.massivecraft.massivegates.ta.TriggerClose;
import com.massivecraft.massivegates.ta.TriggerEnter;
import com.massivecraft.massivegates.ta.TriggerOpen;
import com.massivecraft.massivegates.ta.TriggerPowerOff;
import com.massivecraft.massivegates.ta.TriggerPowerOn;
import com.massivecraft.massivegates.ta.TriggerUse;
import com.massivecraft.massivegates.util.VisualizeUtil;


public class TheListener implements Listener
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	public P p;
	public TheListener(P p)
	{
		this.p = p;
	}
	
	// -------------------------------------------- //
	// DISABLE VANILLA GATES CONFIG OPTION
	// -------------------------------------------- //
			
	@EventHandler(priority = EventPriority.NORMAL)
	public void normalPlayerPortalEvent(PlayerPortalEvent event)
	{
		if (ConfServer.disableVanillaGates)
		{
			event.setCancelled(false);
		}
	}
	
	// -------------------------------------------- //
	// REDSTONE
	// -------------------------------------------- //
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorBlockPhysicsEvent(BlockPhysicsEvent event)
	{
		Block block = event.getBlock();
		WorldCoord3 coord = new WorldCoord3(block); 
		
		Gate gate = Gates.i.getGateAtFrameCoord(coord);
		if (gate == null) return;
		
		if (block.isBlockIndirectlyPowered())
		{
			gate.powerAdd(coord);
		}
		else
		{
			gate.powerRemove(coord);
		}
	}
	
	// -------------------------------------------- //
	// PLAYER MOVE
	// -------------------------------------------- //
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerMove(PlayerMoveEvent event)
	{
		if (event.isCancelled()) return;
		
		Block blockFrom = event.getFrom().getBlock();
		Block blockTo = event.getTo().getBlock();
		
		if (blockFrom.equals(blockTo)) return;
		
		Player player = event.getPlayer();
		
		VisualizeUtil.clear(player);
		
		WorldCoord3 coordFrom = new WorldCoord3(blockFrom);
		WorldCoord3 coordTo = new WorldCoord3(blockTo);
		
		Gate gateFrom = Gates.i.getGateAtContentCoord(coordFrom);
		Gate gateTo = Gates.i.getGateAtContentCoord(coordTo);
		
		GatePlayerWalkEvent gateEvent;
		
		if (gateFrom != null & gateFrom == gateTo)
		{
			gateEvent = new GatePlayerWalkEvent(player, gateFrom, gateTo, GatePlayerWalkType.WITHIN);
			gateEvent.run();
			if (gateEvent.isCancelled())
			{
				event.setCancelled(true);
			}
			
			return;
		}
		
		if (gateFrom != null)
		{
			// OUT
			gateEvent = new GatePlayerWalkEvent(player, gateFrom, gateTo, GatePlayerWalkType.OUT);
			gateEvent.run();
			if (gateEvent.isCancelled())
			{
				event.setCancelled(true);
			}
		}
		
		if (gateTo != null)
		{
			// INTO
			gateEvent = new GatePlayerWalkEvent(player, gateFrom, gateTo, GatePlayerWalkType.INTO);
			gateEvent.run();
			if (gateEvent.isCancelled())
			{
				event.setCancelled(true);
			}
			/*else if (gateTo.isOpen())
			{
				// Trigger usage of the gate :)
				gateTo.use(event.getPlayer());
			}*/
		}
	}
	
	// -------------------------------------------- //
	// LISTEN TO OUR OWN GATE EVENTS
	// -------------------------------------------- //
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void normalGateUseEvent(GateUseEvent event)
	{
		event.getGate().trigger(TriggerUse.getInstance(), event.getUser(), event);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorGateBeforeTeleportEvent(GateBeforeTeleportEvent event)
	{
		event.getGate().trigger(TriggerBtp.getInstance(), event.getUser(), null);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorGateAfterTeleportEvent(GateAfterTeleportEvent event)
	{
		event.getGate().trigger(TriggerAtp.getInstance(), event.getUser(), null);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorGateOpenChangeEvent(GateOpenChangeEvent event)
	{
		// TODO: Improve with user and cause ???
		if (event.getGate().isOpen())
		{
			event.getGate().trigger(TriggerOpen.getInstance(), null, null);
		}
		else
		{
			event.getGate().trigger(TriggerClose.getInstance(), null, null);
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void normalGatePlayerWalkEvent(GatePlayerWalkEvent event)
	{
		if (event.getWalkType() == GatePlayerWalkType.INTO)
		{
			event.getGateTo().trigger(TriggerEnter.getInstance(), event.getPlayer(), event);
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorGatePowerChangeEvent(GatePowerChangeEvent event)
	{
		if (event.powerHas())
		{
			event.getGate().trigger(TriggerPowerOn.getInstance(), null, null);
		}
		else
		{
			event.getGate().trigger(TriggerPowerOff.getInstance(), null, null);
		}
	}
	
	// -------------------------------------------- //
	// PROTECTION AND REACTION TO EDITS - BLOCKS
	// -------------------------------------------- //
	
	// We begin with some utility methods to be used often
	
	// The two parameters should be the same event. It's splitted for type safety only.
	// true means the event is now cancelled
	public boolean handlerNormalSingle(Block block, Cancellable event)
	{
		if (event.isCancelled()) return true;
		
		WorldCoord3 coord = new WorldCoord3(block);
		
		Gate contentGate = Gates.i.getGateAtContentCoord(coord);
		if (contentGate != null && ! contentGate.isContentEditable())
		{
			event.setCancelled(true);
			return true;
		}
		
		Gate frameGate = Gates.i.getGateAtFrameCoord(coord);
		if (frameGate != null && ! frameGate.isFrameEditable())
		{
			event.setCancelled(true);
			return true;
		}
		
		return false;
	}
	
	public void handlerMonitorSingle(Cancellable event, Block block, GateAlterType alterType, Player player)
	{
		if (event.isCancelled()) return;
		WorldCoord3 coord = new WorldCoord3(block);
		Gate gate = Gates.i.getGateAtCoord(coord);
		if (gate == null) return;
		new GateAlterEvent(gate, alterType, player).run();
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
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorBlockIgniteEvent(BlockIgniteEvent event)
	{
		this.handlerMonitorSingle(event, event.getBlock(), GateAlterType.IGNITE, event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void normalBlockPhysicsEvent(BlockPhysicsEvent event)
	{
		this.handlerNormalSingle(event.getBlock(), event);
	}
	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockPhysics(BlockPhysicsEvent event)
	{
		//this.handlerMonitorSingle(event, event.getBlock(), GateAlterType.PHYSICS, null);
		// WILL TRIGGER FAR TO OFTEN ???
	}
	
	/*@EventHandler(priority = EventPriority.NORMAL)
	public void normalBlockFromToEvent(BlockFromToEvent event)
	{
		if (this.handlerNormalSingle(event.getBlock(), event))
		{
			return;
		}
		this.handlerNormalSingle(event.getToBlock(), event);
	}
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorBlockFromToEvent(BlockFromToEvent event)
	{
		this.handlerMonitorSingle(event, event.getBlock(), GateAlterType.FLOW, null);
		this.handlerMonitorSingle(event, event.getToBlock(), GateAlterType.FLOW, null);
	}*/
	
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
		this.handlerNormalSingle(event.getRetractLocation().getBlock(), event);
	}
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorBlockPistonRetractEvent(BlockPistonRetractEvent event)
	{
		this.handlerMonitorSingle(event, event.getBlock(), GateAlterType.PISTON_EXTEND, null);
		this.handlerMonitorSingle(event, event.getRetractLocation().getBlock(), GateAlterType.PISTON_EXTEND, null);
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
			Gate gate = Gates.i.getGateAtCoord(new WorldCoord3(block));
			if (gate == null) continue;
			alteredGates.add(gate);
		}
		
		for (Gate alteredGate : alteredGates)
		{
			GateAlterEvent alterEvent = new GateAlterEvent(alteredGate, GateAlterType.EXPLODE, null);
			Bukkit.getPluginManager().callEvent(alterEvent);
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
