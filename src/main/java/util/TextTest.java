package util;

import java.util.ArrayList;
import java.util.List;

public class TextTest {

	public static void main(String[] args) {
		String s = Constant.text;
		String[] str = s.split(" ");
		for(String ss : str) {
			//System.out.println(ss);
		}
		
		List<String> list = new ArrayList<String>();
		
		for(String ss : str) {
			//System.out.println(ss.indexOf('@'));
			if(ss.indexOf('@') != -1) {
				String[] sk = ss.split("\\t");
				for(String skk : sk) {
					if(skk.indexOf('@') != -1) {
						list.add(skk);
					}
				}
				//list.add(ss);
			}
		}
		
		List<String> list1 = new ArrayList<String>();
		
		for(String ss : list) {
			System.out.println(ss);
		}
		
		System.out.println(list.size());
		
		
	}
	
}
