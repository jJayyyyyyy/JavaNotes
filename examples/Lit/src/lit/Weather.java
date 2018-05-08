package lit;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Weather {
	private String city = "杭州";
	private String weatherHangzhouURL = "http://www.weather.com.cn/weather/101210101.shtml";
	
	private String reWeather = "<p title=\".*?\" class=\"wea\">(.*?)</p>";
	private String reTempDay = "<p class=\"tem\"><span>(\\d+)</span>/<i>(\\d+?)℃</i>";
	private String reTempNight = "<p class=\"tem\"><i>(\\d+?)℃</i>";
	private String reTempTomorrow = "<span>(\\d+)℃</span>/<i>(\\d+?)℃</i>";
	
	private ArrayList<WeatherItem> weatherList = new ArrayList<WeatherItem>();
	
	public void printWeather() {
		getWeather();
		System.out.println(city);
		for( int i = 0; i < weatherList.size(); i++ ) {
			System.out.println( weatherList.get(i) );
		}
	}
	
	private void getWeather() {
		Request req = new Request();
		req.https = false;
		
		String strURL = weatherHangzhouURL;
		String resp = req.getResp(strURL);
		
		if( resp != null ) {
			Matcher mWeather = Pattern.compile(reWeather).matcher(resp);
			Matcher mTempDay = Pattern.compile(reTempDay).matcher(resp);
			Matcher mTempNight = Pattern.compile(reTempNight).matcher(resp);
			Matcher mTempTomorrow = Pattern.compile(reTempTomorrow).matcher(resp);
			
			String weatherToday = "";
			if( mWeather.find() ) {
					weatherToday = mWeather.group(1);
			}
			
			String weatherTomorrow = "";
			if( mWeather.find() ) {
					weatherTomorrow = mWeather.group(1);
			}
			
			String tempDayLow = "";
			String tempDayHigh = "";
			String tempNight = "";
			String tempTomorrowLow = "";
			String tempTomorrowHigh = "";
			
			if( mTempNight.find() ) {
					tempNight = mTempNight.group(1);
					if( mTempTomorrow.find() ) {
						tempTomorrowHigh = mTempTomorrow.group(1);
						tempTomorrowLow = mTempTomorrow.group(2);
						weatherList.add(new WeatherItem("夜间", weatherToday, tempNight, null));
						weatherList.add(new WeatherItem("明天", weatherTomorrow, tempTomorrowLow, tempTomorrowHigh));
					}
			}else if( mTempDay.find() ) {
				tempDayHigh = mTempDay.group(1);
				tempDayLow = mTempDay.group(2);
				if( mTempDay.find() ) {
					tempTomorrowHigh = mTempDay.group(1);
					tempTomorrowLow = mTempDay.group(2);
					weatherList.add(new WeatherItem("今天", weatherToday, tempDayLow, tempDayHigh));
					weatherList.add(new WeatherItem("明天", weatherTomorrow, tempTomorrowLow, tempTomorrowHigh));
				}
			}
			
			if( weatherList.size() == 0 ) {
				weatherList.add(new WeatherItem("今天", "-", "-", "-"));
				weatherList.add(new WeatherItem("明天", "-", "-", "-"));
			}
		}
	}
}

class WeatherItem{
	private String time;
	private String weather;
	private String tempLow;
	private String tempHigh;
	
	public WeatherItem(String time, String weather, String tempLow, String tempHigh) {
		this.time = time;
		this.weather = weather;
		this.tempLow = tempLow;
		this.tempHigh = tempHigh;
	}
	
	public String toString() {
		String string;
		if( tempHigh != null ) {
			string = time + "\t" + weather + "\t" + tempLow + " ~ " + tempHigh + " ℃";
		}else {
			string = time + "\t" + weather + "\t" + tempLow + " ℃";
		}
		return string;
	}
}
