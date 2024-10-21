package demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import demo.SchemaRegistry.CompatibilityType;

public class CompatibilityTest {

  // private final Schema avro6 = new SchemaBuilder("avro_schema")
  //   .putField("identifier_new", FieldType.STRING)
  //   .build();

  @BeforeEach
  public void setup() {
  }

  @Test
  public void backwardCompatibiltyStrategy() {
    assertEquals(true, canEvolveSchema(CompatibilityType.BACKWARD, SchemasUtil.avro1, SchemasUtil.avro2));
    assertEquals(false, canEvolveSchema(CompatibilityType.BACKWARD, SchemasUtil.avro1, SchemasUtil.avro3));
    assertEquals(false, canEvolveSchema(CompatibilityType.BACKWARD, SchemasUtil.avro1, SchemasUtil.avro4));
    assertEquals(false, canEvolveSchema(CompatibilityType.BACKWARD, SchemasUtil.avro1, SchemasUtil.avro5));
    // assertEquals(true, canEvolveSchema(CompatibilityType.BACKWARD, SchemasUtil.avro1, SchemasUtil.avro6));
  }

  @Test
  public void backwardTransitiveCompatibiltyStrategy() {
    assertEquals(true, canEvolveSchema(CompatibilityType.BACKWARD_TRANSITIVE, SchemasUtil.avro1, SchemasUtil.avro2));
    assertEquals(false, canEvolveSchema(CompatibilityType.BACKWARD_TRANSITIVE, SchemasUtil.avro2, SchemasUtil.avro3));
    assertEquals(false, canEvolveSchema(CompatibilityType.BACKWARD_TRANSITIVE, SchemasUtil.avro2, SchemasUtil.avro4));
    assertEquals(false, canEvolveSchema(CompatibilityType.BACKWARD_TRANSITIVE, SchemasUtil.avro2, SchemasUtil.avro5));
    // assertEquals(true, canEvolveSchema(CompatibilityType.BACKWARD_TRANSITIVE, SchemasUtil.avro2, SchemasUtil.avro6));
  }

  @Test
  public void forwardCompatibilityStrategy() {
    assertEquals(true, canEvolveSchema(CompatibilityType.FORWARD, SchemasUtil.avro1, SchemasUtil.avro2)); 
    assertEquals(true, canEvolveSchema(CompatibilityType.FORWARD, SchemasUtil.avro1, SchemasUtil.avro3)); 
    assertEquals(false, canEvolveSchema(CompatibilityType.FORWARD, SchemasUtil.avro1, SchemasUtil.avro4)); 
    assertEquals(false, canEvolveSchema(CompatibilityType.FORWARD, SchemasUtil.avro1, SchemasUtil.avro5)); 
    // assertEquals(false, canEvolveSchema(CompatibilityType.FORWARD, SchemasUtil.avro1, SchemasUtil.avro6));
  }

  @Test
  public void forwardTransitiveCompatibiltyStrategy() {
    assertEquals(true, canEvolveSchema(CompatibilityType.FORWARD_TRANSITIVE, SchemasUtil.avro1, SchemasUtil.avro2));
    assertEquals(true, canEvolveSchema(CompatibilityType.FORWARD_TRANSITIVE, SchemasUtil.avro2, SchemasUtil.avro3));
    assertEquals(false, canEvolveSchema(CompatibilityType.FORWARD_TRANSITIVE, SchemasUtil.avro3, SchemasUtil.avro4));
    assertEquals(false, canEvolveSchema(CompatibilityType.FORWARD_TRANSITIVE, SchemasUtil.avro3, SchemasUtil.avro5));
    // assertEquals(false, canEvolveSchema(CompatibilityType.FORWARD_TRANSITIVE, SchemasUtil.avro3, SchemasUtil.avro6));
  }

  @Test
  public void fullCompatibilityStrategy() {
    assertEquals(true, canEvolveSchema(CompatibilityType.FULL, SchemasUtil.avro1, SchemasUtil.avro2)); 
    assertEquals(false, canEvolveSchema(CompatibilityType.FULL, SchemasUtil.avro1, SchemasUtil.avro3)); 
    assertEquals(false, canEvolveSchema(CompatibilityType.FULL, SchemasUtil.avro1, SchemasUtil.avro4)); 
    assertEquals(false, canEvolveSchema(CompatibilityType.FULL, SchemasUtil.avro1, SchemasUtil.avro5)); 
    // assertEquals(false, canEvolveSchema(CompatibilityType.FULL, SchemasUtil.avro1, SchemasUtil.avro6)); 
  }

  @Test
  public void fullTransitiveCompatibilityStrategy() {
    assertEquals(true, canEvolveSchema(CompatibilityType.FULL_TRANSITIVE, SchemasUtil.avro1, SchemasUtil.avro2)); 
    assertEquals(false, canEvolveSchema(CompatibilityType.FULL_TRANSITIVE, SchemasUtil.avro2, SchemasUtil.avro3)); 
    assertEquals(false, canEvolveSchema(CompatibilityType.FULL_TRANSITIVE, SchemasUtil.avro2, SchemasUtil.avro4)); 
    assertEquals(false, canEvolveSchema(CompatibilityType.FULL_TRANSITIVE, SchemasUtil.avro2, SchemasUtil.avro5)); 
    // assertEquals(false, canEvolveSchema(CompatibilityType.FULL_TRANSITIVE, SchemasUtil.avro2, SchemasUtil.avro6)); 
  } 

  @Test
  public void noneTransitiveCompatibilityStrategy() {
    assertEquals(true, canEvolveSchema(CompatibilityType.NONE, SchemasUtil.avro1, SchemasUtil.avro2));
    // assertEquals(false, canEvolveSchema(CompatibilityType.FULL_TRANSITIVE, SchemasUtil.avro2, SchemasUtil.avro6)); 
  }

  private boolean canEvolveSchema(CompatibilityType compatibilityType, Schema baseSchema, Schema newSchema) {
    SchemaRegistry schemaRegistry = new SchemaRegistry(compatibilityType);

    try {
      schemaRegistry.putSchema(baseSchema);
      schemaRegistry.putSchema(newSchema);
    } catch (SchemaEvolutionException exception) {
      System.err.println(exception);
      return false;
    }
    return true;

  }

  // @Test
  // public void avro1x6() throws SchemaEvolutionException {
  //   schemaRegistry.putSchema(avro1);
  //   assertThrows(SchemaEvolutionException.class, () -> {
  //     schemaRegistry.putSchema(avro1);
  //   });
  // }
}
