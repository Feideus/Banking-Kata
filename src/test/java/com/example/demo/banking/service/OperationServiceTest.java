package com.example.demo.banking.service;

import com.example.demo.service.OperationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.demo.dto.input.OperationInputDto;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class OperationServiceTest {

    @MockBean
    private OperationService operationService;

    @Test
    public void operationValidDepotShouldSucceed(){
        // Given
        // OperationInput is valid
        OperationInputDto operationInputDto = OperationInputDto
                .builder()
                .amount(10.0)
                .build();

        //When
        boolean isComplete = operationService.makeOperation(operationInputDto);

        //Then
        assertThat(isComplete).isTrue();
    }

    @Test
    public void operationInvalidDepotShouldFail(){
        //Given
        // OperationInput is invalid, amount is negative
        OperationInputDto operationInputDto = OperationInputDto
                .builder()
                .amount(-10.0)
                .build();

        //When
        boolean isComplete = operationService.makeOperation(operationInputDto);

        //Then
        assertThat(isComplete).isTrue();
    }
}
