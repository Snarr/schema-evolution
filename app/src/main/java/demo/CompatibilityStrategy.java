package demo;

import java.util.List;

public interface CompatibilityStrategy {
  public void checkCompatibility(Schema newSchema, List<Schema> schemaHistory) throws SchemaEvolutionException;
}
