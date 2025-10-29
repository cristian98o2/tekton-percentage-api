package com.tekton.percentage.api.infraestructure.adapters.persistence.mapper;

import com.tekton.percentage.api.domain.model.PercentageHistoryModel;
import com.tekton.percentage.api.infraestructure.adapters.persistence.entity.PercentageHistoryEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-28T19:52:57-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class IHistoryMapperImpl implements IHistoryMapper {

    @Override
    public PercentageHistoryModel toPercentageHistoryModel(PercentageHistoryEntity percentageHistoryEntity) {
        if ( percentageHistoryEntity == null ) {
            return null;
        }

        PercentageHistoryModel.PercentageHistoryModelBuilder percentageHistoryModel = PercentageHistoryModel.builder();

        percentageHistoryModel.id( percentageHistoryEntity.getId() );
        percentageHistoryModel.timestamp( percentageHistoryEntity.getTimestamp() );
        percentageHistoryModel.endpoint( percentageHistoryEntity.getEndpoint() );
        percentageHistoryModel.params( percentageHistoryEntity.getParams() );
        percentageHistoryModel.response( percentageHistoryEntity.getResponse() );

        return percentageHistoryModel.build();
    }
}
