package com.demo.rentalCarsAssignment;

//import static io.restassured.RestAssured.given;

//import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;

public class RentalCarCompany 
{
	
	public static void main( String[] args )
	{
			//Test method
			List<CarDetail> carDetails = RentalCarCompany.getResponseFromFile("target/sampleInput.json");
			
			String problem1Result = getBlueTeslas(carDetails); 
			List<Car> problem2result1 = getLowestCostCars(carDetails, false);
			List<Car> problem2result2 = getLowestCostCars(carDetails, true);
			List<Car> problem3Result = getHighestRevenueCars(carDetails);
			
			
	}
	
	/*
	 * Mock Method which gets API response from file and return POJO
	 * 
	 * Response : Collection of CarDetail
	 */
	public static List<CarDetail> getResponseFromFile(String fileName)
	{	
		
		List<CarDetail> carDetails = null;
		String json = RentalCarCompany.readFileAsString(fileName);
		JsonPath js = new JsonPath(json);
		//Deserialize JSON to POJO
		carDetails = js.getList("", CarDetail.class);

		return carDetails;
	}
	
	/*
	 * Read from file.
	 */
	public static String readFileAsString(String fileName)
	  { 
	    String data = ""; 
	    try {
			data = new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException e) {
			// TODO 
			e.printStackTrace();
		} 
	    return data; 
	  } 
	

	/*
	 * Problem 1: Print all the blue Teslas received in the web service response. Also print the notes
	 * Printing VIN and corresponding Notes
	 */
	public static String getBlueTeslas(List<CarDetail> cars){

		StringBuilder sb= new StringBuilder();
		String tesla = "Tesla";
		String blue = "blue";

		for(CarDetail carDetail : cars)
		{
			Car car = carDetail.getCar();
			//Filter and append details
			if(car.getMake().equalsIgnoreCase(tesla) && car.getMetadata().getColor().equalsIgnoreCase(blue))
			{
				if (sb.length() > 0)
				{
					sb.append("; ");
				}

				sb.append(car.getVin()); //VIN
				sb.append(", Notes: ");
				sb.append(car.getMetadata().getNotes()); //Notes
			}
		}

		return sb.toString();
	}

	/*
	 * Return all cars which have the lowest per day rental cost for both cases:
	 *    a. Price only
	 *    b. Price after discounts  
	 *    
	 *  Use Boolean parameter to distinguish whether to include discounts or not 			
	 */
	public static List<Car> getLowestCostCars(List<CarDetail> cars, Boolean includeDiscount){

		BigDecimal lowestPrice = new BigDecimal(Float.MAX_VALUE);

		List<Car> lowestPricedCars = new ArrayList<Car>();

		for(CarDetail carDetail : cars)
		{
			Car car = carDetail.getCar();
			BigDecimal carPrice = null;

			if(includeDiscount)
			{
				//subtract discount also
				carPrice = (new BigDecimal(car.getPerdayrent().getPrice())).subtract( new BigDecimal(car.getPerdayrent().getDiscount()) );
			}
			else
			{
				carPrice = new BigDecimal(car.getPerdayrent().getPrice());
			}


			if(carPrice.compareTo(lowestPrice) == -1)
				/*carPrice < lowestPrice -> -1
				carPrice = lowestPrice -> 0
				carPrice > lowestPrice -> 1*/
			{
				lowestPrice = carPrice;
				lowestPricedCars.clear(); 
				lowestPricedCars.add(car);
			}
			else if(carPrice.compareTo(lowestPrice) == 0) //if two car has same low price
			{
				lowestPricedCars.add(car);
			}
		}

		return lowestPricedCars;
	}

	/*
	 * Find the highest revenue generating car. year over year maintenance cost + depreciation is the total expense per car 
	 * for the full year for the rental car company.
	 * The objective is to find those cars that produced the highest profit in the last year
	 * 
	 * Return a list so if there are multiple cars having same highest revenue
	 * Income = (price - discount) * yeartodateRentals
	 * Expense = depreciation and maintenance
	 * Revenue = Income - Expense
	 */
	public static List<Car> getHighestRevenueCars(List<CarDetail> cars){

		//Ignore cars if revenue less than 0
		BigDecimal highestRevenue = new BigDecimal(0);

		List<Car> lowestPricedCars = new ArrayList<Car>();

		for(CarDetail carDetail : cars)
		{
			Car car = carDetail.getCar();

			BigDecimal carPrice = (new BigDecimal(car.getPerdayrent().getPrice())).subtract(new BigDecimal(car.getPerdayrent().getDiscount()));
			BigDecimal totalIncome = carPrice.multiply(new BigDecimal(car.getMetrics().getRentalcount().getYeartodate()));
			BigDecimal totalExpenses = new BigDecimal(car.getMetrics().getDepreciation()).add(new BigDecimal(car.getMetrics().getYoymaintenancecost()));

			BigDecimal revenue = totalIncome.subtract(totalExpenses);

			if(highestRevenue.compareTo(revenue) == -1)
			{
				lowestPricedCars.clear();
				lowestPricedCars.add(car);
				highestRevenue = revenue;
			}
			else if(highestRevenue.compareTo(revenue) == 0) // if multiple cars have same revenue
			{
				lowestPricedCars.add(car);
			}
		}

		return lowestPricedCars;
	}
}
