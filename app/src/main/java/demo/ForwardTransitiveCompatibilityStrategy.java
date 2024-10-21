package demo;

import java.util.List;

public class ForwardTransitiveCompatibilityStrategy implements CompatibilityStrategy {

  @Override
  public void checkCompatibility(Schema newSchema, List<Schema> schemaHistory) throws SchemaEvolutionException {
    ForwardCompatibilityStrategy forwardCompatibilityStrategy = new ForwardCompatibilityStrategy();

    for (Schema oldSchema : schemaHistory) {
      forwardCompatibilityStrategy.checkCompatibility(newSchema, List.of(oldSchema));
    }
  }  
}
