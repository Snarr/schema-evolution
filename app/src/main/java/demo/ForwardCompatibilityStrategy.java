package demo;

import java.util.Map.Entry;
import java.util.List;

public class ForwardCompatibilityStrategy implements CompatibilityStrategy {

  @Override
  public void checkCompatibility(Schema newSchema, List<Schema> schemaHistory) throws SchemaEvolutionException {
    Schema latestSchema = schemaHistory.getLast();  

    for (Entry<String, FieldValue> field : latestSchema.getAllFields().entrySet()) {
      if (field.getValue().isRequired() && !newSchema.containsField(field)) {
        throw new SchemaEvolutionException("Cannot delete required field.");
      }
    }
  }
}
