package ua.goit.jdbc.service;

import ua.goit.jdbc.dao.DeveloperDao;
import ua.goit.jdbc.dto.DeveloperDto;
import ua.goit.jdbc.repository.DeveloperRepository;
import ua.goit.jdbc.service.converter.DeveloperConverter;

import java.sql.SQLException;
import java.util.Optional;

public class DeveloperService {
    private final DeveloperRepository developerRepository;
    private final DeveloperConverter developerConverter;

    public DeveloperService(DeveloperRepository developerRepository, DeveloperConverter developerConverter) {
        this.developerConverter = developerConverter;
        this.developerRepository = developerRepository;
    }

    public DeveloperDto save(DeveloperDto developerDto){
        DeveloperDao developerDao = developerRepository.save(developerConverter.to(developerDto));
        return developerConverter.from(developerDao);
    }

    public Optional<DeveloperDto> findById(Integer id)  {
        Optional<DeveloperDao> developerDao = developerRepository.findById(id);
        return developerDao.map(dao -> developerConverter.from(dao));
    }

}
