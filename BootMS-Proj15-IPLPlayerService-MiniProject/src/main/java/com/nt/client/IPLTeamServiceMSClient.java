package com.nt.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nt.entity.IplTeam;

@FeignClient("TEAM-SERVICE")
public interface IPLTeamServiceMSClient {
	
	@GetMapping("/team-api/find/{tid}")
	public ResponseEntity<IplTeam> fetchTeamById(@PathVariable("tid") Integer id);

}
