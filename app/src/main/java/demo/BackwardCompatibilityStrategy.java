package demo;

import java.util.List;
import java.util.Map.Entry;

public class BackwardCompatibilityStrategy implements CompatibilityStrategy {

  @Override
  public void checkCompatibility(Schema newSchema, List<Schema> schemaHistory) throws SchemaEvolutionException {
    Schema latestSchema = schemaHistory.getLast();

    for (Entry<String, FieldValue> field : newSchema.getAllFields().entrySet()) {
      if (field.getValue().isRequired() && !latestSchema.containsField(field)) {
        throw new SchemaEvolutionException("Cannot add required field.");
      }
    }
  }
}