package com.nt.ms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.IplTeam;
import com.nt.service.IPLTeamService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/team-api")
@Slf4j
public class IPLTeamOperationController {
	@Autowired
	private IPLTeamService service;
	
	@PostMapping("/save")
	public ResponseEntity<?> registerTeam(@RequestBody IplTeam team){
		try {
			//use service method
			String resultMsg=service.saveTeamInfo(team);
			log.info("registerTeam() method---->controller class");
			return new ResponseEntity<String>(resultMsg, HttpStatus.ACCEPTED);
			
		}catch(Exception e) {
			log.error("problem in registerTeam() method ---->controller class"+e.getMessage());
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/find/{tid}")
	public ResponseEntity<?> fetchTeamById(@PathVariable("tid") Integer id){
		
		try {
			//use service
			IplTeam team=service.findTeamById(id);
			log.info("findTeamById() method---->controller class");
			return new ResponseEntity<IplTeam> (team, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			log.error("problem in findTeamById() method ---->controller class"+e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> removeTeamById(@PathVariable Integer id){
		try {
			//use service
			String resultMsg=service.deleteTeamById(id);
			log.info("removeTeamById() method---->controller class");
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			log.error("problem in removeTeamById()  method ---->controller class"+e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateTeam(@RequestBody IplTeam team){
		try {
			//use service
			String resultMsg=service.updateTeam(team);
			log.info("updateTeam() method---->controller class");
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.error("problem in updateTeam()  method ---->controller class"+e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllTeam(){
		try {
			//use service
			List<IplTeam> list=service.getAllTeams();
			log.info("getAllTeam() method---->controller class");
			return new ResponseEntity<List<IplTeam>>(list, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			log.error("problem in getAllTeam()  method ---->controller class"+e.getMessage());
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
