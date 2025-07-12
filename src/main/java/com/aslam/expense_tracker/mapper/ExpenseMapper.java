package com.aslam.expense_tracker.mapper;

import com.aslam.expense_tracker.dto.ExpenseRequestDTO;
import com.aslam.expense_tracker.dto.ExpenseResponseDTO;
import com.aslam.expense_tracker.model.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    @Mapping(target = "user.id", source = "userId")
    Expense toEntity(ExpenseRequestDTO dto);

    @Mapping(target = "userId", source = "user.id")
    ExpenseResponseDTO toDto(Expense entity);
}
