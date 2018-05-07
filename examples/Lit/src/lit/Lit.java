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
	
	public static void main(String[] args) {
		Lit lit = new Lit();
		
//		lit.testNewsFlash();
//		lit.testDictionary();
	}

}
