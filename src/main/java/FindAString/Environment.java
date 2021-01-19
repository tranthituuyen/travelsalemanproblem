package FindAString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Environment {

	private List<ADN> population;
	private String goal;
	private int populationSize;
	List<ADN> matingPool;
	private double mutateRatio;

	public Environment(String goal, int populationSize, double mutateRatio) {
		this.goal = goal;
		this.populationSize = populationSize;
		this.mutateRatio = mutateRatio;
	}

	// Initialize population with n element
	public void generate() {
		population = new ArrayList<ADN>();
		for (int i = 0; i < populationSize; i++) {
			population.add(ADN.randomCreate(goal.length()));
		}

	}

	public void draw(int limit) {
		for (int loop = 0; loop < limit; loop++) {
			List<ADN> newPopulation = new ArrayList<ADN>();
			// Calculate a fitness of each element. Building a mating pool
			this.selection();
			for (int i = 0; i < populationSize; i++) {
				// Pick two parents with probability using fitness
				ADN parentA = this.randomPick();
				ADN parentB = this.randomPick();
				// Crossover
				ADN child = parentA.crossover(parentB);
				// Mutation by a rarely percent
				child.mutate(mutateRatio);
				// Add new child into new population
				newPopulation.add(child);
				
				System.out.println(child.getGenes());
				if(String.valueOf(child.getGenes()).equals(goal)) return;
			}
			// Replace old population with new population
			population = newPopulation;
		}
	}

	private ADN randomPick() {
		Random rd = new Random();
		return matingPool.get(rd.nextInt(matingPool.size()));
	}

	private void selection() {
		matingPool = new ArrayList<ADN>();
		for (ADN adn : population) {
			int fitnessrate = (int) (adn.calFitness(goal) * 100); // calculate a fitness & times to add into a pool
			for (int i = 0; i < fitnessrate; i++) { // loop for adding into a pool
				matingPool.add(adn);
			}
		}
	}

}
