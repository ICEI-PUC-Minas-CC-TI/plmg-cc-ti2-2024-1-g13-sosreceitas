package model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public interface JsonFormatter {
	public JsonObject toJson();

	public default JsonArray toJsonArray() {
		JsonArray jsonArray = new JsonArray();
		jsonArray.add(toJson());
		return jsonArray;
	}
}