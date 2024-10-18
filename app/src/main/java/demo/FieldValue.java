package demo;

public class FieldValue {
  public enum FieldType {
    INT,
    STRING,
    BOOL,
  }

  private FieldType type;
  private String defaultValue;

  public FieldValue(FieldType type) {
    this.type = type;
  }

  public FieldValue(FieldType type, String defaultValue) {
    this.type = type;
    this.defaultValue = defaultValue;
  }

  public FieldType getType() {
    return type;
  }

  public String getDefaultValue() {
    return this.defaultValue;
  }

  public boolean isRequired() {
    return this.defaultValue == null;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof FieldValue)) {
      return false;
    }

    FieldValue ft = (FieldValue) o;

    return this.type == ft.getType() && this.getDefaultValue() == ft.getDefaultValue();
  }

  @Override
  public int hashCode() {
    int total = 31;

    total = total * 31 + (this.type.hashCode());
    total = total * 31 + (this.defaultValue == null ? 0 : this.defaultValue.hashCode());

    return 0;
  }
}
