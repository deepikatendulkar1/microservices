/*package com.myproject.studentsurvey.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
  //public ResourceNotFoundException(String message) {
  // super(message);
  //}

  private static final long serialVersionUID = 1L;

  private String resourceName; // Name of the resource (e.g., "Survey")
  private String fieldName;    // Field causing the error (e.g., "id")
  private Object fieldValue;   // Value of the field (e.g., "123")

  public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
    super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
  }

  // Getters and setters for resourceName, fieldName, and fieldValue
  public String getResourceName() {
    return resourceName;
  }

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public Object getFieldValue() {
    return fieldValue;
  }

  public void setFieldValue(Object fieldValue) {
    this.fieldValue = fieldValue;
  }

}
*/
package com.myproject.studentsurvey.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)  // HTTP status code for not found
public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final String resourceName;   // Name of the resource (e.g., "Survey")
  private final String fieldName;      // Field causing the error (e.g., "id")
  private final Object fieldValue;     // Value of the field (e.g., "123")

  // Constructor to initialize exception details
  public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
    super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
  }

  // Getters only as no need for setters
  public String getResourceName() {
    return resourceName;
  }

  public String getFieldName() {
    return fieldName;
  }

  public Object getFieldValue() {
    return fieldValue;
  }
}
