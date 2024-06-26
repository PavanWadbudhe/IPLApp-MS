package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.IplTeam;

public interface ITeamOperationRepo extends JpaRepository<IplTeam, Integer> {

}
