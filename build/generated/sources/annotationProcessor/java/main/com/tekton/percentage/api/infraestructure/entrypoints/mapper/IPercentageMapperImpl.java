package com.tekton.percentage.api.infraestructure.entrypoints.mapper;

import com.tekton.percentage.api.domain.model.PercentageHistoryModel;
import com.tekton.percentage.api.domain.model.PercentageHistoryResponse;
import com.tekton.percentage.api.domain.model.PercentageModel;
import com.tekton.percentage.api.infraestructure.entrypoints.dto.request.PercentageDto;
import com.tekton.percentage.api.infraestructure.entrypoints.dto.response.HistoryResponseDto;
import com.tekton.percentage.api.infraestructure.entrypoints.dto.response.PercentageHistoryDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-28T19:52:57-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class IPercentageMapperImpl implements IPercentageMapper {

    @Override
    public PercentageModel toPercentageModel(PercentageDto percentageDto) {
        if ( percentageDto == null ) {
            return null;
        }

        PercentageModel.PercentageModelBuilder percentageModel = PercentageModel.builder();

        percentageModel.firstNumber( percentageDto.getFirstNumber() );
        percentageModel.secondNumber( percentageDto.getSecondNumber() );

        return percentageModel.build();
    }

    @Override
    public HistoryResponseDto toHistoryResponseDto(PercentageHistoryResponse percentageHistoryResponse) {
        if ( percentageHistoryResponse == null ) {
            return null;
        }

        HistoryResponseDto.HistoryResponseDtoBuilder historyResponseDto = HistoryResponseDto.builder();

        historyResponseDto.histories( percentageHistoryModelListToPercentageHistoryDtoList( percentageHistoryResponse.getPercentageHistoryModels() ) );

        return historyResponseDto.build();
    }

    protected PercentageHistoryDto percentageHistoryModelToPercentageHistoryDto(PercentageHistoryModel percentageHistoryModel) {
        if ( percentageHistoryModel == null ) {
            return null;
        }

        PercentageHistoryDto.PercentageHistoryDtoBuilder percentageHistoryDto = PercentageHistoryDto.builder();

        percentageHistoryDto.id( percentageHistoryModel.getId() );
        percentageHistoryDto.timestamp( percentageHistoryModel.getTimestamp() );
        percentageHistoryDto.endpoint( percentageHistoryModel.getEndpoint() );
        percentageHistoryDto.params( percentageHistoryModel.getParams() );
        percentageHistoryDto.response( percentageHistoryModel.getResponse() );

        return percentageHistoryDto.build();
    }

    protected List<PercentageHistoryDto> percentageHistoryModelListToPercentageHistoryDtoList(List<PercentageHistoryModel> list) {
        if ( list == null ) {
            return null;
        }

        List<PercentageHistoryDto> list1 = new ArrayList<PercentageHistoryDto>( list.size() );
        for ( PercentageHistoryModel percentageHistoryModel : list ) {
            list1.add( percentageHistoryModelToPercentageHistoryDto( percentageHistoryModel ) );
        }

        return list1;
    }
}
