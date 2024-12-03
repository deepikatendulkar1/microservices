package com.myproject.studentsurvey.controller;

import com.myproject.studentsurvey.model.survey;
import com.myproject.studentsurvey.service.SurveyService;
import com.myproject.studentsurvey.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/surveys") // Base URL for all survey-related endpoints
public class SurveyController {

    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    /**
     * Create a new survey
     *
     * @param survey The survey object to be created
     * @return The created survey
     */
    @PostMapping
    public ResponseEntity<survey> createSurvey(@RequestBody survey survey) {
        survey createdSurvey = surveyService.createSurvey(survey);
        return ResponseEntity.status(201).body(createdSurvey); // Created status code
    }

    /**
     * Get a survey by its ID
     *
     * @param id The ID of the survey
     * @return The survey with the specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<survey> getSurveyById(@PathVariable Long id) {
        survey survey = surveyService.getSurveyById(id);
        if (survey == null) {
            throw new ResourceNotFoundException("Survey", "id", id); // If not found, throw exception
        }
        return ResponseEntity.ok(survey);
    }

    /***
     * Get all surveys
     *
     * @return A list of all surveys
     */
    @GetMapping
    public ResponseEntity<List<survey>> getAllSurveys() {
        List<survey> surveys = surveyService.getAllSurveys();
        return ResponseEntity.ok(surveys);
    }

    /***
     * Update a survey by its ID
     *
     * @param id            The ID of the survey to update
     * @param updatedSurvey The updated survey object
     * @return The updated survey
     */
    @PutMapping("/{id}")
    public ResponseEntity<survey> updateSurvey(
            @PathVariable Long id,
            @RequestBody survey updatedSurvey) {
        survey existingSurvey = surveyService.getSurveyById(id);
        if (existingSurvey == null) {
            throw new ResourceNotFoundException("Survey", "id", id); // If not found, throw exception
        }
        survey survey = surveyService.updateSurvey(id, updatedSurvey);
        return ResponseEntity.ok(survey);
    }

    /**
     * Delete a survey by its ID
     *
     * @param id The ID of the survey to delete
     * @return A success message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSurvey(@PathVariable Long id) {
        survey existingSurvey = surveyService.getSurveyById(id);
        if (existingSurvey == null) {
            throw new ResourceNotFoundException("Survey", "id", id); // If not found, throw exception
        }
        surveyService.deleteSurvey(id);
        return ResponseEntity.ok("Survey deleted successfully!");
    }
}
