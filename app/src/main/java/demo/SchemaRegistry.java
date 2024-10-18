package demo;

import java.util.HashMap;
import java.util.Map;

public class SchemaRegistry {
  public enum CompatibilityType {
    BACKWARD,
    BACKWARD_TRANSITIVE,
    FORWARD,
    FORWARD_TRANSITIVE,
    FULL,
    FULL_TRANSITIVE,
    NONE,
    INCOMPATIBLE,
  }

  CompatibilityStrategy compatibilityStrategy;
  Map<String, Schema> schemas;

  public SchemaRegistry(CompatibilityType compatibilityType) {
    this.schemas = new HashMap<>();

    setCompatibilityStrategy(compatibilityType);
  }

  private void setCompatibilityStrategy(CompatibilityType compatibilityType) {
    switch (compatibilityType) {
        case BACKWARD:
            this.compatibilityStrategy = new BackwardCompatibilityStrategy();
            break;
        case BACKWARD_TRANSITIVE:
            break;
        case FORWARD:
            this.compatibilityStrategy = new ForwardCompatibilityStrategy();
            break;
        case FORWARD_TRANSITIVE:
            break;
        case FULL:
            this.compatibilityStrategy = new FullCompatibilityStrategy();
            break;
        case FULL_TRANSITIVE:
        case NONE:
        case INCOMPATIBLE:
          this.compatibilityStrategy = new IncompatibleStrategy();
          break;
        // Add cases for other strategies
        default:
    }
}

  public void putSchema(Schema newSchema) throws SchemaEvolutionException {
    String newSchemaName = newSchema.getName();

    if (schemas.containsKey(newSchemaName)) {
      Schema oldSchema = schemas.get(newSchemaName);
      compatibilityStrategy.checkCompatibility(newSchema, oldSchema);
    }
    schemas.put(newSchema.getName(), newSchema);
  }
}


