package demo;

import java.util.List;
import java.util.Map.Entry;

public class BackwardCompatibilityStrategy implements CompatibilityStrategy {

  @Override
  public void checkCompatibility(Schema newSchema, List<Schema> schemaHistory) throws SchemaEvolutionException {
    Schema latestSchema = schemaHistory.getLast();


    for (Entry<String, FieldValue> field : newSchema.getAllFields().entrySet()) {
      boolean fieldIsRequired = field.getValue().isRequired();
      boolean fieldIsNew = !latestSchema.containsField(field);
      if (fieldIsRequired && fieldIsNew) {
        
        boolean fieldHasAlias = field.getValue().getAlias().isPresent();
        if (!fieldHasAlias) {
          throw new SchemaEvolutionException("Cannot add required field.");
        }

        boolean aliasReferenceExists = latestSchema.hasField(field.getValue().getAlias().get());
        if (!aliasReferenceExists) {
          throw new SchemaEvolutionException("Invalid alias: " + field.getValue().getAlias().get());
        }
      }
    }
  }
}