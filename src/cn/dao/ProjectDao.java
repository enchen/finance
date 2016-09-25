package cn.dao;

import java.util.List;

import cn.beans.Project;

public interface ProjectDao {
	List<Project> getAvailableProjects();
}
