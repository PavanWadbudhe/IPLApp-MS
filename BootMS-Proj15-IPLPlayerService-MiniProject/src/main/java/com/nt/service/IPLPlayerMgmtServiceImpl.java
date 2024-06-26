package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.IPLPlayer;
import com.nt.repository.IPLPlayerRepository;

import lombok.extern.slf4j.Slf4j;

@Service("playerService")
@Slf4j
public class IPLPlayerMgmtServiceImpl implements IPLPlayerMgmtService {
	@Autowired
	private IPLPlayerRepository playerRepo;

	@Override
	public String registerPlayer(IPLPlayer player) {
		//use repo
		int idVal=playerRepo.save(player).getPid();
		log.info("registerPlayer() method --->playerService ");
		return "Player is registerd with the Id::"+idVal;
	}

	@Override
	public List<IPLPlayer> getAllPlayers() {
		//use repo
		log.info("getAllPlayers() method --->playerService ");
		return playerRepo.findAll();
	}

	@Override
	public IPLPlayer findPlayerById(Integer id) {
		//use repo
		Optional<IPLPlayer> opt=playerRepo.findById(id);
		if(opt.isPresent()) {
			log.info("findPlayerById() method --->playerService ");
			return opt.get();
		}
		log.error("problem in registerPlayer() method --->playerService ");
			throw new IllegalArgumentException("Player not found");
	}

}
