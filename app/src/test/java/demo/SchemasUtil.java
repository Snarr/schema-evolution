package demo;

import demo.FieldValue.FieldType;
import demo.Schema.SchemaBuilder;

public class SchemasUtil {
  
  public static final Schema avro1 = new SchemaBuilder("avro_schema")
    .putField("identifier", FieldType.STRING)
    .putField("first_name", FieldType.STRING)
    .build();

  public static final Schema avro2 = new SchemaBuilder(avro1)
    .putField("date", FieldType.STRING, "2000-01-01")
    .build();

  public static final Schema avro3 = new SchemaBuilder(avro1)
    .putField("date", FieldType.STRING)
    .build();                             

  public static final Schema avro4 = new SchemaBuilder("avro_schema")
    .putField("identifier", FieldType.INT)
    .putField("first_name", FieldType.STRING)
    .build();

  
  public static final Schema avro5 = new SchemaBuilder("avro_schema")
    .putField("identifier_new", FieldType.STRING)
    .putField("first_name", FieldType.STRING)
    .build();

  public static final Schema avro6 = new SchemaBuilder("avro_schema")
    .putField("identifier_new", FieldType.STRING, null, "identifier")
    .build();
}
