package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.IplTeam;
import com.nt.repository.ITeamOperationRepo;

import lombok.extern.slf4j.Slf4j;

@Service("iplService")
@Slf4j
public class IPLTeamServiceImpl implements IPLTeamService {
	
	@Autowired
	private ITeamOperationRepo teamRepo;

	@Override
	public String saveTeamInfo(IplTeam team) {
		//use JpaRepo predefined method to save the team
		log.info("saveTeamInfo() method (service)");
		int tid=teamRepo.save(team).getTeamId();
		return "Team is registered with id value ::"+tid;
	}

	@Override
	public IplTeam findTeamById(Integer id) throws Exception {
		log.info("findTeamById() method (service)");
		//use repo
		Optional<IplTeam> opt=teamRepo.findById(id);
	/*	if(opt.isPresent()) {
			return opt.get();
		}
		throw new IllegalArgumentException("Incorect Team Id");
	*/
		
	return teamRepo.findById(id).orElseThrow(()-> new IllegalAccessException("Incorect Team Id"));

	}

	@Override
	public String deleteTeamById(Integer id) throws Exception {
		log.info("deleteTeamById() method (service)");
		//use repo
		Optional<IplTeam> opt=teamRepo.findById(id);
		if(opt.isPresent()) {
			teamRepo.deleteById(id);
			return id+" Team deleted";
		}
		else
			throw new IllegalAccessException("Incorect Team id");
	}

	@Override
	public String updateTeam(IplTeam team) throws Exception{
		log.info("updateTeam() method (service)");
		//use repo
		Optional<IplTeam> opt=teamRepo.findById(team.getTeamId());
		if(opt.isPresent()) {
			teamRepo.save(team);
			return team.getTeamId()+" Team is updated";
		}
		else
			throw new IllegalAccessException("Incorect Team id");
	}

	@Override
	public List<IplTeam> getAllTeams() {
		//use repo
		log.info("getAllTeams() method (service)");
		return teamRepo.findAll();
	}
	
}
