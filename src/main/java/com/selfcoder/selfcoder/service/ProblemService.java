package com.selfcoder.selfcoder.service;

import com.selfcoder.selfcoder.dto.APIResponseDTO;
import com.selfcoder.selfcoder.form.CreateProblemForm;

public interface ProblemService {

	APIResponseDTO addProblem(CreateProblemForm createProblemForm);

	APIResponseDTO getProblems();
	
}
