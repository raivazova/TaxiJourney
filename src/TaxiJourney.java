
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;



/**
 *
 * A TaxiJourney has a location, destination, start time and end time.
 * There is an estimated duration (endTime - startTime) of the journey, as well as initialCharge, 
 * chargePerMinute, distance and currentChargePerKm which is either of chargePerKmDay or chargePerKmNight
 * depending on when the journey took place.
 * initialCharge - the initial charge for the journey.
 * currentChargePerKm is the current charge and depends on whether the journey is in day or night time, 
 * chargePerKmDay - will be assigned to the currentChargePerKm if the journey took place during the day,
 * chargePerKmNight - will be assigned to the currentChargePerKm if the journey took place during the night.
 * 
 * The class has a getFare method which calculates
 * the charge for the journey, using the formula :
 * Fare = initialCharge+chargePerMIn*duration+currentChargePerKm*distance;
 *
 *
 * @author raivazova
 *  */
public class TaxiJourney {





	// fields
	private String location;
	private String destination;
	private int initialCharge;
	private int chargePerKmDay;
	private int chargePerKmNight;
	private int currentChargePerKm;
	private int chargePerMin;
	private String startTime;
	private String endTime;
	private String amPm;
	private int duration; //in minutes
	private int distance; // in km

	private enum DayOrNight {
		DAY, NIGHT}




	//constructors

	/**
	 *Default constructor. Creates a TaxiJourney and sets location and destination to empty strings, initialCharge to default value of 240 pence, 
	 *chargePerKmDay to default value of 50 pence, chargePerKmNight to default value of 70 pence, 
	 *chargePerMin to default value of 40pence, start time to default value of "00:00", endTime to default value of "00:00",
	 *Duration and distance are set to 0. 
	 *amPm (the time of the day)is set to default value of "day".
	 * 
	 *
	 */
	public TaxiJourney()
	{
		location="";
		destination="";
		initialCharge = 240;
		chargePerKmDay=50;
		chargePerKmNight = 70;
		chargePerMin=40;
		startTime="00:00";
		endTime="00:00";
		duration=0;
		distance=0;
		amPm="day";
	}


	/**
	 *Custom constructor. Creates a TaxiJourney with the given location, destination, distance and amPm.
	 *startTime and endTime are set using the setStartTime and setEndTime methods, the reason is to assure that the valid string format is used e.g "HH:mm"
	 *Sets initialCharge to default value of 240 pence, 
	 *chargePerKmDay to default value of 50 pence, chargePerKmNight to default value of 70 pence, 
	 *chargePerMin to default value of 40pence.
	 *
	 *Estimates duration of the journey by subtracting startTime form endTime.
	 *
	 * @param location The start location of the journey.
	 * @param destination The destination of the journey
	 * @param startTime The time when the journey started
	 * @param endTime The finish time of the journey
	 * @param distance The distance between location and destination
	 * @param amPm The time of the day when the journey took place ( during the day or the night)
	 */
	public TaxiJourney(String location, String destination, String startTime, String endTime,int distance, String amPm)
	{
		this.location=location;
		this.destination=destination;
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.distance=distance;
		this.amPm=amPm;
		duration=this.getDuration();
		initialCharge = 240;
		chargePerKmDay=50;
		chargePerKmNight = 70;
		chargePerMin=40;
	}


	//methods

	/**
	 * Returns the start location of the journey.
	 * @return the start location of the journey.
	 */
	public String getLocation() {
		return location;
	}

	

	/**
	 *Sets the start location to the given value.
	 * @param location the start location of the taxi journey.
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	
	
	/**
	 *Returns the destination of the journey.
	 * @return the destination of the journey.
	 */
	public String getDestination() {
		return destination;
	}

	
	/**
	 *Sets the destination of the taxi journey to the given value.
	 * @param destination the destination of the taxi journey.
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	

	/**
	 *Returns the initialCharge of taxi taxi journey.
	 * @return the initialCharge of taxi taxi journey.
	 */
	public int getInitialCharge() {
		return initialCharge;
	}

	
	
