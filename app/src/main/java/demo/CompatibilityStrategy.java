package demo;

public interface CompatibilityStrategy {
  public void checkCompatibility(Schema newSchema, Schema oldSchema) throws SchemaEvolutionException;
}
