package com.demo.rentalCarsAssignment;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;

public class CarCompanyTest extends BaseTest   
{
	@Test
    public void blueTeslaPositiveTest1()
    {
		//Arrange
		List<CarDetail> cars = RentalCarCompany.getResponseFromFile("target/blueTeslaPositiveData.json");
		String expectedResult = "09AGHY64352JITEG98K, Notes: Scratches on the back side of the car";
		
		//Act
		String blueTeslas = RentalCarCompany.getBlueTeslas(cars);
		
		//Assert
		assertEquals(blueTeslas, expectedResult);

    }
	
	@Test
    public void blueTeslaNegativeTest1()
    {
		List<CarDetail> cars = RentalCarCompany.getResponseFromFile("target/blueTeslaNegativeData.json");
		
		String blueTeslas = RentalCarCompany.getBlueTeslas(cars);
		
		assertEquals(blueTeslas, "");

    }
	
	@Test
    public void lowestPriceTest1()
    {
		List<CarDetail> cars = RentalCarCompany.getResponseFromFile("target/lowestPriceData1.json");
		boolean isVINPresent = false;
		String expectedVIN = "09AGHY64352JITEG98K";	
		
		//Act
		List<Car> lowestCostCars = RentalCarCompany.getLowestCostCars(cars, false); //pass false
		
		//First, assert count
		assertEquals(lowestCostCars.size(), 1);
		
		for(Car car : lowestCostCars)
		{
			if(car.getVin().equals(expectedVIN))
			{
				isVINPresent = true;
				break;
			}
		}
		
		assertEquals(isVINPresent, true);

    }
	
	@Test
    public void lowestPriceWithDiscountTest1()
    {
		List<CarDetail> cars = RentalCarCompany.getResponseFromFile("target/lowestPriceWithDiscountData1.json");
		boolean isInvalidVINPresent = false;
		List<String> expectedVIN = new ArrayList<String>(Arrays.asList("39AGHY64352JITEG98K", "19AGHY64352JITEG98K"));	
		
		//Act
		List<Car> lowestCostCars = RentalCarCompany.getLowestCostCars(cars, true); // pass true
		
		//First, assert count
		assertEquals(lowestCostCars.size(), 2);
		
		
		for(Car car : lowestCostCars)
		{
			if(expectedVIN.contains(car.getVin()) == false)
			{
				isInvalidVINPresent = true;
				break;
			}
		}
		
		//Then, assert if both VINs present
		assertEquals(isInvalidVINPresent, false);

    }
	
	@Test
	public void highestRevenueCarTest1()
    {
		List<CarDetail> cars = RentalCarCompany.getResponseFromFile("target/highestRevenueCarData1.json");
		boolean isVINPresent = false;
		String expectedVIN = "39AGHY64352JITEG98K";	
		
		//Act
		List<Car> highestRevenueCars = RentalCarCompany.getHighestRevenueCars(cars);
		
		//First, assert count
		assertEquals(highestRevenueCars.size(), 1);
		
		for(Car car : highestRevenueCars)
		{
			if(car.getVin().equals(expectedVIN))
			{
				isVINPresent = true;
				break;
			}
		}
		
		//Then, assert if VIN returned
		assertEquals(isVINPresent, true);

    }

   
}
