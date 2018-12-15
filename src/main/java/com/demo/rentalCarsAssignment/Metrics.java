package com.demo.rentalCarsAssignment;

public class Metrics
{
	private Float depreciation;

	private Float yoymaintenancecost;

	private Rentalcount rentalcount;

	public Float getDepreciation ()
	{
		return depreciation;
	}

	public void setDepreciation (Float depreciation)
	{
		this.depreciation = depreciation;
	}

	public Float getYoymaintenancecost ()
	{
		return yoymaintenancecost;
	}

	public void setYoymaintenancecost (Float yoymaintenancecost)
	{
		this.yoymaintenancecost = yoymaintenancecost;
	}

	public Rentalcount getRentalcount ()
	{
		return rentalcount;
	}

	public void setRentalcount (Rentalcount rentalcount)
	{
		this.rentalcount = rentalcount;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [depreciation = "+depreciation+", yoymaintenancecost = "+yoymaintenancecost+", rentalcount = "+rentalcount+"]";
	}
}