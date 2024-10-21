package demo;

import java.util.List;

public class BackwardTransitiveCompatibilityStrategy implements CompatibilityStrategy {

  @Override
  public void checkCompatibility(Schema newSchema, List<Schema> schemaHistory) throws SchemaEvolutionException {
    BackwardCompatibilityStrategy backwardCompatibilityStrategy = new BackwardCompatibilityStrategy();

    for (Schema oldSchema : schemaHistory) {
      backwardCompatibilityStrategy.checkCompatibility(newSchema, List.of(oldSchema));
    }
  }  
}
