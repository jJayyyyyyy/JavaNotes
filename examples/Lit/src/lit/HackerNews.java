package lit;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HackerNews {
	private String newsURL = "https://news.ycombinator.com/";
	private String reHackerNews = "storylink\">(.*?)</a>";
	private ArrayList<String> newsTitleList = new ArrayList<String>();
	
	public void printNews() {
		getNewsList();
		for( int i = 0; i < newsTitleList.size(); i++ ) {
			System.out.println(i+1 + ":\t" + newsTitleList.get(i) + "\n");
		}
	}
	
	private void getNewsList() {
		Request req = new Request();
		req.https = true;
		
		String strURL = newsURL;
		String resp = req.getResp(strURL);
		
		if( resp != null ) {
			Matcher m = Pattern.compile(reHackerNews).matcher(resp);
			while( m.find() ) {
				newsTitleList.add(m.group(1));
			}
		}
	}
}
