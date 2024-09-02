package com.vtiger.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNum() {
		Random random=new Random();
		int randomNum = random.nextInt(5000);
		return randomNum;
	}

	public String getSysDateYYYYMMDD() {

		Date dateObj=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("YYYY-MM-dd");
		String date = sim.format(dateObj);
		return date;
	}

	public String getRequiredDateYYYYMMDD(int days) {
		Date dateObj= new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		sim.format(dateObj);
		//for required days
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate= sim.format(cal.getTime());
		return reqDate;
		
//		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DAY_OF_MONTH, days);
//		String date = sdf.format(cal.getTime());
//		return date;
				
	}
}
