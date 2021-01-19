package FindAString;

import java.util.Random;

public class ADN {
	private char[] genes;
	private double fitness;
	
	public ADN(String genes) {
		this.genes = genes.toCharArray();
	}
	//Create an ADN with random characters
	public static ADN randomCreate(int size) {
		String adnString = "";
		Random rd = new Random();
		for(int i = 0; i < size; i++) {
			adnString += (char) (32 + rd.nextInt(91));
		}
		return new ADN(adnString);
	}
	public int size() {
		return this.genes.length;
	}
	public double calFitness(String goal) {
		int score = 0;
		for(int i = 0; i < genes.length; i++) {
			if(genes[i] == goal.charAt(i)) {
				score++;
			}
		}
		fitness = (score*1.0)/genes.length;
		return fitness;
	}
	
	public ADN crossover(ADN that) {
		String adnString = "";
		int crossPoint = this.size()/2;
		for(int i = 0; i < genes.length; i++) {
			if(i <= crossPoint) {
				adnString += this.genes[i];
			} else adnString += that.genes[i];
		}
		return new ADN(adnString);
	}
	public char[] getGenes() {
		return genes;
	}
	public double getFitness() {
		return fitness;
	}

	public void mutate(double mutateRatio) {
		Random rd = new Random();
		if(Math.random() <= mutateRatio) {
			for(int i = 0; i < genes.length; i++) {
				if(Math.random() >=0.5) this.genes[i] = (char) (32 + rd.nextInt(91));
			}
		}
	}


	

}
