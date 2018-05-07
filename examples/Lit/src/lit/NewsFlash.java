package lit;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewsFlash {

	private ArrayList<NewsItem> newsList = new ArrayList<NewsItem>();
	private String newsflashURL = "https://36kr.com/api/newsflash";
	
	private void getNewsList() {
		Request req = new Request();
		req.https = true;
		
		String strURL = newsflashURL;
		String resp = req.getResp(strURL);
		
		if ( resp != null ) {
			try {
				JSONArray jsonArray = new JSONObject(resp).getJSONObject("data").getJSONArray("items");
				for ( int i = 0; i < jsonArray.length(); i++ ) {
					String subject = jsonArray.getJSONObject(i).getString("title");
					String content = jsonArray.getJSONObject(i).getString("description");
					String updateDate = jsonArray.getJSONObject(i).getString("updated_at");
					newsList.add(new NewsItem(subject, content, updateDate));
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
		}		
	}
	
	public void printNews() {
		getNewsList();
		if( newsList != null ) {
			for( int i = 0; i < newsList.size(); i++ ) {
				System.out.println(newsList.get(i));
			}
		} else {
			System.out.println("newsList not found");
		}
	}
	
	public static void main(String[] args) {
		NewsFlash nf = new NewsFlash();
		nf.printNews();
	}
}


class NewsItem {
	private String mSubject;
	private String mContent;
	private String mUpdateDate;
	
	public NewsItem(String subject, String content, String updateDate) {
		mSubject = subject;
		mContent = content;
		mUpdateDate = updateDate;
	}
	
	@Override
	public String toString() {
		String news;
		String fullNews = mUpdateDate + "\n" + mSubject + "\n" + mContent + "\n";
		news = fullNews;
//		String briefNews = mUpdateDate + "\n" + mSubject + "\n";
//		news = briefNews;
		return news;
	}
}