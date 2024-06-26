package com.nt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "IPL_TEAM_INFO")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class IplTeam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer teamId;
	
	@Column(length = 30)
	@NonNull
	private String teamName;
	
	@Column(length = 20)
	@NonNull
	private String location;
	
	@Column(length = 20)
	@NonNull
	private String owner;
	
	@Column(length = 20)
	@NonNull
	private String captain;

}
