package com.amazonmshop.framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
	Properties props = new Properties();

	public ReadProperties(String fileName) throws IOException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try (InputStream resourceStream = loader.getResourceAsStream(fileName)) {
			props.load(resourceStream);
		}
	}

	// Method to get value from property file for given key
	public String getElement(String ElementName) throws Exception {
		// Read value using the logical name as Key
		String data = props.getProperty(ElementName);
		return data;
	}

}
