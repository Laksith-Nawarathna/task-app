package lk.ijse.dep9.app.service.custom.impl;

import lk.ijse.dep9.app.dao.custom.ProjectDAO;
import lk.ijse.dep9.app.dao.custom.TaskDAO;
import lk.ijse.dep9.app.dto.ProjectDTO;
import lk.ijse.dep9.app.entity.Project;
import lk.ijse.dep9.app.exceptions.AccessDeniedException;
import lk.ijse.dep9.app.service.custom.ProjectTaskService;
import lk.ijse.dep9.app.util.Transformer;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Component
public class ProjectTaskServiceImpl implements ProjectTaskService {

    private TaskDAO taskDAO;
    private ProjectDAO projectDAO;

    private final Transformer transformer;

    public ProjectTaskServiceImpl(TaskDAO taskDAO, ProjectDAO projectDAO, Transformer transformer) {
        this.taskDAO = taskDAO;
        this.projectDAO = projectDAO;
        this.transformer = transformer;
    }

    @Override
    public ProjectDTO createNewProject(ProjectDTO projectDTO) {
        return transformer.toProjectDTO(projectDAO.save(transformer.toProject(projectDTO)));
    }

    @Override
    public List<ProjectDTO> getAllProjects(String username) {
        return projectDAO.findAllProjectsByUsername(username).stream().map(transformer::toProjectDTO).collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProjectDetails(String username, int projectId) {
        ProjectDTO project = projectDAO.findById(projectId).
                map(transformer::toProjectDTO).orElseThrow(() -> new EmptyResultDataAccessException(1));
        if (!project.getUsername().matches(username)) throw new AccessDeniedException();
        return project;
    }

    @Override
    public void renameProject(ProjectDTO project) {
        Project projectEntity = projectDAO.findById(project.getId()).
                orElseThrow(() -> new EmptyResultDataAccessException(1));
        if (!projectEntity.getUsername().matches(project.getUsername())) throw new AccessDeniedException();
        projectDAO.update(transformer.toProject(project));
    }

    @Override
    public void deleteProject(String username, int projectId) {
        Project project = projectDAO.findById(projectId).orElseThrow(
                () -> new EmptyResultDataAccessException(1));
        if (!project.getUsername().matches(username)) throw new AccessDeniedException();
        taskDAO.findAllByProjectId(projectId).forEach(task -> taskDAO.deleteById(task.getId()));
        projectDAO.deleteById(projectId);
    }
}
