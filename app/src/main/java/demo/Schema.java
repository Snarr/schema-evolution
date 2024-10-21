package demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import demo.FieldValue.FieldType;

public class Schema {
  private final String name;
  private final int version;
  private final Map<String, FieldValue> fields;

  private Schema(String name, int version, Map<String, FieldValue> fields) {
    this.name = name;
    this.version = version;
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

  public int getVersion() {
    return this.version;
  }

  public boolean containsField(Entry<String, FieldValue> fieldEntry) {
    return fields.entrySet().contains(fieldEntry);
  }

  public static class SchemaBuilder {
    private String _name;
    private Map<String, FieldValue> _fields;
    private int _version;

    public SchemaBuilder(String name) {
      this._name = name;
      this._version = 0;
      this._fields = new HashMap<String, FieldValue>();
    }

    public SchemaBuilder(Schema schemaBase) {
      this._name = schemaBase.getName();
      this._fields = new HashMap<String, FieldValue>();
      this._version = schemaBase.getVersion()+1;
      
      schemaBase.getAllFields().entrySet().forEach(entry -> {
        String entryFieldName = entry.getKey();
        FieldValue entryFieldValue = entry.getValue();
        if (entryFieldValue.getDefaultValue().isPresent()) {
          this.putField(entryFieldName, entryFieldValue.getType(), entryFieldValue.getDefaultValue().get());
        } else if (entryFieldValue.getAlias().isPresent()) {
          this.putField(entryFieldName, entryFieldValue.getType(), "", entryFieldValue.getAlias().get());
        } else {
          this.putField(entryFieldName, entryFieldValue.getType());
        }

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

    public SchemaBuilder putField(String fieldName, FieldType type, String defaultValue, String alias) {
      this._fields.put(fieldName, new FieldValue(type, defaultValue, alias));
      return this;
    }

    public SchemaBuilder removeField(String fieldName) {
      this._fields.remove(fieldName);
      return this;
    }

    public SchemaBuilder setVersion(int versionNo) {
      this._version = versionNo;
      return this;
    }

    public Schema build() {
      return new Schema(this._name, this._version, this._fields);
    }
  }
}
