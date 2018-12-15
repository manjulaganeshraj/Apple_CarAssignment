package com.demo.rentalCarsAssignment;

//import com.fasterxml.jackson.annotation.JsonProperty;

public class Car
{
	private String model;

	private Perdayrent perdayrent;

	private Metrics metrics;

	private String vin;

	//@JsonProperty("Make")
	private String make;

	private Metadata metadata;

	public String getModel ()
	{
		return model;
	}

	public void setModel (String model)
	{
		this.model = model;
	}

	public Perdayrent getPerdayrent ()
	{
		return perdayrent;
	}

	public void setPerdayrent (Perdayrent perdayrent)
	{
		this.perdayrent = perdayrent;
	}

	public Metrics getMetrics ()
	{
		return metrics;
	}

	public void setMetrics (Metrics metrics)
	{
		this.metrics = metrics;
	}

	public String getVin ()
	{
		return vin;
	}

	public void setVin (String vin)
	{
		this.vin = vin;
	}

	public String getMake ()
	{
		return make;
	}

	public void setMake (String make)
	{
		this.make = make;
	}

	public Metadata getMetadata ()
	{
		return metadata;
	}

	public void setMetadata (Metadata metadata)
	{
		this.metadata = metadata;
	}

	@Override
	public String toString()
	{
		return "[model = "+model+", perdayrent = "+perdayrent+", metrics = "+metrics+", vin = "+vin+", make = "+make+", metadata = "+metadata+"]";
	}
}