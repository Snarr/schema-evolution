package demo;

import java.util.Map.Entry;

public class FullCompatibilityStrategy implements CompatibilityStrategy {

  @Override
  public void checkCompatibility(Schema newSchema, Schema oldSchema) throws SchemaEvolutionException {
    for (Entry<String, FieldValue> field : newSchema.getAllFields().entrySet()) {
      if (field.getValue().isRequired() && !oldSchema.containsField(field)) {
        throw new SchemaEvolutionException("Cannot add required field.");
      }
    }

    for (Entry<String, FieldValue> field : oldSchema.getAllFields().entrySet()) {
      if (field.getValue().isRequired() && !newSchema.containsField(field)) {
        throw new SchemaEvolutionException("Cannot remove required field.");
      }
    }
  }
  
}
