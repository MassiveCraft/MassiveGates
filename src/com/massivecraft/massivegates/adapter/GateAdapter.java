package com.massivecraft.massivegates.adapter;

import java.lang.reflect.Type;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.P;
import com.massivecraft.mcore4.xlib.gson.JsonDeserializationContext;
import com.massivecraft.mcore4.xlib.gson.JsonDeserializer;
import com.massivecraft.mcore4.xlib.gson.JsonElement;
import com.massivecraft.mcore4.xlib.gson.JsonParseException;
import com.massivecraft.mcore4.xlib.gson.JsonSerializationContext;
import com.massivecraft.mcore4.xlib.gson.JsonSerializer;

public class GateAdapter implements JsonDeserializer<Gate>, JsonSerializer<Gate>
{
	@Override
	public JsonElement serialize(Gate src, Type typeOfSrc, JsonSerializationContext context)
	{
		return P.p.gfgson.toJsonTree(src, typeOfSrc);
	}

	// TODO: This is just to survive the update!
	
	@Override
	public Gate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		Gate ret = P.p.gfgson.fromJson(json, typeOfT);
		
		if (ret.targetFixedLoc != null)
		{
			ret.getTarget().location = ret.targetFixedLoc;
			ret.targetFixedLoc = null;
		}
		else if (ret.targetGateId != null)
		{
			ret.getTarget().gateId = ret.targetGateId;
			ret.targetGateId = null;
		}
		return ret;
	}
	
	private GateAdapter() {}
	private static GateAdapter instance = new GateAdapter();
	public static GateAdapter getInstance() { return instance; }
}
