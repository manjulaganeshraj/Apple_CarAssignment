package com.demo.rentalCarsAssignment;

public class CarDetail {
	private Car car;

	public Car getCar ()
	{
		return car;
	}

	public void setCar (Car car)
	{
		this.car = car;
	}

	@Override
	public String toString()
	{
		return "[car = "+car+"]";
	}
}
