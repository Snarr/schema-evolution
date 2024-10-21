package demo;

import java.util.List;

public class NoneCompatibilityStrategy implements CompatibilityStrategy {

  @Override
  public void checkCompatibility(Schema newSchema, List<Schema> schemaHistory) throws SchemaEvolutionException {
    
  }  
}
