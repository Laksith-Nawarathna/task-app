package lk.ijse.dep9.app.service.custom.impl;

import lk.ijse.dep9.app.dao.custom.ProjectDAO;
import lk.ijse.dep9.app.dao.custom.TaskDAO;
import lk.ijse.dep9.app.dto.ProjectDTO;
import lk.ijse.dep9.app.dto.TaskDTO;
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

    private final ProjectDAO projectDAO;
    private final TaskDAO taskDAO;
    private final Transformer transformer;

    public ProjectTaskServiceImpl(ProjectDAO projectDAO, TaskDAO taskDAO, Transformer transformer) {
        this.projectDAO = projectDAO;
        this.taskDAO = taskDAO;
        this.transformer = transformer;
    }

    @Override
    public ProjectDTO createNewProject(ProjectDTO projectDTO) {
        return transformer.toProjectDTO(projectDAO.save(transformer.toProject(projectDTO)));
    }

    @Override
    public List<ProjectDTO> getAllProjects(String username) {
        return projectDAO.findAllProjectsByUsername(username).stream().
                map(transformer::toProjectDTO).collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProjectDetails(String username, int projectId) {
        return projectDAO.findById(projectId).map(transformer::toProjectDTO).get();
    }

    @Override
    public void renameProject(ProjectDTO project) {
        projectDAO.update(transformer.toProject(project));
    }

    @Override
    public void deleteProject(String username, int projectId) {
        taskDAO.findAllByProjectId(projectId).forEach(task -> taskDAO.deleteById(task.getId()));
        projectDAO.deleteById(projectId);
    }

    @Override
    public TaskDTO createNewTask(String username, TaskDTO task) {
        return null;
    }

    @Override
    public void renameTask(String username, TaskDTO task) {

    }

    @Override
    public void deleteTask(String username, TaskDTO taskDTO) {

    }

    @Override
    public TaskDTO getTaskDetails(String username, TaskDTO taskDTO) {
        return null;
    }

    @Override
    public List<TaskDTO> getAllTasks(String username, int projectId) {
        return null;
    }

    @Override
    public void updateTaskStatus(String username, TaskDTO taskDTO, boolean completed) {

    }
}
