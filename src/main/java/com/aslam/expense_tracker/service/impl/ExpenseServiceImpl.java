package com.aslam.expense_tracker.service.impl;

import com.aslam.expense_tracker.dto.ExpenseRequestDTO;
import com.aslam.expense_tracker.dto.ExpenseResponseDTO;
import com.aslam.expense_tracker.mapper.ExpenseMapper;
import com.aslam.expense_tracker.model.Expense;
import com.aslam.expense_tracker.repository.ExpenseRepository;
import com.aslam.expense_tracker.service.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseMapper mapper;
    private final ExpenseRepository repository;

    public ExpenseServiceImpl(ExpenseMapper mapper, ExpenseRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public ExpenseResponseDTO addExpense(ExpenseRequestDTO dto) {
        Expense e = mapper.toEntity(dto);
        Expense saved = repository.save(e);
        return mapper.toDto(saved);
    }

    @Override
    public List<ExpenseResponseDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public List<ExpenseResponseDTO> getByCategory(String category) {
        return repository.findByCategoryIgnoreCase(category).stream().map(mapper::toDto).toList();
    }

    @Override
    public List<ExpenseResponseDTO> getByDate(LocalDate date) {
        return repository.findByDate(date).stream().map(mapper::toDto).toList();
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Expense with ID " + id + " not found.");
        }
        repository.deleteById(id);

    }

    @Override
    public double getMonthlyTotal(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<Expense> monthlyExpenses = repository.findByDateBetween(start, end);

        return monthlyExpenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}
