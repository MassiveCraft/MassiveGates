package com.massivecraft.massivegates.adapter;

import java.lang.reflect.Type;

import com.massivecraft.massivegates.LocWrap;
import com.massivecraft.mcore4.xlib.gson.JsonDeserializationContext;
import com.massivecraft.mcore4.xlib.gson.JsonDeserializer;
import com.massivecraft.mcore4.xlib.gson.JsonElement;
import com.massivecraft.mcore4.xlib.gson.JsonParseException;
import com.massivecraft.mcore4.xlib.gson.JsonPrimitive;
import com.massivecraft.mcore4.xlib.gson.JsonSerializationContext;
import com.massivecraft.mcore4.xlib.gson.JsonSerializer;

public class LocWrapAdapter implements JsonDeserializer<LocWrap>, JsonSerializer<LocWrap>
{
	@Override
	public JsonElement serialize(LocWrap src, Type typeOfSrc, JsonSerializationContext context)
	{
		return new JsonPrimitive(src.toPersistString());
	}

	@Override
	public LocWrap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		return new LocWrap(json.getAsString());
	}
	
	private LocWrapAdapter() {}
	private static LocWrapAdapter instance = new LocWrapAdapter();
	public static LocWrapAdapter getInstance() { return instance; }
}
