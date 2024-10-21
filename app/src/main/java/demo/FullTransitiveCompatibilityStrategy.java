package demo;

import java.util.List;

public class FullTransitiveCompatibilityStrategy implements CompatibilityStrategy {

  @Override
  public void checkCompatibility(Schema newSchema, List<Schema> schemaHistory) throws SchemaEvolutionException {
    FullCompatibilityStrategy fullCompatibilityStrategy = new FullCompatibilityStrategy();

    for (Schema oldSchema : schemaHistory) {
      fullCompatibilityStrategy.checkCompatibility(newSchema, List.of(oldSchema));
    }
  }  
}
