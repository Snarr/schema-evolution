package demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import demo.FieldValue.FieldType;

public class Schema {
  private final String name;
  private final Map<String, FieldValue> fields;

  private Schema(String name, Map<String, FieldValue> fields) {
    this.name = name;
    this.fields = fields;
  }

  public String getName() {
    return this.name;
  }

  public Map<String, FieldValue> getAllFields() {
    return this.fields;
  }

  public FieldValue getField(String name) {
    return fields.get(name);
  }

  public boolean hasField(String name) {
    return fields.containsKey(name);
  }

  public boolean containsField(Entry<String, FieldValue> fieldEntry) {
    return fields.entrySet().contains(fieldEntry);
  }

  public static class SchemaBuilder {
    private String _name;
    private Map<String, FieldValue> _fields;

    public SchemaBuilder(String name) {
      this._name = name;
      this._fields = new HashMap<String, FieldValue>();
    }

    public SchemaBuilder(Schema schemaBase) {
      this._name = schemaBase.getName();
      this._fields = new HashMap<String, FieldValue>();
      
      schemaBase.getAllFields().entrySet().forEach(entry -> {
        String entryFieldName = entry.getKey();
        FieldValue entryFieldValue = entry.getValue();
        this.putField(entryFieldName, entryFieldValue.getType(), entryFieldValue.getDefaultValue());
      });;
    }

    public SchemaBuilder putField(String fieldName, FieldType type) {
      this._fields.put(fieldName, new FieldValue(type));
      return this;
    }

    public SchemaBuilder putField(String fieldName, FieldType type, String defaultValue) {
      this._fields.put(fieldName, new FieldValue(type, defaultValue));
      return this;
    }

    public SchemaBuilder removeField(String fieldName) {
      this._fields.remove(fieldName);
      return this;
    }

    public Schema build() {
      return new Schema(this._name, this._fields);
    }
  }
}
