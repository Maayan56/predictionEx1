package dto.definition.property.definition.value.generator.impl.random.impl.bool;


import dto.definition.property.definition.value.generator.impl.random.api.AbstractRandomValueGenerator;

public class RandomBooleanValueGenerator extends AbstractRandomValueGenerator<Boolean> {

    @Override
    public Boolean generateValue() {
        return random.nextDouble() < 0.5;
    }
}
