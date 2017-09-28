
public class PowerScrew {
	
	private double diameter;
	private double threadAngle;
	private double gearRato;
	private double velocity;
	
	public PowerScrew(double d, double tAngle, double gRatio, double velocity)
	{
		this.diameter = d;
		this.threadAngle = tAngle;
		this.gearRato = gRatio;
		this.velocity = velocity;
	}
	
	
	private static double powerScrewEfficiency(double threadAngle, double friction)
	{
		double tanAngle = Math.tan(threadAngle);
		
		return tanAngle * (1 - (friction * tanAngle)) / (tanAngle + friction);
	}
	
	private static double nominalShearStress(double torque, double diameter)
	{
		return 16 * torque / (Math.PI * Math.pow(diameter, 3));
	}
	
	private static double transverseShearStress(double force, double diameter, int engagedThreads, double pitch)
	{
		return 3 * force / (Math.PI * diameter * engagedThreads * pitch);
	}
	
	private static double nominalNormalStress(double force, double diameter)
	{
		return 4 * force / (Math.PI * Math.pow(diameter, 2));
	}
	
	private static double bendingNormalStress(double force, double diameter, int engagedThreads, double pitch)
	{
		return 6 * force / (Math.PI * diameter * engagedThreads * pitch);
	}
	
	private static double bearingNormalStress(double force, double diameter, int engagedThreads, double pitch)
	{
		return - 2 * force / (Math.PI * diameter * engagedThreads * pitch);
	}

}
