package GApackage;

public class EvoAlgorithm {
    static int ruleSetSize = Main.rulesetSize;
    
    //Extensions of the defined variables in main
    private static final double crossoverPoint = Main.crossoverPoint;
    private static final double mutationRate = Main.mutationRate;
    private static final int tournamentSize = Main.tournamentSize;    //Extensions of the defined variables in main
    private static final boolean saveBest = Main.saveBest;
    
    //Public methods
    //Process set to evolve the population
    public static Population evolvePopulation (Population pop){
        Population newPopulation = new Population(pop.size(), false);
        
        //to keep the best set saveBest to 1 in the main
        if(saveBest){
            newPopulation.saveRuleSet(0, pop.getFittest());
        }
        
        int saveBestOffset;
        if(saveBest){
            saveBestOffset =1;
        }else{
            saveBestOffset =0;
        }
        //Loop over the population size and create a new individuals
        
        //Crossover Main
        for(int i = saveBestOffset; i < pop.size(); i++){
            RuleSet ruleSet1 = tournamentSelection(pop);
            RuleSet ruleSet2 = tournamentSelection(pop);
            RuleSet newRuleSet = crossover(ruleSet1, ruleSet2);
            newPopulation.saveRuleSet(i, newRuleSet);
        }
        
        //Mutation
        for(int i = saveBestOffset; i <newPopulation.size(); i++){
            newPopulation.population[i] =  mutate(newPopulation.getRuleSet(i));
        }
        return newPopulation;
    }
    
    //Crossover function
    private static RuleSet crossover(RuleSet ruleSet1, RuleSet ruleSet2){
        RuleSet newSol = new RuleSet(ruleSetSize, false);
        //Loop through genes
        for(int i = 0; i < ruleSet1.size(); i++){
            //Crossover
            if(Math.random() <= crossoverPoint){
                newSol.saveRule(i, ruleSet1.getRule(i)); 
            }else { 
                newSol.saveRule(i, ruleSet2.getRule(i));
            }
        }
        return newSol;
    }
    
    //Mutation of the rule set
    private static RuleSet mutate(RuleSet ruleSet){
        //Loop through genes
        for(int i=0; i<ruleSet.size(); i++){
            if(Math.random() <= mutationRate){
                //Mutate random gene
                Rule mutant = new Rule();
                mutant.generateRule();
                ruleSet.saveRule(i, mutant);
            }
        }
        return ruleSet;
    }
    
    private static RuleSet tournamentSelection(Population pop){
        //Create a population for the tournement
        Population tournament = new Population(tournamentSize, false);
        //Fill the tournaments slots with random rules
        for(int i=0; i < tournamentSize; i++){
            int randomID = (int) (Math.random() * pop.size());
            tournament.saveRuleSet(i, pop.getRuleSet(randomID));
        }
        //Get the fittest
        RuleSet fittest = tournament.getFittest();
        return fittest;
    }
        
}
    

