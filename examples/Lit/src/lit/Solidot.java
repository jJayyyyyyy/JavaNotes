package lit;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Solidot {
	private String solidotURL = "https://www.solidot.org/index.rss";
	private String reSolidot = "<title><!\\[CDATA\\[(.*?)\\]\\]>";
	private ArrayList<String> solidotTitleList = new ArrayList<String>();
	
	public void printNews() {
		getNewsList();
		for( int i = 0; i < solidotTitleList.size(); i++ ) {
			System.out.println(i+1 + ":\t" + solidotTitleList.get(i) + "\n");
		}
	}
	
	private void getNewsList() {
		Request req = new Request();
		req.https = true;
		
		String strURL = solidotURL;
		String resp = req.getResp(strURL);
		
		if( resp != null ) {
			Matcher m = Pattern.compile(reSolidot).matcher(resp);
			while( m.find() ) {
				solidotTitleList.add(m.group(1));
			}
		}
	}
}
