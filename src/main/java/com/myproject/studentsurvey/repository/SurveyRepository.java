package com.myproject.studentsurvey.repository;

import com.myproject.studentsurvey.model.survey;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SurveyRepository extends JpaRepository<survey, Long> {

}
