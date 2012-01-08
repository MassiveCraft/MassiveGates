package com.massivecraft.massivegates;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;

import com.massivecraft.massivegates.adapter.LocWrapAdapter;
import com.massivecraft.massivegates.adapter.WorldCoord3Adapter;
import com.massivecraft.massivegates.cmd.CmdGate;
import com.massivecraft.massivegates.cmdarg.AHAction;
import com.massivecraft.massivegates.cmdarg.AHEffect;
import com.massivecraft.massivegates.cmdarg.AHGate;
import com.massivecraft.massivegates.cmdarg.AHTrigger;
import com.massivecraft.massivegates.event.GateAlterType;
import com.massivecraft.massivegates.privatelistener.PluginBlockListener;
import com.massivecraft.massivegates.privatelistener.PluginGateListener;
import com.massivecraft.massivegates.privatelistener.PluginPlayerListener;
import com.massivecraft.massivegates.privatelistener.PluginPlayerListenerVis;
import com.massivecraft.massivegates.privatelistener.alterimpl.GateAlterCancelContentBlockListener;
import com.massivecraft.massivegates.privatelistener.alterimpl.GateAlterCancelContentEntityListener;
import com.massivecraft.massivegates.privatelistener.alterimpl.GateAlterCancelContentPlayerListener;
import com.massivecraft.massivegates.privatelistener.alterimpl.GateAlterCancelFrameBlockListener;
import com.massivecraft.massivegates.privatelistener.alterimpl.GateAlterCancelFrameEntityListener;
import com.massivecraft.massivegates.privatelistener.alterimpl.GateAlterCancelFramePlayerListener;
import com.massivecraft.massivegates.privatelistener.alterimpl.GateAlterMonitorContentBlockListener;
import com.massivecraft.massivegates.privatelistener.alterimpl.GateAlterMonitorContentEntityListener;
import com.massivecraft.massivegates.privatelistener.alterimpl.GateAlterMonitorContentPlayerListener;
import com.massivecraft.massivegates.privatelistener.alterimpl.GateAlterMonitorFrameBlockListener;
import com.massivecraft.massivegates.privatelistener.alterimpl.GateAlterMonitorFrameEntityListener;
import com.massivecraft.massivegates.privatelistener.alterimpl.GateAlterMonitorFramePlayerListener;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.ActionClose;
import com.massivecraft.massivegates.ta.ActionFxe;
import com.massivecraft.massivegates.ta.ActionFxg;
import com.massivecraft.massivegates.ta.ActionMsg;
import com.massivecraft.massivegates.ta.ActionOpen;
import com.massivecraft.massivegates.ta.ActionUse;
import com.massivecraft.massivegates.ta.ActionUseForced;
import com.massivecraft.massivegates.ta.Trigger;
import com.massivecraft.massivegates.ta.TriggerAtp;
import com.massivecraft.massivegates.ta.TriggerBtp;
import com.massivecraft.massivegates.ta.TriggerClose;
import com.massivecraft.massivegates.ta.TriggerEnter;
import com.massivecraft.massivegates.ta.TriggerFrameAlter;
import com.massivecraft.massivegates.ta.TriggerHour;
import com.massivecraft.massivegates.ta.TriggerOpen;
import com.massivecraft.massivegates.ta.TriggerPowerOff;
import com.massivecraft.massivegates.ta.TriggerPowerOn;
import com.massivecraft.massivegates.ta.TriggerUse;
import com.massivecraft.mcore1.MPlugin;
import com.massivecraft.mcore1.lib.gson.GsonBuilder;

public class P extends MPlugin
{
	// Our single plugin instance
	public static P p;
	
	// GateAlter Listeners
	public GateAlterCancelContentBlockListener gateAlterCancelContentBlockListener;
	public GateAlterCancelContentEntityListener gateAlterCancelContentEntityListener;
	public GateAlterCancelContentPlayerListener gateAlterCancelContentPlayerListener;
	public GateAlterCancelFrameBlockListener gateAlterCancelFrameBlockListener;
	public GateAlterCancelFrameEntityListener gateAlterCancelFrameEntityListener;
	public GateAlterCancelFramePlayerListener gateAlterCancelFramePlayerListener;
	public GateAlterMonitorContentBlockListener gateAlterMonitorContentBlockListener;
	public GateAlterMonitorContentEntityListener gateAlterMonitorContentEntityListener;
	public GateAlterMonitorContentPlayerListener gateAlterMonitorContentPlayerListener;
	public GateAlterMonitorFrameBlockListener gateAlterMonitorFrameBlockListener;
	public GateAlterMonitorFrameEntityListener gateAlterMonitorFrameEntityListener;
	public GateAlterMonitorFramePlayerListener gateAlterMonitorFramePlayerListener;
	
