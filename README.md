# Schema Evolution Demo

This repository implements a Schema Registry to demonstrate Schema Evolution with different compatibility types.

## Compatibility Types

| Compatibility Type  | Changes allowed                             | Check against which schemas     |
|---------------------|---------------------------------------------|---------------------------------|
| BACKWARD            | Delete fields, Add optional fields          | Last version                    |
| BACKWARD_TRANSITIVE | Delete fields, Add optional fields          | All previous versions           |
| FORWARD             | Add fields, Delete optional fields          | Last version                    |
| FORWARD_TRANSITIVE  | Add fields, Delete optional fields          | All previous versions           |
| FULL                | Add optional fields, Delete optional fields | Last version                    |
| FULL_TRANSITIVE     | Add optional fields, Delete optional fields | All previous versions           |
| NONE                | All changes are accepted                    | Compatibility checking disabled |

## Resources

This code was based on the [Confluent Schema Registry: Schema Evolution documentation](https://docs.confluent.io/platform/current/schema-registry/fundamentals/index.html#avro-json-and-protobuf-supported-formats-and-extensibility).

The test cases were designed after [Thiago Corden's "Evolving Schemas with Schema Registry" article](https://medium.com/data-arena/schema-evolution-with-schema-registry-8d601ee84f4b).