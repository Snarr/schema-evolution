package demo;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

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

  // First string = name, int = version
  Map<String, List<Schema>> schemas;

  public SchemaRegistry(CompatibilityType compatibilityType) {
    this.schemas = new HashMap<String, List<Schema>>();

    setCompatibilityStrategy(compatibilityType);
  }

  private void setCompatibilityStrategy(CompatibilityType compatibilityType) {
    switch (compatibilityType) {
        case BACKWARD:
            this.compatibilityStrategy = new BackwardCompatibilityStrategy();
            break;
        case BACKWARD_TRANSITIVE:
            this.compatibilityStrategy = new BackwardTransitiveCompatibilityStrategy();
            break;
        case FORWARD:
            this.compatibilityStrategy = new ForwardCompatibilityStrategy();
            break;
        case FORWARD_TRANSITIVE:
            this.compatibilityStrategy = new ForwardTransitiveCompatibilityStrategy();
            break;
        case FULL:
            this.compatibilityStrategy = new FullCompatibilityStrategy();
            break;
        case FULL_TRANSITIVE:
            this.compatibilityStrategy = new FullTransitiveCompatibilityStrategy();
            break;
        case NONE:
            this.compatibilityStrategy = new NoneCompatibilityStrategy();
            break;
        case INCOMPATIBLE:
          this.compatibilityStrategy = new IncompatibleStrategy();
          break;
        default:
    }
}

  public void putSchema(Schema newSchema) throws SchemaEvolutionException {
    String newSchemaName = newSchema.getName();
    
    // If Schema initialized
    if (schemas.containsKey(newSchemaName)) {
      List<Schema> schemaHistory = schemas.get(newSchemaName);
      compatibilityStrategy.checkCompatibility(newSchema, schemaHistory);
    } else {
      schemas.put(newSchemaName, new ArrayList<Schema>());
    }
    schemas.get(newSchemaName).add(newSchema);
  }
}


