package dto.definition.entity.api;


import dto.definition.property.api.PropertyDefinitionDTO;

import java.util.List;

public interface EntityDefinitionDTO {
    String getUniqueName();
    int getPopulation();
    List<PropertyDefinitionDTO> getProps();
}
