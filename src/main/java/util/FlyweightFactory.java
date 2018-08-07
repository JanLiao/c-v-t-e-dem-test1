package util;

import java.util.Hashtable;

/**
* @author jan
* @data 2018年8月7日 下午5:51:26
*/
public class FlyweightFactory {
	private Hashtable flyweights = new Hashtable();
	private Hashtable<Object, Flyweight> flyweights1 = new Hashtable<Object, Flyweight>();
	public FlyweightFactory() {
		
	}
	
	public Flyweight getFlyweight(Object obj) {
		Flyweight flyweight = (Flyweight)flyweights.get(obj);
		if(flyweight == null) {
			flyweight = new CreateFlyweight((String) obj);
			flyweights.put(obj, flyweight);
		}
		return flyweight;
	}
	
	public int getFlyweightSize() {
		System.out.println("flyweights : " + flyweights);
		return flyweights.size();
	}

	public static void main(String[] args) {
		FlyweightFactory flyweightFactory = new FlyweightFactory();
        Flyweight fly1 = flyweightFactory.getFlyweight("abc");
        Flyweight fly2 = flyweightFactory.getFlyweight("b");
        Flyweight fly3 = flyweightFactory.getFlyweight("abc");
        Flyweight fly4 = flyweightFactory.getFlyweight("ef");
        Flyweight fly5 = flyweightFactory.getFlyweight("ef");
        Flyweight fly6 = flyweightFactory.getFlyweight("ef");
        fly1.operation();
        fly2.operation();
        fly3.operation();
        fly4.operation();
        fly5.operation();
        fly6.operation();
        System.out.println(flyweightFactory.getFlyweightSize());
	}

}