	/**
	 *Sets the initialCharge to the given value.
	 * @param initialCharge the charge added to the taxi journey fare no matter the duration and the distance of the journey..
	 */
	public void setInitialCharge(int initialCharge) {
		this.initialCharge = initialCharge;
	}

	/**
	 *Returns the chargePerKmDay of the taxi journey.
	 * @return the chargePerKmDay of the taxi journey.
	 */
	public int  getChargePerKmDay() {
		return chargePerKmDay;
	}

	/**
	 *Sets chargePerKmDay to the given value.
	 * @param chargePerKmDay the charge per kilometre for a day journey. 
	 */
	public void setChargePerKmDay(int  chargePerKmDay) {
		this.chargePerKmDay = chargePerKmDay;
	}

	/**
	 * Returns chargePerKmNight of the taxi journey.
	 *
	 * @return chargePerKmNight of the taxi journey.
	 */
	public int  getChargePerKmNight() {
		return chargePerKmNight;
	}

	/**
	 *Sets chargePerKmNight to the given value.
	 * @param chargePerKmNight the charge per kilometre for a night journey.
	 */
	public void setChargePerKmNight(int chargePerKmNight) {
		this.chargePerKmNight = chargePerKmNight;
	}

	/**
	 *Returns the startTime of the taxi journey.
	 * @return the startTime of the taxi journey.
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 *Sets startTime to the given value.
	 *The method is checking if the string passed to startTime can be converted to an hour between 00:00 and 23:59.
	 *If using the default constructor and startTime is set, but endTime is still not set, 
	 *and the toString method is called it would show that duration is 0. 
	 *Only after the endTime is also set then the duration will be estimated.
	 *
	 * @param startTime the start time of the taxi journey.
	 */
	public void setStartTime(String startTime) {

		if(startTime.length()==5&&Integer.parseInt(startTime.substring(0,2))>=0&&Integer.parseInt(startTime.substring(0,2))<24
				&&Integer.parseInt(startTime.substring(3,5))>=0 && Integer.parseInt(startTime.substring(3,5))<60)
		{
			this.startTime = startTime;
		}

	}


	/**
	 *Returns the endTime of the taxi JOurney.
	 * @return the endTime of the taxi JOurney.
	 */
	public String getEndTime() {
		return endTime;
	}


	/**
	 * Sets endTime to the given value.
	 * The method is checking if the string passed to endTime can be converted to an hour between 00:00 and 23:59.
	 * The endTime passed as parameter represents an hour that has to be greater then startTime.
	 * Once endTime is set, the duration is estimated. 
	 * That way if endTime is set and the toString method is called it would show the accurate duration.
	 * 
	 * @param endTime the end time of the taxi journey - it should be later then start time.
	 */
	public void setEndTime(String endTime) {
		if(endTime.length()==5&&Integer.parseInt(endTime.substring(0,2))>=0 && Integer.parseInt(endTime.substring(0,2))<24
				&& Integer.parseInt(endTime.substring(3,5))>=0 && Integer.parseInt(endTime.substring(3,5))<60)
		{
			if((Integer.parseInt(endTime.substring(0,2))== Integer.parseInt(startTime.substring(0,2))
					&& Integer.parseInt(endTime.substring(3,5))> Integer.parseInt(startTime.substring(3,5)))
					|| (Integer.parseInt(endTime.substring(0,2))> Integer.parseInt(startTime.substring(0,2))))
			{
				
				this.endTime=endTime;
				duration=this.getDuration();
			}

		}
		
	}


	/**
	 * Returns the charge per minute of the taxi journey.
	 *
	 * @return chargePerMin of the taxi journey.
	 */
	public int getChargePerMin() {
		return chargePerMin;
	}


	/**
	 *Sets timePerMin to the given value.
	 * @param chargePerMin the charge per minute of the taxi journey.
	 */
	public void setChargePerMin(int  chargePerMin) {
		this.chargePerMin = chargePerMin;
	}


	/**
	 *Returns the distance of the taxi journey.
	 * @return the distance of the taxi journey.
	 */
	public int getDistance() {
		return distance;
	}


