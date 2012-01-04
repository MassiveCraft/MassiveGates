package com.massivecraft.massivegates.fx;

import java.util.List;
import java.util.Map;

import org.bukkit.entity.Entity;

import com.massivecraft.massivegates.Gate;

public interface GateFx
{
	/** Return null if can't handle. Otherwise a map argname2val. */
	public Map<String, Object> parse(String parsie);
	
	/** Errors found during the parse */
	public List<String> getParseErrors();
	public String getName();
	public String getUsagePattern();
	public String getDesc();
	public String getDesc(String parsie);
	public String getDesc(Map<String, Object> fxArgs);
	
	public void run(String parsie, Gate gate, Entity entity);
	public void run(Map<String, Object> fxArgs, Gate gate, Entity entity);
}