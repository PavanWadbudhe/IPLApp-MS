package com.nt.ms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.client.IPLTeamServiceMSClient;
import com.nt.entity.IPLPlayer;
import com.nt.entity.IplTeam;
import com.nt.service.IPLPlayerMgmtService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/player-api")
@Slf4j
public class IPLPlayerOperationController {
	@Autowired
	private IPLPlayerMgmtService playerService;
	@Autowired
	private IPLTeamServiceMSClient client;
	
	@PostMapping("/save")
	public ResponseEntity<String> registerPlayer(@RequestBody IPLPlayer player){
		// get the Player's Team id
		int tid=player.getTeam().getTeamId();
		log.info("get team id registerPlayer() method --->controller");
		//use client comp get IPL Team obj from producer MS (IPLTeam MS)
		ResponseEntity<IplTeam> team=client.fetchTeamById(tid);
		log.info("fetching tean obj using feign client  registerPlayer() method --->controller");
		IplTeam tBody=team.getBody();
		//set Team obj to Player obj
		player.setTeam(tBody);
		
		try {
			//use service
			String msg=playerService.registerPlayer(player);
			log.info(" registerPlayer() method --->controller");
			return new ResponseEntity<String>(msg, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			log.error("Problem in registerPlayer() method --->controller");
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> showAllPlayers(){
		try {
			//use service
			List<IPLPlayer> list=playerService.getAllPlayers();
			log.info("showAllPlayer() method --->controller");
			return new ResponseEntity<List<IPLPlayer>>(list, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			log.error("Problem in showAllPlayers() method --->controller");
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> showPlayerById(@PathVariable Integer id){
		try {
			//use service
			IPLPlayer player=playerService.findPlayerById(id);
			log.info("showPlayerById() method --->controller");
			return new ResponseEntity<IPLPlayer>(player, HttpStatus.OK);
			
		}catch(Exception e) {
			log.error("Problem in showPlayerById() method --->controller");
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
