package br.com.backend.rest.data;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonResolver {
	private static ObjectMapper mapper = new ObjectMapper();

	public static String toJson(final Object o) {
		String json = null;
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static Object fromJson(final String o, final Class<?> cl) {
		Object obj = null;
		try {
			obj = mapper.readValue(o, cl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
