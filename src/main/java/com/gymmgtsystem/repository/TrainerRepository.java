package com.gymmgtsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmgtsystem.entity.TrainerEntity;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity, String> {

	Optional<TrainerEntity> findByTrainerId(Integer trainerId);

}
