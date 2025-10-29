package com.tekton.percentage.api.domain.model.gateways;

import com.tekton.percentage.api.domain.model.PercentageHistoryModel;
import com.tekton.percentage.api.domain.model.PercentageModel;

import java.util.List;

public interface IHistoryJPARepository {

    void saveHistory(PercentageModel percentageModel, String response);
    List<PercentageHistoryModel> getPersentageHistory();

}
