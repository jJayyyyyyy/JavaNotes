package castle;

// 如果用抽象类，子类就要用implements而不是extends
// 其次，每一个子类必须补全实现父类中的abstract方法
// 假设doCmd是abstract
// 那么子类HandlerBye必须实现doCmd
public class Handler {
	public boolean isBye(){
		return false;
	}

	public void doCmd(String cmd) {
		
	}
}
