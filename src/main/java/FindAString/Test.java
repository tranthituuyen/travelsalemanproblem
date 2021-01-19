package FindAString;

public class Test {

	public static void main(String[] args) {
		String goal = "Xinchao";
		int populationSize = 1000;
		double mutateRatio = 0.01;
		int limit = 100000;
		
		Environment environment = new Environment(goal, populationSize, mutateRatio);
		environment.generate();
		environment.draw(limit);

	}

}
