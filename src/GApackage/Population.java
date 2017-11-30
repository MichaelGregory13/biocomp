package GApackage;

public class Population {
    //Population is a Array for RuleSet.
    
    RuleSet[] population;
    private int ruleSetSize = Main.rulesetSize; //data 1 = 32, data 2 = 64
    
     public Population(int populationSize, boolean initialise){
        population = new RuleSet[populationSize];
        //Initialise the population
        if(initialise){
            //Loop and create individuals
            for (int i=0;i<population.length; i++){
                RuleSet newRuleSet = new RuleSet(ruleSetSize, true);
                saveRuleSet(i,newRuleSet);
            }
        }
    }
     
     public RuleSet getRuleSet(int index){
         return population[index];
     }
     
     public void saveRuleSet(int index, RuleSet ruleSet){
        population[index] = ruleSet;
    }
     
     public RuleSet getFittest(){
        RuleSet fittest = population[0];
        //Loop through the population to find the fittest
        for(int i=0; i<population.length; i++){
            if(fittest.getFitness() <= getRuleSet(i).getFitness()){
                fittest = getRuleSet(i);
            }
        }
        return fittest;
    }
     
    public int size(){
        return population.length;
    }
    
}
