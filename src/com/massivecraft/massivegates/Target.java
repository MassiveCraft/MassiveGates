package com.massivecraft.massivegates;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.massivecraft.massivecore.mixin.Mixin;
import com.massivecraft.massivecore.mixin.TeleporterException;
import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivecore.ps.PSFormatDesc;
import com.massivecraft.massivecore.teleport.DestinationSimple;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.GateColl;

public class Target
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public PS location;
	public void setLocation(Location location) { this.remove(); this.location = PS.valueOf(location); }
	public PS getLocation() { return this.location; }
	
	public String gateId;
	public void setGate(Gate gate) { this.remove(); this.gateId = gate.getId(); }
	public Gate getGate() { return GateColl.get().get(this.gateId); }
	
	// -------------------------------------------- //
	// METHODS
	// -------------------------------------------- //
	
	public void remove()
	{
		this.location = null;
		this.gateId = null;
	}
	
	public TargetType getType()
	{
		if (this.location != null) return TargetType.LOCATION;
		if (this.getGate() != null) return TargetType.GATE;
		return TargetType.NONE;
	}
	
	public String getDesc()
	{
		switch (this.getType())
		{
			case LOCATION: return Txt.parse("<k>Location: <v>%s", this.location.toString(PSFormatDesc.get()));
			case GATE: return Txt.parse("<k>Gate: <v>%s", this.getGate().getIdNameStringShort());
			default: return Txt.parse("<v>*NONE*");
		}
	}
	
	public boolean exists()
	{
		switch (this.getType())
		{
			case LOCATION: return this.getLocation() != null;
			case GATE: return this.getGate().getExit().getLocation() != null;
			default: return false;
		}
	}
	
	public boolean teleport(Player player)
	{
		if ( ! this.exists()) return false;
		try
		{
			switch(this.getType())
			{
				case LOCATION:
					Mixin.teleport(player, new DestinationSimple(this.getLocation()));
					return true;
				case GATE:
					Mixin.teleport(player, new DestinationSimple(this.getGate().getExit()));
					return true;
				default:
					return false;
			}
		}
		catch (TeleporterException e)
		{
			player.sendMessage(e.getMessage());
		}
		return false;
	}
	
}
