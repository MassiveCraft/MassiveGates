package com.massivecraft.massivegates.adapter;

import java.lang.reflect.Type;

import com.massivecraft.massivegates.WorldCoord3;
import com.massivecraft.mcore4.lib.gson.JsonDeserializationContext;
import com.massivecraft.mcore4.lib.gson.JsonDeserializer;
import com.massivecraft.mcore4.lib.gson.JsonElement;
import com.massivecraft.mcore4.lib.gson.JsonParseException;
import com.massivecraft.mcore4.lib.gson.JsonPrimitive;
import com.massivecraft.mcore4.lib.gson.JsonSerializationContext;
import com.massivecraft.mcore4.lib.gson.JsonSerializer;

public class WorldCoord3Adapter implements JsonDeserializer<WorldCoord3>, JsonSerializer<WorldCoord3>
{
	@Override
	public JsonElement serialize(WorldCoord3 src, Type typeOfSrc, JsonSerializationContext context)
	{
		return new JsonPrimitive(src.toPersistString());
	}

	@Override
	public WorldCoord3 deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		return new WorldCoord3(json.getAsString());
	}
	
	private WorldCoord3Adapter() {}
	private static WorldCoord3Adapter instance = new WorldCoord3Adapter();
	public static WorldCoord3Adapter getInstance() { return instance; }
}
