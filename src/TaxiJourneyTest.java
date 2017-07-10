import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TaxiJourneyTest {

	public static void main(String[] args) throws FileNotFoundException {

		String report;
		Scanner in = new Scanner(System.in);


		char oneMore;
		char constructor;
		char dayOrNight;
		char changeChIn;
		char changeChDay;
		char changeChNight;
		char changeChMin;
		char moreTests;
		boolean check=true;
		int checkSum=0;
		int i=0;
		int add;
		int base;
		String construct;
		String location;
		String destination;
		String dOrN;
		String start;
		String end;

		ArrayList<TaxiJourney> list = new ArrayList<TaxiJourney>();
		ArrayList<TaxiJourney> oneWorkingDay = new ArrayList<TaxiJourney>();


		list.add(new TaxiJourney());
		list.add(new TaxiJourney("Birmingham", "Manchester","12:30","14:40",230,"night"));
		for(TaxiJourney a :list)
		{
			if(i==0)
			{
				construct ="default";
				location="Derby";
				destination="Leeds";
				dOrN= "night";
				start="07:20";
				end="08:50";
				add=30;
				base=60;
			}
			else
			{
				construct = "custom";
				location="Leicester";
				destination="London";
				dOrN= "day";
				start="16:40";
				end="18:15";
				add=20;
				base=40;
			}
			System.out.println("*********************************************************************************************************");
			System.out.println("\nChecking the state of the new object created by "+ construct+" constructor upon creation:\n\n"+list.get(i).toString());

			System.out.println("\nChecking the current value of location:"+list.get(i).getLocation());	
			list.get(i).setLocation(location);	
			System.out.println("Changing the value of location...\nExpected output: "+location+", actual output: "+list.get(i).getLocation());

			System.out.println("\nChecking the current value of destination: "+list.get(i).getDestination());
			list.get(i).setDestination(destination);
			System.out.println("Changing the value of destination...\nExpected output: "+destination+", actual output: "+list.get(i).getDestination());

			System.out.println("\nChecking the current value of startTime: "+list.get(i).getStartTime());
			System.out.println("Trying to change the value of startTime by passing a string that is not the right format e.g.\"HH:mm\":");
			list.get(i).setStartTime("6:40");
			System.out.println("Expected output 6:40, actual output: "+list.get(i).getStartTime());

			System.out.println("\nTrying to change the value of startTime by passing another string that is not the right format e.g. from 00:00 to 23:59:");
			list.get(i).setStartTime("56:40");
			System.out.println("Expected output 56:40, actual output: "+list.get(i).getStartTime());
			list.get(i).setStartTime(start);
			System.out.println("\nChanging the value of startTime using a string of valid format...\nExpected output: "+start+", actual output: "+list.get(i).getStartTime());

			System.out.println("\nChecking the current value of endTime: "+list.get(i).getEndTime());
			System.out.println("Trying to change the value of endTime by passing a string that is not the right format e.g.\"HH:mm\":");
			list.get(i).setEndTime("9:23");
			System.out.println("Expected output 9:23, actual output: "+list.get(i).getEndTime());
			System.out.println("\nTrying to change the value of endTime by passing another string that is not the right format e.g. from 00:00 to 23:59:");
			list.get(i).setEndTime("12:87");
			System.out.println("Expected output 12:87, actual output: "+list.get(i).getEndTime());
			list.get(i).setEndTime(end);
			System.out.println("\nChanging the value of endTime using a string of valid format...\nExpected output: "+end+", actual output: "+list.get(i).getEndTime());

			System.out.println("\nChecking the duration of the Taxi Journey in minutes based on the start and end time: "+list.get(i).getDuration()+" minutes");

			System.out.println("\nChecking the current value of initialCharge: "+list.get(i).getInitialCharge());
			list.get(i).setInitialCharge(base+3*add);
			System.out.println("Changing that value..Expected output: "+(base+3*add)+", actual output: "+list.get(i).getInitialCharge());

			System.out.println("\nChecking the current value of chargePerKmDay: "+list.get(i).getChargePerKmDay());
			list.get(i).setChargePerKmDay(base);
			System.out.println("Changing that value..Expected output: "+base+", actual output: "+list.get(i).getChargePerKmDay());

			System.out.println("\nChecking the current value of chargePerKmNight: "+list.get(i).getChargePerKmNight());
			list.get(i).setChargePerKmNight(base+2*add);;
			System.out.println("Changing that value..Expected output: "+(base+2*add)+", actual output: "+list.get(i).getChargePerKmNight());

			System.out.println("\nChecking the current value of chargePerMin: "+list.get(i).getChargePerMin());
			list.get(i).setChargePerMin(base+add);
			System.out.println("Changing that value..Expected output: "+(base+add)+", actual output: "+list.get(i).getChargePerMin());

			System.out.println("\nChecking the value of amPm: "+list.get(i).getAmPm());
			list.get(i).setAmPm(dOrN);
			System.out.println("Changing that value..\nExpected output "+dOrN.toUpperCase()+", actual output: "+a.getAmPm());

			System.out.println("Trying to change amPm to a string different than \"day\" or \"night\"...");
			list.get(i).setAmPm("animal");
			System.out.println("Expected output: ANIMAL, actual output: "+list.get(i).getAmPm());// the actual output would be the last valid value.

			System.out.println("\nChecking currentChargePerKm, based on when the journey took place - during the day ot during the night: "+list.get(i).getCurrentCharge());

			System.out.println("\nChecking the current value of distance: "+list.get(i).getDistance());
			list.get(i).setDistance(base+5*add);
			System.out.println("Changing that value..Expected output: "+(base+5*add)+", actual output: "+list.get(i).getDistance());

			System.out.println("\nCalculating the fare for the journey, based on the formula:"
					+ "\nFare=initialCharge+duration*chargePerMin+distance+currentChargePerKm,\nFare= "+list.get(i).getFare());
			if(i>0)
			{
				System.out.println("\nChecking if the two objects are equal:  "+list.get(i-1).equals(list.get(i)));
			}
			i++;
		}

		//**************************************************************************************************************
		do{
			System.out.println("*************************************************************************************************");
			System.out.println("\n\nDo you want to do some more tests?   Y/N?");
			moreTests=in.next().charAt(0);
		}
		while(moreTests!='y'&&moreTests!='Y'&&moreTests!='n'&&moreTests!='N');
		i=0;// reseting the counter
		if(moreTests=='y'||moreTests=='Y')
		{

			while(check==true)
			{


				do{
					System.out.println("Do you want to create a new Taxi Journey?   Y/N?");
					oneMore=in.next().charAt(0);
				}
				while(oneMore!='y'&&oneMore!='Y'&&oneMore!='n'&&oneMore!='N');


				if(oneMore=='y'|| oneMore=='Y')
				{
					do{
						System.out.println("Which constructor would you like to use?\nType 'd' - for default constructor and 'c' - for the custom one...");
						constructor = in.next().charAt(0);
					}while(constructor!='c'&&constructor!='C'&&constructor!='d'&&constructor!='D');

					if(constructor =='c'||constructor=='C')
					{
						in.nextLine();
						System.out.println("Please type in the start location:..");
						location =in.nextLine();
						System.out.println("Please type in the destination:...");
						destination=in.nextLine();
						System.out.println("Please choose the start time:...");
						start=in.next();
						System.out.println("Please choose journey's end time:...");
						end=in.next();
						System.out.println("Please choose distance in kilometres:..");
						int distance=in.nextInt();
						System.out.println("Please choose time period for the journey (day/night):...");
						String timePeriod=in.next();

						oneWorkingDay.add(new TaxiJourney(location,destination,start,end,distance,timePeriod));

					}
					else if(constructor=='d'||constructor=='D')
					{

						oneWorkingDay.add(new TaxiJourney());
						in.nextLine();
						System.out.println("\nNo start location. Please type your start location:..");
						oneWorkingDay.get(i).setLocation(in.nextLine());
						System.out.println("Your start location is: "+oneWorkingDay.get(i).getLocation());

						System.out.println("\nNo destination. Please type your destination:..");
						oneWorkingDay.get(i).setDestination(in.nextLine());
						System.out.println("Your destination is: "+oneWorkingDay.get(i).getDestination());

						System.out.println("\nNo start time selected.\nPlease type in the start time of the journey:..");
						oneWorkingDay.get(i).setStartTime(in.next());
						System.out.println("You journey's start time is: " +oneWorkingDay.get(i).getStartTime());

						System.out.println("\nNo end time selected.\nPlease type in the end time of the journey:..");
						oneWorkingDay.get(i).setEndTime(in.next());
						System.out.println("You journey's end time is: " +oneWorkingDay.get(i).getEndTime());

						System.out.println("\nChecking journey's distance...\nYour journey's distance is: "+oneWorkingDay.get(i).getDistance());
						System.out.println("Please input new distance in kilometres:");
						oneWorkingDay.get(i).setDistance(in.nextInt());
						System.out.println("The updated distance is: "+oneWorkingDay.get(i).getDistance()+" kilometres");
						in.nextLine();

						do{
							System.out.println("\nChecking journey's time period...\nCurent time period: "+oneWorkingDay.get(i).getAmPm()+"\nDo you want to change it? Y/N");
							dayOrNight = in.next().charAt(0);
						}while(dayOrNight!='y'&&dayOrNight!='Y'&&dayOrNight!='n'&&dayOrNight!='N');
						in.nextLine();
						if(dayOrNight=='y'|| dayOrNight=='Y')
						{
							System.out.println("Please enter new time period...(day/night)");
							oneWorkingDay.get(i).setAmPm(in.nextLine());
						}
						System.out.println("Your journey's time period remains  "+ oneWorkingDay.get(i).getAmPm() );


					}
					System.out.println("\nEstimating duration based on start and end time ...\nYour journey's duration is: "+oneWorkingDay.get(i).getDuration()+" minutes\n");
					System.out.println("The initial charge for this journey in pence is: "+oneWorkingDay.get(i).getInitialCharge());
					do
					{
						System.out.println("Do you want to change the initial charge   (Y/N)?");
						changeChIn=in.next().charAt(0);
					}while(changeChIn !='y'&&changeChIn !='Y'&&changeChIn !='n'&&changeChIn !='N');
					in.nextLine();
					if(changeChIn=='y'||changeChIn=='Y')
					{
						System.out.println("Please input new initial charge in pence...");
						oneWorkingDay.get(i).setInitialCharge(in.nextInt());
						System.out.println("You changed the initial charge to: "+oneWorkingDay.get(i).getInitialCharge()+" pence");
					}
					System.out.println("\nThe charge per Km in pence for day journey is: "+oneWorkingDay.get(i).getChargePerKmDay());
					do
					{
						System.out.println("Do you want to change the  charge   (Y/N)?");
						changeChDay=in.next().charAt(0);
					}while(changeChDay!='y'&&changeChDay !='Y'&&changeChDay !='n'&&changeChDay !='N');
					in.nextLine();
					if(changeChDay=='y'||changeChDay=='Y')
					{
						System.out.println("Please input new day charge in pence...");
						oneWorkingDay.get(i).setChargePerKmDay(in.nextInt());
						System.out.println("You changed the day charge to: "+oneWorkingDay.get(i).getChargePerKmDay()+" pence");
					}
					System.out.println("\nThe charge per Km in pence for night journeys is: "+oneWorkingDay.get(i).getChargePerKmNight());
					do
					{
						System.out.println("Do you want to change the  charge   (Y/N)?");
						changeChNight=in.next().charAt(0);
					}while(changeChNight!='y'&&changeChNight !='Y'&&changeChNight !='n'&&changeChNight !='N');
					in.nextLine();
					if(changeChNight=='y'||changeChNight=='Y')
					{
						System.out.println("Please input new night charge per Km in pence...");
						oneWorkingDay.get(i).setChargePerKmNight(in.nextInt());
						System.out.println("You changed the nigh charge to: "+oneWorkingDay.get(i).getChargePerKmNight()+" pence");
					}
					System.out.println("\nThe curent time period for your journey is: "+oneWorkingDay.get(i).getAmPm());
					System.out.println("That means that the current charge in pence for this journey is: "+oneWorkingDay.get(i).getCurrentCharge());
					System.out.println("\nThe charge per minute in pence is: "+oneWorkingDay.get(i).getChargePerMin());
					do
					{
						System.out.println("Do you want to change the  charge   (Y/N)?");
						changeChMin=in.next().charAt(0);
					}while(changeChMin!='y'&&changeChMin !='Y'&&changeChMin !='n'&&changeChMin !='N');
					in.nextLine();
					if(changeChMin=='y'||changeChMin=='Y')
					{
						System.out.println("Please input new night charge per minute in pence...");
						oneWorkingDay.get(i).setChargePerMin(in.nextInt());
						System.out.println("You changed the charge per minute to: "+oneWorkingDay.get(i).getChargePerMin()+" pence");
					}
					System.out.println("\nEstimating fare for the journey..."
							+ "based on the folowing formula: \nFare=initialCharge + duration * chargePerMin + distance * currentChargePerKm"
							+ "\n\nThe fare for this journey in pence is: "+oneWorkingDay.get(i).getFare());
					System.out.println("\n\nTaxi Journey created !\nOverview of the created journey: \n"+oneWorkingDay.get(i).toString()+"\n");

					i++;
				}
				else
				{
					check = false;



					System.out.println("Thank you !");

					if(i>0)
					{
						i=0;//  reseting the counter
						System.out.println("\n\nPlease choose a file name for the report for the day  (Example: E:/report.txt):..");
						report=in.next();
						PrintWriter pw= new PrintWriter(report);

						for(TaxiJourney tJValue :oneWorkingDay)
						{
							pw.println("\n\nJourney number: "+(i+1)+" overview:\n");
							pw.println(oneWorkingDay.get(i).toString());
							pw.println("\nFare for the journey in pence: "+oneWorkingDay.get(i).getFare());

							checkSum=checkSum+oneWorkingDay.get(i).getFare();
							pw.println("*******************************************************************************************************");
							i++;
						}
						pw.println("*******************************************************************************************************");
						pw.println("Total number of Taxi Journeys for the day: "+oneWorkingDay.size());
						pw.println("\nTotal amount of all fares for the day: "+checkSum);
						pw.close();
					}

				}

			}

			in.close();
		}

	}
}

