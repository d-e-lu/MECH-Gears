
public class VelocityOptimizer{

	private static double friction = 0.1; // usually around 0.1 - 0.25
	private static double rps = 2500.0/60.0; // revolutions per second
	private static double motorTorque = 2.5; // N*m
	private static double minTorqueRatio = 1.2; // ratio of T_L / T_0
	
	private static double rpsC = rps * Math.PI; // revolutions per second * pi
	private static double forceConst = 12500 * Math.PI; // used for calculations with force
	private static int iterations = 8; // search iterations
	private static double maxVelocity = 0; // max velocity found in search
	
	public static PowerScrew calculateMaxVelocity(double maxDiameter, double maxThreadAngle, double minGearRatio, double maxGearRatio, int searchSpaceLength)
	{	
		double[] diameter = getSearchSpace(0, .025, searchSpaceLength);
		double[] threadAngle = getSearchSpace(0, Math.PI/36, searchSpaceLength);
		double[] gearRatio = getSearchSpace(0.1, 6, searchSpaceLength);
		
		
		int[] searchSpaceIndexes = new int[3];
		
		for(int ii = 0; ii < iterations; ii++)
		{	
			for(int i = 0; i < diameter.length; ++i)
			{			
				for(int j = 0; j < threadAngle.length; ++j)
				{
					for(int k = 0; k < gearRatio.length; ++k)
					{
						double newVelocity = calculateAllToVelocity(diameter[i], threadAngle[j], gearRatio[k]);
						if(newVelocity > maxVelocity)
						{
							maxVelocity = newVelocity;
							searchSpaceIndexes[0] = i;
							searchSpaceIndexes[1] = j;
							searchSpaceIndexes[2] = k;
						}
					}
				}
			}
			diameter = getSearchSpace(diameter[Math.max(0, searchSpaceIndexes[0] - 1)],diameter[Math.min(diameter.length - 1, searchSpaceIndexes[0] + 1)], diameter.length);
			threadAngle = getSearchSpace(threadAngle[Math.max(0, searchSpaceIndexes[1] - 1)],threadAngle[Math.min(threadAngle.length - 1, searchSpaceIndexes[1] + 1)], threadAngle.length);
			gearRatio = getSearchSpace(gearRatio[Math.max(0, searchSpaceIndexes[2] - 1)],gearRatio[Math.min(gearRatio.length - 1, searchSpaceIndexes[2] + 1)], gearRatio.length);
		}
		
		printCalculations(diameter, threadAngle, gearRatio, searchSpaceIndexes, maxVelocity);
		
		return new PowerScrew(diameter[searchSpaceIndexes[0]], threadAngle[searchSpaceIndexes[1]], gearRatio[searchSpaceIndexes[2]], maxVelocity);
	}
	
	private static double calculateAllToVelocity(double diameter, double threadAngle, double gearRatio)
	{
		double velocity = getVelocity(diameter, threadAngle, gearRatio);
		double force = getForce(velocity);
		if(getMinTorqueNeeded(diameter, threadAngle, force) * minTorqueRatio > getTorqueApplied(gearRatio))
		{
			return -1;
		}
		else
		{
			return velocity;
		}
	}
	
	private static double getVelocity(double diameter, double threadAngle, double gearRatio)
	{
		return (rpsC/gearRatio) * diameter * Math.tan(threadAngle);
	}
	
	private static double getForce(double velocity)
	{
		return velocity * forceConst;
	}
	
	private static double getTorqueApplied(double gearRatio)
	{
		return motorTorque * gearRatio;
	}
	
	private static double getMinTorqueNeeded(double diameter, double threadAngle, double force)
	{
		return (friction - Math.tan(threadAngle)) * (force * diameter / 2)/(1+friction * Math.tan(threadAngle));

	}
	
	private static double[] getSearchSpace(double min, double max, int length)
	{
		double diff = max - min;
		double[] array = new double[length];
		for(int i = 0; i < length; ++i)
		{
			array[i] = min + diff * ((double)i/length);
		}
		return array;
	}
	private static void printCalculations(double[] diameter, double[] threadAngle, double[] gearRatio, int[] searchSpaceIndexes, double maxVelocity)
	{
		System.out.println("Diameter: " + diameter[searchSpaceIndexes[0]] + " (m)");
		System.out.println("Thread Angle: " + (threadAngle[searchSpaceIndexes[1]] * 180.0/Math.PI) + " (degrees)");
		System.out.println("Gear Ratio: " + gearRatio[searchSpaceIndexes[2]]);
		System.out.println("Max Velocity: " + maxVelocity * 1000 + " (mm/s)");
	}
	
}






