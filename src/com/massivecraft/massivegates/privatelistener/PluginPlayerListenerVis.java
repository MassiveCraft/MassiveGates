package com.massivecraft.massivegates.privatelistener;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerPreLoginEvent;

import com.massivecraft.massivegates.P;
import com.massivecraft.massivegates.util.VisualizeUtil;

public class PluginPlayerListenerVis extends PlayerListener
{
	P p;
	public PluginPlayerListenerVis(P p)
	{
		this.p = p;
	}
	
	@Override
	public void onPlayerPreLogin(PlayerPreLoginEvent event)
	{
		VisualizeUtil.onPlayerPreLogin(event.getName());
	}
	
}
