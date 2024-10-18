package demo;

public class IncompatibleStrategy implements CompatibilityStrategy {

  @Override
  public void checkCompatibility(Schema newSchema, Schema oldSchema) throws SchemaEvolutionException {
    throw new SchemaEvolutionException("Unimplemented.");
  }
  
}
