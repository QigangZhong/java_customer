package com.qigang.factory;

import java.io.FileReader;
import java.util.Properties;

public class BasicFactory {
	private static BasicFactory factory=new BasicFactory();
	private static Properties props=null;
	private BasicFactory(){}
	public static BasicFactory getFactory(){
		return factory;
	}
	
	static{
		props=new Properties();
		try {
			props.load(new FileReader(BasicFactory.class.getClassLoader().getResource("config.properties").getPath()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public <T> T getInstance(Class<T> c){
		try {
			String name=c.getSimpleName();
			String implName = props.getProperty(name);
			return (T)Class.forName(implName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
