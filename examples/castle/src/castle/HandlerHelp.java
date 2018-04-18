package castle;

public class HandlerHelp extends Handler {
	@Override
	public void doCmd(String help){
		System.out.print("Input [bye] to exit. Input [help] for help. Input [go east] to go east.\n");
	}
}
