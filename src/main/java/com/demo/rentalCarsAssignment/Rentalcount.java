package com.demo.rentalCarsAssignment;


public class Rentalcount
{
	private Integer yeartodate;

	private Integer lastweek;

	public Integer getYeartodate ()
	{
		return yeartodate;
	}

	public void setYeartodate (Integer yeartodate)
	{
		this.yeartodate = yeartodate;
	}

	public Integer getLastweek ()
	{
		return lastweek;
	}

	public void setLastweek (Integer lastweek)
	{
		this.lastweek = lastweek;
	}

	@Override
	public String toString()
	{
		return "[yeartodate = "+yeartodate+", lastweek = "+lastweek+"]";
	}
}