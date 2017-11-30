package GApackage;

import java.io.FileNotFoundException;

public class Main {

    //When changing variables in this list, it is best to Clean and Build the Project before running
    public final static int data = 1; //Enter either 1 or 2 

    //Manually Changable Values
    public final static int popSize = 10;
    public final static double crossoverPoint = 0.5;
    public final static double mutationRate = 0.015;
    public final static int tournamentSize = 5;
    public final static boolean saveBest = true;

    //Automatically Set Values
    public static int conditionSize;
    public static int rulesetSize;
    public static int ruleSize;

    public static void main(String[] args) throws FileNotFoundException {

        setImportantVariables();
        //Create a Solution
        FitnessCalc fitness = new FitnessCalc();
        fitness.collectData();

        fitness.printDataSet();

        //Boot up population 0
        Population pop = new Population(popSize, true);
        //keep going until the evolution process within finds the best
        //Keep the loop  result
        int generationCount = 0;

        while (pop.getFittest().getFitness() < FitnessCalc.getOptFitness() && generationCount < 2000) {
            generationCount++;
            System.out.println("Generation: " + generationCount + "  Fittest: " + pop.getFittest().getFitness());
            pop = EvoAlgorithm.evolvePopulation(pop);

        }
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Fitness: " + pop.getFittest().getFitness());

    }

    //Depending on which number is set at the top, it will automatically change the values to suit
    public static void setImportantVariables() {
        if (data == 1) {
            conditionSize = 5;
            rulesetSize = 32;
            ruleSize = 6;
        } else if (data == 2) {
            conditionSize = 6;
            rulesetSize = 64;
            ruleSize = 7;
        }
    }

}
