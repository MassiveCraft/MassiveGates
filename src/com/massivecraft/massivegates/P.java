package com.massivecraft.massivegates;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;

import com.massivecraft.massivegates.adapter.WorldCoord3Adapter;
import com.massivecraft.massivegates.cmd.CmdGate;
import com.massivecraft.massivegates.cmdarg.AHGate;
import com.massivecraft.massivegates.event.GateAlterType;
import com.massivecraft.massivegates.privatelistener.PlayerListenerMonitor;
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
	public PlayerListenerMonitor playerListenerMonitor;
	
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
		
		this.playerListenerMonitor = new PlayerListenerMonitor(this);
	}
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// This will force the static instance to be created.
		Gates.i.getClass();
		
		// Load Conf from disk
		Conf.load();
		
		// Add Base Commands
		this.cmdGate = new CmdGate();
		this.cmd.addCommand(this.cmdGate);
		
		// Add Argument Handlers
		this.cmd.setArgHandler(Gate.class, AHGate.getInstance());
		
		// Register events
		this.registerEvent(Type.PLAYER_MOVE, this.playerListenerMonitor, Priority.Monitor);
		
		// Register the gate protection related events.
		GateAlterType.registerListeners();
		
		postEnable();
	}
	
	@Override
	public GsonBuilder getGsonBuilder()
	{
		return super.getGsonBuilder()
		.registerTypeAdapter(WorldCoord3.class, WorldCoord3Adapter.getInstance());
	}
	
}
