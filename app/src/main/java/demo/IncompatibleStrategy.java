package demo;

import java.util.List;

public class IncompatibleStrategy implements CompatibilityStrategy {

  @Override
  public void checkCompatibility(Schema newSchema, List<Schema> schemaHistory) throws SchemaEvolutionException {
    throw new SchemaEvolutionException("Unimplemented.");
  }
  
}
