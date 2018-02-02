package com.personal.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigHandler {

	static Properties properties;
	static File file; 
	
	static void saveProperties(Properties p) throws IOException {
		FileOutputStream fOut = new FileOutputStream(file);
		p.store(fOut, "Properties");
		fOut.close();
		System.out.println("After saving properties:" + p);
	}
	
	static void loadProperties(Properties p)throws IOException {
        FileInputStream fIn = new FileInputStream(file);
        p.load(fIn);
        fIn.close();
        System.out.println("After loading properties:" + p);
    }
	
	public static void main(String[] args) throws IOException {
		file = new File("test.properties");
		Properties table = new Properties();
		
		table.setProperty("hello", "abcd");
		table.setProperty("world", "1234");
		
		saveProperties(table);
		
		table.setProperty("after", "AFTERDATA");
		
		saveProperties(table);
		loadProperties(table);
	}

}
