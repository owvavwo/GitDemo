package com.hui.factory;

public class Car implements Moveable {
	private static Car car = new Car();		//����
	//List<Car> cars = new ArrayList<Car>(); //����
	
	//private Car() {}
	
	public static Car getInstance() {
		return car;
	}
	
	public void run() {
		System.out.println("ð���̶����ܡ�����");
	}
}
