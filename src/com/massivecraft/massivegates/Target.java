package com.massivecraft.massivegates;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.massivecraft.mcore.mixin.Mixin;
import com.massivecraft.mcore.mixin.TeleporterException;
import com.massivecraft.mcore.ps.PS;
import com.massivecraft.mcore.ps.PSFormatDesc;

public class Target
{
	public PS location;
	public void setLocation(Location location) { this.remove(); this.location = PS.valueOf(location); }
	public PS getLocation() { return this.location; }
	
	public String gateId;
	public void setGate(Gate gate) { this.remove(); this.gateId = gate.getId(); }
	public Gate getGate() { return GateColl.i.get(this.gateId); }
	
	public String rubberServerName;
	public void setRubberServer(String name) { this.remove(); this.rubberServerName = name; }
	public String getRubberServer() { return this.rubberServerName; }
	
	public void remove()
	{
		this.location = null;
		this.gateId = null;
		this.rubberServerName = null;
	}
	
	public TargetType getType()
	{
		if (this.location != null) return TargetType.LOCATION;
		if (this.getGate() != null) return TargetType.GATE;
		if (this.rubberServerName != null) return TargetType.RUBBERSERVER;
		return TargetType.NONE;
	}
	
	public String getDesc()
	{
		switch(this.getType())
		{
			case LOCATION: return "<k>Location: <v>"+this.location.toString(PSFormatDesc.get());
			case GATE: return "<k>Gate: <v>"+this.getGate().getIdNameStringShort();
			case RUBBERSERVER: return "<k>RubberServer: <v>"+this.rubberServerName;
			default: return "<v>*NONE*";
		}
	}
	
	public boolean exists()
	{
		switch(this.getType())
		{
			case LOCATION: return this.getLocation() != null;
			case GATE: return this.getGate().getExit().getLocation() != null;
			case RUBBERSERVER: return true;
			default: return false;
		}
	}
	
	public boolean teleport(Entity entity)
	{
		if (!this.exists()) return false;
		try
		{
			switch(this.getType())
			{
				case LOCATION:
					Mixin.teleport((Player)entity, this.getLocation());
					return true;
				case GATE:
					Mixin.teleport((Player)entity, this.getGate().getExit());
					return true;
				case RUBBERSERVER: 
					if (!(entity instanceof Player)) return false;
					Player player = (Player) entity;
					player.sendPluginMessage(P.p, "RubberBand", this.rubberServerName.getBytes());
					return true;
				default:
					return false;
			}
		}
		catch (TeleporterException e)
		{
			if (entity instanceof Player)
			{
				Player player = (Player) entity;
				player.sendMessage(e.getMessage());
			}
		}
		return false;
	}
	
	public void delayedTeleport(final Entity entity)
	{
		final Target ME = this;
		Bukkit.getScheduler().scheduleSyncDelayedTask(P.p, new Runnable()
		{
			public void run()
			{
				ME.teleport(entity);
			}
		});
	}
}
