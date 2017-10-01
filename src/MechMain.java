import java.io.IOException;

public class MechMain {
	public static void main(String[] args)
	{
		PowerScrew optimalScrew = VelocityOptimizer.calculateMaxVelocity(0.025, Math.PI/9, 0.1, 6.0, 100);
		
		/*try {
			TextReader.readFile("L:/Dylan/Programming/Java/Mech Design Assignment/src/PowerScrewsMcMaster.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
