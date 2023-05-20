package com.selfcoder.selfcoder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.selfcoder.selfcoder.dto.APIResponseDTO;
import com.selfcoder.selfcoder.form.CreateProblemForm;
import com.selfcoder.selfcoder.service.ProblemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("selfcoder/v1/problems")
@RestController
@CrossOrigin(origins = "*")
public class ProblemController {

    private final ProblemService problemService;
	
    
	@PostMapping
	public ResponseEntity<APIResponseDTO> addProblem(@RequestBody CreateProblemForm problemForm) {
	   
		APIResponseDTO apiResponse = problemService.addProblem(problemForm); 
		 return new ResponseEntity<>(apiResponse,HttpStatus.valueOf(apiResponse.getStatus())); 
	}
	
	
	/*
	 * @GetMapping("/name/{name}") public ResponseEntity<APIResponseDTO>
	 * getProblemByProblemName(@PathVariable String name) {
	 * 
	 * APIResponseDTO apiResponse = problemService.getProblemByProblemName(name);
	 * return new
	 * ResponseEntity<>(apiResponse,HttpStatus.valueOf(apiResponse.getStatus())); }
	 */
	
	@GetMapping
    public ResponseEntity<APIResponseDTO> getProblem() {
		APIResponseDTO apiResponse = problemService.getProblems(); 
		 return new ResponseEntity<>(apiResponse,HttpStatus.valueOf(apiResponse.getStatus())); 
	}
}
