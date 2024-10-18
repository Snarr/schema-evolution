package demo;

import java.util.Map.Entry;

public class BackwardCompatibilityStrategy implements CompatibilityStrategy {

  @Override
  public void checkCompatibility(Schema newSchema, Schema oldSchema) throws SchemaEvolutionException {
    for (Entry<String, FieldValue> field : newSchema.getAllFields().entrySet()) {
      if (field.getValue().isRequired() && !oldSchema.containsField(field)) {
        throw new SchemaEvolutionException("Cannot add required field.");
      }
    }
  }
}