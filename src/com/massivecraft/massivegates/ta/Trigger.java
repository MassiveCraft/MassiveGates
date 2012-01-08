package com.massivecraft.massivegates.ta;

public interface Trigger
{
	/** The id should be unique and never change */
	public String getId();
	
	/** An nicename for the trigger */
	public String getName();
	
	/** A description of the trigger */
	public String getDesc();
}
