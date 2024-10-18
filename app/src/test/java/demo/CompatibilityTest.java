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
  public void avro1x2() throws SchemaEvolutionException {
    assertEquals(true, canEvolveSchema(CompatibilityType.BACKWARD, SchemasUtil.avro1, SchemasUtil.avro2));
    assertEquals(true, canEvolveSchema(CompatibilityType.FORWARD, SchemasUtil.avro1, SchemasUtil.avro2)); 
    assertEquals(true, canEvolveSchema(CompatibilityType.FULL, SchemasUtil.avro1, SchemasUtil.avro2)); 
  }

  @Test
  public void avro1x3() throws SchemaEvolutionException {
    assertEquals(false, canEvolveSchema(CompatibilityType.BACKWARD, SchemasUtil.avro1, SchemasUtil.avro3));
    assertEquals(true, canEvolveSchema(CompatibilityType.FORWARD, SchemasUtil.avro1, SchemasUtil.avro3)); 
    assertEquals(false, canEvolveSchema(CompatibilityType.FULL, SchemasUtil.avro1, SchemasUtil.avro3)); 
  }

  @Test
  public void avro1x4() throws SchemaEvolutionException {
    assertEquals(false, canEvolveSchema(CompatibilityType.BACKWARD, SchemasUtil.avro1, SchemasUtil.avro4));
    assertEquals(false, canEvolveSchema(CompatibilityType.FORWARD, SchemasUtil.avro1, SchemasUtil.avro4)); 
    assertEquals(false, canEvolveSchema(CompatibilityType.FULL, SchemasUtil.avro1, SchemasUtil.avro4)); 
  }

  @Test
  public void avro1x5() throws SchemaEvolutionException {
    assertEquals(false, canEvolveSchema(CompatibilityType.BACKWARD, SchemasUtil.avro1, SchemasUtil.avro5));
    assertEquals(false, canEvolveSchema(CompatibilityType.FORWARD, SchemasUtil.avro1, SchemasUtil.avro5)); 
    assertEquals(false, canEvolveSchema(CompatibilityType.FULL, SchemasUtil.avro1, SchemasUtil.avro5)); 
  }

  private boolean canEvolveSchema(CompatibilityType compatibilityType, Schema baseSchema, Schema newSchema) {
    SchemaRegistry schemaRegistry = new SchemaRegistry(compatibilityType);

    try {
      schemaRegistry.putSchema(baseSchema);
      schemaRegistry.putSchema(newSchema);
    } catch (SchemaEvolutionException exception) {
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
