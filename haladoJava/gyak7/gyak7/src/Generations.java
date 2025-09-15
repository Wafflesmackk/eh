import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Generations<Entity> {


    public Generations() {}


    public Entity runGeneticAlgorithm(int populationCount, int crossoverCount, double mutationProbability, int generationCount, int pruneCount, List<Entity> population,
        BiFunction<Entity,Entity,Entity> doCrossover, BiFunction<Entity,Double ,Entity> mutateEntity, Function<Entity, Integer> calculateFitness, Supplier<Entity> createRandomEntity
    ){
        for(int i = 0; i < populationCount; i++ ){
            population.add(createRandomEntity.get());
        }
        Random random = new Random();

        for(int generation = 0; generation < generationCount; generation++ ){
           for(int crossover = 0; crossover < crossoverCount; crossover++){
                Entity offspring = doCrossover.apply(population.get(random.nextInt(populationCount)), population.get(random.nextInt(populationCount)));
                population.addLast(offspring);
                populationCount = population.size();
           }
            Stream<Entity> entities = population.stream()
                    .map(entity -> mutateEntity.apply(entity,mutationProbability))
                    .sorted(Comparator.comparingInt(calculateFitness::apply))
                    .limit(pruneCount);

           population = entities.toList();

           while (population.size() < populationCount){
               population.addLast(createRandomEntity.get());
           }
        }
        return population.get(0);
    }




}
