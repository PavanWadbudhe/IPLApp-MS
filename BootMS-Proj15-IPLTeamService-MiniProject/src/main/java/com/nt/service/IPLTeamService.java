package com.nt.service;

import java.util.List;

import com.nt.entity.IplTeam;

public interface IPLTeamService {
	
	public String saveTeamInfo(IplTeam team);
	
	public IplTeam findTeamById(Integer id) throws Exception;
	
	public String deleteTeamById(Integer id) throws Exception;
	
	public String updateTeam(IplTeam team) throws Exception;
	
	public List<IplTeam> getAllTeams();

}
