package practice.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PracticeClass_DP {

	@Test(dataProvider = "getData")
	public void getProductInfoTest(String a, String b) {
		
		System.out.println(a+" "+b);
	}
	
	@DataProvider
	public Object[][] getData(){
		Object[][] objAr=new Object[3][2];
		objAr[0][0]="iphone";
		objAr[0][1]="Apple iPhone 15 (128 GB) - Black";
		objAr[1][0]="iphone";
		objAr[1][1]="Apple iPhone 13 (128GB) - Midnight";
		objAr[2][0]="iphone";
		objAr[2][1]="Apple iPhone 13 (128GB) - Starlight";
		
		return objAr;
	}
}
