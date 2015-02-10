package com.massivecraft.massivegates.util;

public class StandableInfo
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public StandableInfo(final boolean validGround, final boolean validLower, final boolean validUpper)
	{
		this.validGround = validGround;
		this.validLower = validLower;
		this.validUpper = validUpper;
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private final boolean validGround;
	public boolean isValidGround() { return this.validGround; }
	
	private final boolean validLower;
	public boolean isValidLower() { return this.validLower; }
	
	private final boolean validUpper;
	public boolean isValidUpper() { return this.validUpper; }

}
