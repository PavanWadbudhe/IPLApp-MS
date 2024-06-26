package com.nt.service;

import java.util.List;

import com.nt.entity.IPLPlayer;

public interface IPLPlayerMgmtService {
	public String registerPlayer(IPLPlayer player);
	
	public List<IPLPlayer> getAllPlayers();
	
	public IPLPlayer findPlayerById(Integer id);

}