	/**
	 *Sets the distance to the given value.
	 * @param distance the distance of the taxi journey.
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}


	
	/**
	 * Returns amPm (night or day) of the taxi journey and converts it to upper case.
	 *
	 * @return amPm (night or day) of the taxi journey and converts it to upper case.
	 */
	public String getAmPm() {

		return amPm.toUpperCase();
	}

	

	/**
	 *Sets amPm to the given value. The only two valid strings are "day" or "night".
	 * @param amPm the time period in which the journey took place (day or night).
	 */
	public void setAmPm(String amPm) {
		if(amPm.toUpperCase().equals("DAY")||amPm.toUpperCase().equals("NIGHT"))
		{
			this.amPm = amPm;
		}
		
	}
	
	


	/**
	 * Returns the currentChargePerKM of the taxi journey, depending on the time period (day or night) that the journey took place in. 
	 * @return the currentChargePerKM of the taxi journey.
	 */
	public int getCurrentCharge() {
		DayOrNight whatTime = DayOrNight.valueOf(this.amPm.toUpperCase());
		switch(whatTime)
		{
		case DAY:
			currentChargePerKm = chargePerKmDay;
			break;
		case NIGHT:
			currentChargePerKm=chargePerKmNight;
			break;
		}
		return currentChargePerKm;
	}





	/**
	 * Returns the duration of the journey.
	 * Checks if startTime end endTime are the right format e.g. "HH:mm".
	 * Parses startTime and endTime to variables of type Date.
	 * Duration is estimated by subtracting etartTime form endTime.
	 * 
	 * @return the duration of the taxi journey.
	 */
	public int getDuration()
	{
		SimpleDateFormat format=new SimpleDateFormat("HH:mm");

		try {
			Date start = format.parse(startTime);
			Date end = format.parse(endTime);
			long durMilSec=end.getTime()-start.getTime();
			duration = (int) TimeUnit.MILLISECONDS.toMinutes(durMilSec);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return duration;
	}



	/**
	 *Returns the calculated fare for the taxi journey.
	 *The fare is calculated using the following formula:
	 *Fare = initialCharge+distance*currentChargePerKm+duration*chargePerMin.
	 *
	 * @return the calculated fare for the taxi journey.
	 */
	public int getFare()
	{
		this.getCurrentCharge();
		return  initialCharge+duration*chargePerMin+currentChargePerKm*distance;
	}

	
	/**
	 * Returns a textual representation of the taxi journey.
	 */
	@Override
	public String toString()
	{
		return "TaxiJourney: [ location: "+ location +", destination: " +destination+", amPm: "+ amPm.toUpperCase()
				+",\nstartTime: "+startTime+", endTime: "+endTime+", duration: "+duration
				+",distance: "+distance+",\ncurrentChargePerKm: "+this.currentChargePerKm
				+", chargePerMin: "+chargePerMin+", initialCharge: "+initialCharge
				+",\nchargePerkmDay: "+chargePerKmDay+", chargePerKmNight: "+chargePerKmNight+"]";
	}


	/**
	 * Compares this Taxi Journey to the specified object.
	 * Returns true if and only if the argument is not null and is an TaxiJourney object that has the same
	 * location, destination, startTime, endTime, initialCharge, chargePerKmDay, chargePerKmNight, 
	 * currentChargePerKm, chargePerMin, amPm, distance and duration as this object.
	 * 
	 * @param obj the object to compare this OrderLine against.
	 * 
	 * @return true if the given object represents a TaxiJourney equivalent to this taxi journey, false otherwise.
	 * 
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj ==null || this.getClass()!= obj.getClass())
		{
			return false;
		}
		
		TaxiJourney other=(TaxiJourney) obj;
		
		return this.location.equals(other.location) && this.destination.equals(other.destination) 
				&& this.initialCharge==other.initialCharge && this.chargePerKmDay==other.chargePerKmDay
				&&this.chargePerKmNight==other.chargePerKmNight&&this.currentChargePerKm==other.currentChargePerKm
				&&this.chargePerMin==other.chargePerMin&&this.startTime.equals(other.startTime)&&this.endTime.equals(other.endTime)
				&&this.amPm.toUpperCase().equals(other.amPm.toUpperCase())&&this.distance==other.distance&&this.duration==other.duration;
	}

}
