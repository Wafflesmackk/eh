import java.util.*;

public class Mains {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

        GeneticAlgorithm<String> geneticAlgorithm = new GeneticAlgorithm<>(100, 50, 0.01, 10, 100);
        String result = geneticAlgorithm.run(names);

        System.out.println("Best result: " + result);
    }
}

class GeneticAlgorithm<Entity> {
    private final int populationCount;
    private final int crossoverCount;
    private final double mutationProbability;
    private final int pruneCount;
    private final int generationCount;

    public GeneticAlgorithm(int populationCount, int crossoverCount, double mutationProbability, int pruneCount, int generationCount) {
        this.populationCount = populationCount;
        this.crossoverCount = crossoverCount;
        this.mutationProbability = mutationProbability;
        this.pruneCount = pruneCount;
        this.generationCount = generationCount;
    }

    public Entity run(List<Entity> names) {
        List<Entity> population = createInitialPopulation(names);
        Random random = new Random();

        for (int generation = 0; generation < generationCount; generation++) {
            for (int i = 0; i < crossoverCount; i++) {
                Entity parent1 = population.get(random.nextInt(populationCount));
                Entity parent2 = population.get(random.nextInt(populationCount));
                Entity child = doCrossover(parent1, parent2);
                population.add(child);
            }

            for (Entity entity : population) {
                if (random.nextDouble() < mutationProbability) {
                    mutateEntity(entity);
                }
            }

            population.sort((e1, e2) -> Double.compare(calculateFitness(e2), calculateFitness(e1)));
            population = population.subList(0, pruneCount);

            List<Entity> newIndividuals = createInitialPopulation(names);
            population.addAll(newIndividuals);
        }

        return population.get(0);
    }

    private List<Entity> createInitialPopulation(List<Entity> names) {
        List<Entity> population = new ArrayList<>();
        for (int i = 0; i < populationCount; i++) {
            population.add(createRandomEntity(names));
        }
        return population;
    }

    private Entity createRandomEntity(List<Entity> names) {
        return names.get(new Random().nextInt(names.size()));
    }

    private Entity doCrossover(Entity e1, Entity e2) {
        int length = Math.min(e1.toString().length(), e2.toString().length());
        int crossoverPoint = new Random().nextInt(length);
        String childName = e1.toString().substring(0, crossoverPoint) + e2.toString().substring(crossoverPoint);
        return (Entity) childName;
    }

    private void mutateEntity(Entity entity) {
        String name = entity.toString();
        int mutationPoint = new Random().nextInt(name.length());
        char[] nameArray = name.toCharArray();
        nameArray[mutationPoint] = (char) ('a' + new Random().nextInt(26));
        entity = (Entity) new String(nameArray);
    }

    private double calculateFitness(Entity entity) {
        return entity.toString().length();
    }
}
