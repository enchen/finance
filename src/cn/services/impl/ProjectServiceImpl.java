package cn.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.beans.Project;
import cn.dao.ProjectDao;
import cn.services.ProjectService;
@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectDao projectDao;
	@Override
	public List<Project> getAvailableProjects() {
		return projectDao.getAvailableProjects();
	}

}
