package ai.shreds.wordpress4j.categoryRetrieval.domain.ports;

import ai.shreds.wordpress4j.categoryRetrieval.domain.entities.OptionEntity;

public interface OptionRepositoryPort {

    OptionEntity findByOptionName(String optionName);

}
