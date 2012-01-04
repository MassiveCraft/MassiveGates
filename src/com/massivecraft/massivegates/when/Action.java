package com.massivecraft.massivegates.when;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.Gate;

public interface Action
{
	/** The id should be unique and never change */
	public String getId();
	
	/** An nicename for the action */
	public String getName();
	
	/** A description of the action */
	public String getDesc();
	
	/** Perform the action */
	public void perform(Gate gate, Entity entity, Cancellable cancellable);
}
