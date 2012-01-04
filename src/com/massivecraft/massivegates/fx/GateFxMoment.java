package com.massivecraft.massivegates.fx;

public enum GateFxMoment
{
	OPEN("open"),
	CLOSE("close"),
	BEFORE_TELEPORT("btp"),
	AFTER_TELEPORT("atp"),
	;
	
	protected final String shortName;
	public final String getShortName() { return this.shortName; } 
	
	private GateFxMoment(String nicename)
	{
		this.shortName = nicename;
	}
	
	public static GateFxMoment getGateFxMoment(String str)
	{
		if (str == null || str.length() < 1) return null;
		char c = str.toLowerCase().charAt(0); 
		GateFxMoment ret = null;
		switch (c)
		{
			case 'o': ret = OPEN; break;
			case 'c': ret = CLOSE; break;
			case 'b': ret = BEFORE_TELEPORT; break;
			case 'a': ret = AFTER_TELEPORT; break;
		}
		return ret;
	}
	
	public static String getOrShorts()
	{
		StringBuilder sb = new StringBuilder();
		for (GateFxMoment m : GateFxMoment.values())
		{
			sb.append(m.getShortName());
			sb.append('|');
		}
		String ret = sb.toString();
		ret = ret.substring(0, ret.length()-1);
		return ret;
	}
}
