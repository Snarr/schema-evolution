package demo;

import java.util.Map.Entry;
import java.util.List;

public class FullCompatibilityStrategy implements CompatibilityStrategy {

  @Override
  public void checkCompatibility(Schema newSchema, List<Schema> schemaHistory) throws SchemaEvolutionException {
    Schema latestSchema = schemaHistory.getLast();

    for (Entry<String, FieldValue> field : newSchema.getAllFields().entrySet()) {
      if (field.getValue().isRequired() && !latestSchema.containsField(field)) {
        throw new SchemaEvolutionException("Cannot add required field.");
      }
    }

    for (Entry<String, FieldValue> field : latestSchema.getAllFields().entrySet()) {
      if (field.getValue().isRequired() && !newSchema.containsField(field)) {
        throw new SchemaEvolutionException("Cannot remove required field.");
      }
    }
  }
  
}
