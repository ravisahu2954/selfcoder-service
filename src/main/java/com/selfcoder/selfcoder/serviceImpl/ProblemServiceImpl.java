package com.selfcoder.selfcoder.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.selfcoder.selfcoder.dto.APIResponseDTO;
import com.selfcoder.selfcoder.dto.ProblemDTO;
import com.selfcoder.selfcoder.entity.Problem;
import com.selfcoder.selfcoder.exception.ProblemAlreadyExistException;
import com.selfcoder.selfcoder.exception.ProblemNotFoundException;
import com.selfcoder.selfcoder.form.CreateProblemForm;
import com.selfcoder.selfcoder.repository.ProblemRepository;
import com.selfcoder.selfcoder.service.ProblemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProblemServiceImpl implements ProblemService{

	private final ProblemRepository problemRepository; 
	
	private final ModelMapper modelMapper;
	
	@Override
	public APIResponseDTO addProblem(CreateProblemForm createProblemForm) {
		log.info("addProblem");

		Optional<Problem> opt = problemRepository.findByTitle(createProblemForm.getTitle());
		if (opt.isPresent()) {
			throw new ProblemAlreadyExistException("Problem already exist");
		}

		log.info("model");
		Problem problem = modelMapper.map(createProblemForm, Problem.class);
		problemRepository.save(problem);
		log.debug("Problem is added"); // log can be added in classes and enums
		return APIResponseDTO.builder()
				.timeStamp(System.currentTimeMillis())
				.message("Set Data")
				.success(true)
				.status(HttpStatus.CREATED.value())
				.build();
	}

	@Override
	public APIResponseDTO getProblems() {
		List<Problem> listOfProblem = problemRepository.findAll();
		List<ProblemDTO> listDTO = null;
		if(listOfProblem.isEmpty())
			throw new ProblemNotFoundException("Data not found");
		else
		{
			listDTO = listOfProblem.stream().map(u->modelMapper.map(u,ProblemDTO.class)).collect(Collectors.toList());
		}
	   return APIResponseDTO.builder()
				.timeStamp(System.currentTimeMillis())
				.data(listDTO)
				.message("data found")
				.success(true)
				.status(HttpStatus.OK.value())
				.build();
	}

}
