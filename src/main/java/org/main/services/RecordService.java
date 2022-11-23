package org.main.services;

import org.main.entities.Record;
import org.main.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    public List<Record> getRecordsByUserId(Long id) {
        return recordRepository.findAllByUserId(id);
    }

    public void deleteById(Long id) {
        recordRepository.deleteById(id);
    }

    public List<Record> getRecordsByDoctorId(Long id) {
        return recordRepository.findAllByDoctorId(id);
    }

    public Record saveRecord(Record record) {
        return recordRepository.save(record);
    }
}
