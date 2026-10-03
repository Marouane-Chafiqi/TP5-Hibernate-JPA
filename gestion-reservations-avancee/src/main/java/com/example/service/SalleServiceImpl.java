package com.example.service;

import com.example.model.Salle;
import com.example.repository.SalleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class SalleServiceImpl implements SalleService {

    private final SalleRepository repository;

    public SalleServiceImpl(SalleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Salle> findAvailableRooms(LocalDateTime start, LocalDateTime end) {
        return repository.findAvailableRooms(start, end);
    }

    @Override
    public List<Salle> searchRooms(Map<String, Object> criteria) {
        return repository.findByCriteria(criteria);
    }

    @Override
    public List<Salle> getPaginatedRooms(int page, int size) {
        return repository.findAllPaginated(page, size);
    }

    @Override
    public int getTotalPages(int size) {
        return (int) Math.ceil((double) repository.count() / size);
    }
}