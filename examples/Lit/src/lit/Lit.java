package lit;

public class Lit {

	private void testNewsFlash() {
		NewsFlash nf = new NewsFlash();
		nf.printNews();
	}
	
	private void testDictionary() {
		Dictionary dict = new Dictionary();
		String trans = dict.getTranslation("hi");
		System.out.println(trans);
		trans = dict.getTranslation("你好");
		System.out.println(trans);
	}
	
	private void testSolidot() {
		Solidot solidot = new Solidot();
		solidot.printNews();
	}
	
	private void testHackerNews() {
		HackerNews hackernews = new HackerNews();
		hackernews.printNews();
	}
	
	private void testWeather() {
		Weather weather = new Weather();
		weather.printWeather();
	}
	
	public static void main(String[] args) {
//		Lit lit = new Lit();
		
//		lit.testNewsFlash();
		
//		lit.testDictionary();
		
//		lit.testSolidot();
		
//		lit.testHackerNews();
		
//		lit.testWeather();	
	}
}