	// Other Listeners
	public PluginPlayerListener playerListener;
	public PluginGateListener gateListener;
	public PluginPlayerListenerVis playerListenerVis;
	public PluginBlockListener blockListener; 
	
	// Command
	public CmdGate cmdGate;
	
	public P()
	{
		P.p = this;
		
		this.gateAlterCancelContentBlockListener = new GateAlterCancelContentBlockListener(this);
		this.gateAlterCancelContentEntityListener = new GateAlterCancelContentEntityListener(this);
		this.gateAlterCancelContentPlayerListener = new GateAlterCancelContentPlayerListener(this);
		this.gateAlterCancelFrameBlockListener = new GateAlterCancelFrameBlockListener(this);
		this.gateAlterCancelFrameEntityListener = new GateAlterCancelFrameEntityListener(this);
		this.gateAlterCancelFramePlayerListener = new GateAlterCancelFramePlayerListener(this);
		this.gateAlterMonitorContentBlockListener = new GateAlterMonitorContentBlockListener(this);
		this.gateAlterMonitorContentEntityListener = new GateAlterMonitorContentEntityListener(this);
		this.gateAlterMonitorContentPlayerListener = new GateAlterMonitorContentPlayerListener(this);
		this.gateAlterMonitorFrameBlockListener = new GateAlterMonitorFrameBlockListener(this);
		this.gateAlterMonitorFrameEntityListener = new GateAlterMonitorFrameEntityListener(this);
		this.gateAlterMonitorFramePlayerListener = new GateAlterMonitorFramePlayerListener(this);
		
		this.playerListener = new PluginPlayerListener(this);
		this.playerListenerVis = new PluginPlayerListenerVis(this);
		this.gateListener = new PluginGateListener(this);
		this.blockListener = new PluginBlockListener(this);
	}
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// Register Triggers & Actions
		Gates.i.registerAction(ActionUse.getInstance());
		Gates.i.registerAction(ActionUseForced.getInstance());
		Gates.i.registerAction(ActionOpen.getInstance());
		Gates.i.registerAction(ActionClose.getInstance());
		Gates.i.registerAction(ActionFxe.getInstance());
		Gates.i.registerAction(ActionFxg.getInstance());
		Gates.i.registerAction(ActionMsg.getInstance());
		
		Gates.i.registerTrigger(TriggerEnter.getInstance());
		Gates.i.registerTrigger(TriggerBtp.getInstance());
		Gates.i.registerTrigger(TriggerAtp.getInstance());
		Gates.i.registerTrigger(TriggerUse.getInstance());
		Gates.i.registerTrigger(TriggerOpen.getInstance());
		Gates.i.registerTrigger(TriggerClose.getInstance());
		Gates.i.registerTrigger(TriggerPowerOn.getInstance());
		Gates.i.registerTrigger(TriggerPowerOff.getInstance());
		Gates.i.registerTrigger(TriggerFrameAlter.getInstance());
		Gates.i.registerTriggers(TriggerHour.triggerHours.values());
		
		// Load Conf from disk
		Conf.load();
		
		// Add Base Commands
		this.cmdGate = new CmdGate();
		this.cmd.addCommand(this.cmdGate);
		
		// Add Argument Handlers
		this.cmd.setArgHandler(Gate.class, AHGate.getInstance());
		this.cmd.setArgHandler(Trigger.class, AHTrigger.getInstance());
		this.cmd.setArgHandler(Action.class, AHAction.getInstance());
		this.cmd.setArgHandler(Effect.class, AHEffect.getInstance());		
		
		// Register events
		this.registerEvent(Type.PLAYER_MOVE, this.playerListener, Priority.High);
		this.registerEvent(Type.CUSTOM_EVENT, this.gateListener, Priority.Normal);
		this.registerEvent(Type.PLAYER_PRELOGIN, this.playerListenerVis, Priority.Lowest);
		this.registerEvent(Type.PLAYER_QUIT, this.playerListenerVis, Priority.Lowest);
		this.registerEvent(Type.BLOCK_PHYSICS, this.blockListener, Priority.Monitor);
		
		// Register the gate protection related events.
		GateAlterType.registerListeners();
		
		// Disable Vanilla Gates (if requested)
		if (Conf.disableVanillaGates)
		{
			this.registerEvent(Type.PLAYER_PORTAL, this.playerListener);
		}
		
		// Register the HourTriggingTask
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new HourTriggingTask(), Conf.hourTriggingTaskTicks, Conf.hourTriggingTaskTicks);
		
		postEnable();
	}
	
	@Override
	public GsonBuilder getGsonBuilder()
	{
		return super.getGsonBuilder()
		.registerTypeAdapter(WorldCoord3.class, WorldCoord3Adapter.getInstance())
		.registerTypeAdapter(LocWrap.class, LocWrapAdapter.getInstance())
		;
	}
	
}
