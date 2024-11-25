package com.myproject.studentsurvey.service;

import com.myproject.studentsurvey.model.survey;
import java.util.List;

public interface SurveyService {
    survey createSurvey(survey survey);

    survey getSurveyById(Long id);
    List<survey> getAllSurveys();
    //survey saveSurvey(survey survey);
    survey updateSurvey(Long id, survey updatedSurvey);
    void deleteSurvey(Long id);
}
