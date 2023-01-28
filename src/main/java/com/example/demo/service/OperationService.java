package com.example.demo.service;

import com.example.demo.dto.input.OperationInputDto;
import com.example.demo.dto.ouput.OperationOutputDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperationService {

    public boolean makeOperation(final OperationInputDto operationInputDto){
        return false;
    }

    public List<OperationOutputDto> getOperationsForAccount(final Long accountNumber){
        return new ArrayList<>();
    }
    public List<OperationOutputDto> depositForAccount(final OperationInputDto operationInputDto){
        return new ArrayList<>();
    }
    public List<OperationOutputDto> withdrawFromAccount(final OperationInputDto operationInputDto){
        return new ArrayList<>();
    }
}