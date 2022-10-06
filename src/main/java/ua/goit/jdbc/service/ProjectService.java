package ua.goit.jdbc.service;

import ua.goit.jdbc.dto.DeveloperDto;
import ua.goit.jdbc.dto.ProjectDto;
import ua.goit.jdbc.repository.ProjectRepository;
import ua.goit.jdbc.service.converter.ProjectConverter;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;

    public ProjectService(ProjectRepository projectRepository, ProjectConverter projectConverter) {
        this.projectRepository = projectRepository;
        this.projectConverter = projectConverter;
    }

    public List<ProjectDto> findAllProjects() {
        return projectRepository.findAll().stream().map(dao -> projectConverter.from(dao))
                .collect(Collectors.toList());
    }
}
