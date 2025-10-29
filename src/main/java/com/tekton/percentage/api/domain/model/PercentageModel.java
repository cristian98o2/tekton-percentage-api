package com.tekton.percentage.api.domain.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PercentageModel {

    public Double firstNumber;
    public Double secondNumber;

}
