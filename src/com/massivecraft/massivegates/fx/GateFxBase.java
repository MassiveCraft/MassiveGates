package com.massivecraft.massivegates.fx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Entity;

import com.massivecraft.massivegates.Gate;

public abstract class GateFxBase implements GateFx
{
	public abstract Map<String, Object> innerParse(String parsie);
	
	@Override
	public Map<String, Object> parse(String parsie)
	{
		this.parseErrors.clear();
		return this.innerParse(parsie);
	}

	protected List<String> parseErrors = new ArrayList<String>();  
	
	@Override
	public List<String> getParseErrors()
	{
		return this.parseErrors;
	}
	
	@Override
	public String getDesc(String parsie)
	{
		Map<String, Object> fxArgs = this.parse(parsie);
		return this.getDesc(fxArgs);
	}
	
	@Override
	public void run(String parsie, Gate gate, Entity entity)
	{
		Map<String, Object> fxArgs = this.parse(parsie);
		if (fxArgs == null) return;
		this.run(fxArgs, gate, entity);
	}
	
	@Override
	public String toString()
	{
		return this.getName();
	}
}
