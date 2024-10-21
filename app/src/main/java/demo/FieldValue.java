package demo;

import java.util.Optional;

public class FieldValue {
  public enum FieldType {
    INT,
    STRING,
    BOOL,
  }

  private FieldType type;
  private Optional<String> defaultValue;
  private Optional<String> alias;

  public FieldValue(FieldType type) {
    this.type = type;
    this.defaultValue = Optional.empty();
    this.alias = Optional.empty();
  }

  public FieldValue(FieldType type, String defaultValue) {
    this.type = type;
    this.defaultValue = Optional.of(defaultValue);
    this.alias = Optional.empty();
  }

  public FieldValue(FieldType type, String defaultValue, String alias) {
    this.type = type;
    this.defaultValue = Optional.empty();
    this.alias = Optional.of(alias);
  }

  public FieldType getType() {
    return type;
  }

  public Optional<String> getDefaultValue() {
    return this.defaultValue;
  }

  public boolean isRequired() {
    return this.defaultValue.isEmpty();
  }

  public Optional<String> getAlias() {
    return this.alias;
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

    return this.type.equals(ft.getType())
          && (this.getDefaultValue().isPresent() == ft.getDefaultValue().isPresent())
          && (this.getDefaultValue().isEmpty() || this.getDefaultValue().get().equals(ft.getDefaultValue().get())
          && (this.alias.isPresent() == ft.getDefaultValue().isPresent())
          && (this.getAlias().isEmpty() || this.getAlias().get().equals(ft.getAlias().get())));
  }

  @Override
  public int hashCode() {
    int total = 31;

    total = total * 31 + (this.type.hashCode());
    total = total * 31 + (this.defaultValue == null ? 0 : this.defaultValue.hashCode());

    return 0;
  }
}
