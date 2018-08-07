package util;
/**
* @author jan
* @data 2018年8月7日 下午5:48:40
*/
public class CreateFlyweight extends Flyweight {
	private String str;

	@Override
	public void operation() {
		System.out.println("create Flyweight ： " + str);
	}
	
	public CreateFlyweight(String str) {
		this.str = str;
	}
	

}
