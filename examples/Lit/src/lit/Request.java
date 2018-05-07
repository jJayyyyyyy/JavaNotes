package lit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import javax.net.ssl.HttpsURLConnection;

public class Request {

	public boolean https = true;

	private URL getURL(String strURL) {
		URL url = null;
		try {
			url = new URL(strURL);
		}catch(MalformedURLException e){
			e.printStackTrace();
		}
		return url;
	}

	private String readFromStream(InputStream is) {
		StringBuilder sb = new StringBuilder();

		if( is != null ) {
			InputStreamReader isReader = new InputStreamReader(is, Charset.forName("UTF-8"));
			BufferedReader bufReader = new BufferedReader(isReader);
			String line = "";
			try {
				line = bufReader.readLine();
				while ( line != null ) {
					sb.append(line);
					line = bufReader.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public String getResp(String strURL) {
		URL url = getURL(strURL);
		String resp = "";

		if( url != null ) {
			HttpURLConnection conn = null;

			InputStream is = null;

			try {
				if( this.https == true ) {
					conn = (HttpsURLConnection) url.openConnection();
				}else {
					conn = (HttpURLConnection) url.openConnection();
				}
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(5000);
				conn.setReadTimeout(2000);
				conn.connect();

				if ( conn.getResponseCode() == 200 ) {
					is = conn.getInputStream();
					resp = readFromStream(is);
				}
				conn.disconnect();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resp;
	}
}
