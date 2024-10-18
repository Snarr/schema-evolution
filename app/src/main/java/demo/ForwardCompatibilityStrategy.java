package demo;

import java.util.Map.Entry;

public class ForwardCompatibilityStrategy implements CompatibilityStrategy {

  @Override
  public void checkCompatibility(Schema newSchema, Schema oldSchema) throws SchemaEvolutionException {
    for (Entry<String, FieldValue> field : oldSchema.getAllFields().entrySet()) {
      if (field.getValue().isRequired() && !newSchema.containsField(field)) {
        throw new SchemaEvolutionException("Cannot delete required field.");
      }
    }
  }
}
