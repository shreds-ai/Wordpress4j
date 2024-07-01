package ai.shreds.wordpress4j.userRetrieval.domain.ports;

import ai.shreds.wordpress4j.userRetrieval.domain.entities.OptionEntity;

public interface OptionRepositoryPort {

    OptionEntity findByOptionName(String optionName);

}
