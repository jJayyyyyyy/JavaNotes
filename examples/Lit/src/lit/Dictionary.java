package lit;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dictionary {
	private String youdaoDictURL = "http://dict.youdao.com/fsearch?q=";
	private String pattern = "<content><!\\[CDATA\\[(.*?)\\]\\]>";
	
	public String getTranslation (String query) {
		String translation = "";
		if ( query != null ) {
			try {
				Request req = new Request();
				req.https = false;
				
				String strURL = youdaoDictURL + URLEncoder.encode(query, "UTF-8");
				String resp = req.getResp(strURL);
				
				if( resp != null ) {
					Matcher m = Pattern.compile(pattern).matcher(resp);
					if ( m.find() == true ) {
						translation = m.group(1);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return translation;
	}
}
