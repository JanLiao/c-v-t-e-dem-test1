package util;
/**
* @author jan
* @data 2018年8月7日 下午4:50:50
*/
public class TestD implements C<Person> {
	
	@Override
	public Person getMethod(String s) {
		return null;
	}

//	@Override
//	public T getMethod(String s) {
//		T t = (T)new Person(1, s);
//		return t;
//	}
	
	public static void main(String[] args) {
		//TestD<Person> test = new TestD<Person>();
		Person p = new TestD().getMethod("jan");
		System.out.println("output = " + p);
	}

}
