package com.john.belt_exam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.john.belt_exam.models.Television;
import com.john.belt_exam.repo.TelevisionRepository;

@Service
public class TelevisionService {

	// adding the book repository as a dependency
	@Autowired
	private TelevisionRepository televisionRepository;

	// READ ALL
	public List<Television> allTelevisions() {
		return televisionRepository.findAll();
	}

	// CREATE
	public Television createTelevision(Television t) {
		return televisionRepository.save(t);
	}

	// READ ONE
	public Television findTelevision(Long id) {
		Optional<Television> optionalTelevision = televisionRepository.findById(id);
		if (optionalTelevision.isPresent()) {
			return optionalTelevision.get();
		} else {
			return null;
		}

	}

	// UPDATE
	public Television updateTelevision(Television t) {
		return televisionRepository.save(t);
	}

	// Delete
	public void deleteTelevision(Long id) {
		televisionRepository.deleteById(id);
	}
}
