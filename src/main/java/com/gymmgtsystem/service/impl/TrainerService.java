package com.gymmgtsystem.service.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gymmgtsystem.dto.TrainerRequestDto;
import com.gymmgtsystem.dto.TrainerResponse;
import com.gymmgtsystem.entity.TrainerEntity;
import com.gymmgtsystem.repository.TrainerRepository;
import com.gymmgtsystem.service.ITrainerService;

@Service
public class TrainerService implements ITrainerService {
	
	@Autowired
	private TrainerRepository trainerRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Integer addTrainer(TrainerRequestDto request) {
		TrainerEntity trainer = trainerRepository.save(mapper.map(request, TrainerEntity.class));

		return trainer.getTrainerId();
	}
	
	@Override
	public Integer updateTrainer(String id, TrainerRequestDto request) {
		Integer trainerId = Integer.valueOf(id);
		TrainerEntity trainer = trainerRepository.findByTrainerId(trainerId)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Trainer doesn't exist with the given trainer Id"));

		trainer = mapper.map(request, TrainerEntity.class);
		trainer.setTrainerId(trainerId);
		trainer = trainerRepository.save(trainer);

		return trainer.getTrainerId();

	}

	@Override
	public TrainerResponse findTrainerById(String trainerId) {
		TrainerEntity trainer = trainerRepository.findByTrainerId(Integer.valueOf(trainerId))
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Trainer doesn't exist with the given trainer Id"));
		
		return mapper.map(trainer, TrainerResponse.class);
	}

	@Override
	public Boolean deleteTrainerById(String trainerId) {
		TrainerEntity trainer = trainerRepository.findByTrainerId(Integer.valueOf(trainerId))
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Trainer doesn't exist with the given trainer Id"));
		
		trainerRepository.delete(trainer);
		return true;
	}

	@Override
	public List<TrainerResponse> getAllTrainer() {
		List<TrainerEntity> trainerList = trainerRepository.findAll();
		
		Type listType = new TypeToken<List<TrainerResponse>>(){}.getType();
		return mapper.map(trainerList, listType);
	}
}
