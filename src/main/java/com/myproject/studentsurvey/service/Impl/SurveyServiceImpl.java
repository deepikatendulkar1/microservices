package com.myproject.studentsurvey.service.Impl;

import com.myproject.studentsurvey.exception.ResourceNotFoundException;
import com.myproject.studentsurvey.model.survey;
import com.myproject.studentsurvey.repository.SurveyRepository;
import com.myproject.studentsurvey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    // Constructor-based injection for better testability
    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    /**
     * Create a new survey
     * @param survey The survey object to be saved
     * @return The saved survey
     */
    @Override
    public survey createSurvey(survey survey) {
        return surveyRepository.save(survey);
    }

    /**
     * Retrieve a survey by its ID
     * @param id The ID of the survey to retrieve
     * @return The survey with the specified ID
     * @throws ResourceNotFoundException if the survey with the given ID does not exist
     */
    @Override
    public survey getSurveyById(Long id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Survey", "id", id));
    }

    /**
     * Retrieve all surveys
     * @return A list of all surveys
     */
    @Override
    public List<survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    /**
     * Update an existing survey
     * @param id The ID of the survey to update
     * @param updatedSurvey The survey object containing updated data
     * @return The updated survey
     * @throws ResourceNotFoundException if the survey with the given ID does not exist
     */
    @Override
    public survey updateSurvey(Long id, survey updatedSurvey) {
        survey existingSurvey = surveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Survey", "id", id));

        // Updating fields with values from the updated survey object
        existingSurvey.setFirstName(updatedSurvey.getFirstName());
        existingSurvey.setLastName(updatedSurvey.getLastName());
        existingSurvey.setStreetAddress(updatedSurvey.getStreetAddress());
        existingSurvey.setCity(updatedSurvey.getCity());
        existingSurvey.setState(updatedSurvey.getState());
        existingSurvey.setZip(updatedSurvey.getZip());
        existingSurvey.setTelephone(updatedSurvey.getTelephone());
        existingSurvey.setEmail(updatedSurvey.getEmail());
        existingSurvey.setDateOfSurvey(updatedSurvey.getDateOfSurvey());
        existingSurvey.setLikedMost(updatedSurvey.getLikedMost());
        existingSurvey.setHowInterested(updatedSurvey.getHowInterested());
        existingSurvey.setRecommendationLikelihood(updatedSurvey.getRecommendationLikelihood());

        return surveyRepository.save(existingSurvey);
    }

    /**
     * Delete a survey by its ID
     * @param id The ID of the survey to delete
     * @throws ResourceNotFoundException if the survey with the given ID does not exist
     */
    @Override
    public void deleteSurvey(Long id) {
        survey existingSurvey = surveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Survey", "id", id));
        surveyRepository.delete(existingSurvey);
    }
}