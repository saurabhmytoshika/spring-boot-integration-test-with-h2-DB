package com.gymmgtsystem.controller;

import java.util.List;
import java.util.Objects;

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

import com.gymmgtsystem.dto.Status;
import com.gymmgtsystem.dto.TrainerRequestDto;
import com.gymmgtsystem.dto.TrainerResponse;
import com.gymmgtsystem.response.BaseResponse;
import com.gymmgtsystem.service.ITrainerService;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

	@Autowired
	private ITrainerService trainerService;

	@PostMapping
	public ResponseEntity<BaseResponse<Boolean, Integer>> addTrainer(
			@RequestBody TrainerRequestDto request) {
		Integer trainerId = trainerService.addTrainer(request);
		
		BaseResponse<Boolean, Integer> response = new BaseResponse<>();
		response.setData(Boolean.TRUE);
		response.setStatus(Status.SUCCESS);
		response.setMessage("Trainer created successfully with id :: " + trainerId);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{trainerId}")
	public ResponseEntity<BaseResponse<Boolean, Integer>> updateById(
			@PathVariable String trainerId,
			@RequestBody TrainerRequestDto request) {
		Integer trainerIdAfterUpdate = trainerService.updateTrainer(trainerId, request);

		BaseResponse<Boolean, Integer> response = new BaseResponse<>();
		response.setData(Boolean.TRUE);
		response.setStatus(Status.SUCCESS);
		response.setMessage("Trainer updated successfully with id :: " + trainerIdAfterUpdate);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<BaseResponse<List<TrainerResponse>, Integer>> getAllTrainer() {
		List<TrainerResponse> trainerList = trainerService.getAllTrainer();
		
		BaseResponse<List<TrainerResponse>, Integer> response = new BaseResponse<>();
		response.setData(trainerList);
		response.setRecords(trainerList.size());
		response.setMessage("Trainer List fetched successfully");
		response.setStatus(Status.SUCCESS);

		if(trainerList.size() == 0) return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		else return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/{trainerId}")
	public ResponseEntity<BaseResponse<TrainerResponse, Integer>> getTrainerById(
			@PathVariable String trainerId) {
		TrainerResponse trainerResponse = trainerService.findTrainerById(trainerId);
		
		BaseResponse<TrainerResponse, Integer> response = new BaseResponse<>();
		response.setData(trainerResponse);
		response.setMessage("Trainer List fetched successfully");
		response.setStatus(Status.SUCCESS);

		if(Objects.isNull(trainerResponse)) return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		else return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{trainerId}")
	public ResponseEntity<BaseResponse<Boolean, Integer>> deleteTrainerById(
			@PathVariable String trainerId) {
		BaseResponse<Boolean, Integer> response = new BaseResponse<>();
		response.setData(trainerService.deleteTrainerById(trainerId));
		response.setStatus(Status.SUCCESS);
		response.setMessage("Trainer deleted successfully with id :: " + trainerId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}