package com.myproject.studentsurvey.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class survey{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false) // Maps this field to a database column, making it non-nullable
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String streetAddress;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String zip;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate dateOfSurvey;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LikedMost likedMost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HowInterested howInterested;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecommendationLikelihood recommendationLikelihood;

    public enum LikedMost {
        STUDENTS, LOCATION, CAMPUS, ATMOSPHERE, DORM_ROOMS, SPORTS
    }

    public enum HowInterested {
        FRIENDS, TELEVISION, INTERNET, OTHER
    }

    public enum RecommendationLikelihood {
        VERY_LIKELY, LIKELY, UNLIKELY
    }

    // constructors

    public survey() {
    }

    // Parameterized Constructor
    public survey(String firstName, String lastName, String streetAddress, String city, String state,
                  String zip, String telephone, String email, LocalDate dateOfSurvey,
                  LikedMost likedMost, HowInterested howInterested, RecommendationLikelihood recommendationLikelihood) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.telephone = telephone;
        this.email = email;
        this.dateOfSurvey = dateOfSurvey;
        this.likedMost = likedMost;
        this.howInterested = howInterested;
        this.recommendationLikelihood = recommendationLikelihood;
    }


    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfSurvey() {
        return dateOfSurvey;
    }

    public void setDateOfSurvey(LocalDate dateOfSurvey) {
        this.dateOfSurvey = dateOfSurvey;
    }

    public LikedMost getLikedMost() {
        return likedMost;
    }

    public void setLikedMost(LikedMost likedMost) {
        this.likedMost = likedMost;
    }

    public HowInterested getHowInterested() {
        return howInterested;
    }

    public void setHowInterested(HowInterested howInterested) {
        this.howInterested = howInterested;
    }

    public RecommendationLikelihood getRecommendationLikelihood() {
        return recommendationLikelihood;
    }

    public void setRecommendationLikelihood(RecommendationLikelihood recommendationLikelihood) {
        this.recommendationLikelihood = recommendationLikelihood;
    }

    // toString
    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfSurvey=" + dateOfSurvey +
                ", likedMost='" + likedMost + '\'' +
                ", recommendationLikelihood='" + recommendationLikelihood + '\'' +
                '}';
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        survey survey = (survey) o;
        return Objects.equals(id, survey.id);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
