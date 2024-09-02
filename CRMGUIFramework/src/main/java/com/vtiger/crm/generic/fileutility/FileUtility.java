package com.vtiger.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class FileUtility {

	public String getDataFromPropertyFile(String key) throws Throwable {
		FileInputStream fis=new FileInputStream("./configAppData/commondatavtiger.properties");
		Properties pObj=new Properties();
		pObj.load(fis);
		String data = pObj.getProperty(key);
		return data;
	}
}
