package system.engine.world.definition.property.api;

import system.engine.world.definition.value.generator.api.ValueGenerator;
import system.engine.world.rule.enums.Type;

public interface PropertyDefinition {
    String getUniqueName();
    Type getType();
    Object generateValue();
    ValueGenerator getValueGenerator();

    void setValueGenerator(ValueGenerator valueGenerator);
}