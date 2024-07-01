package ai.shreds.wordpress4j.commentRetrieval.domain.ports;

import ai.shreds.wordpress4j.commentRetrieval.domain.entities.OptionEntity;

public interface OptionRepositoryPort {

    OptionEntity findByOptionName(String optionName);

}
