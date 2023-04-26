package com.gymmgtsystem.service;

import java.util.List;

import com.gymmgtsystem.dto.TrainerRequestDto;
import com.gymmgtsystem.dto.TrainerResponse;

public interface ITrainerService {

	public Integer addTrainer(TrainerRequestDto dto);
	public TrainerResponse findTrainerById(String trainerId);
	public Boolean deleteTrainerById(String trainerId);
	public Integer updateTrainer(String trainerId, TrainerRequestDto dto);
	public List<TrainerResponse> getAllTrainer();
	
}
