package com.sj.tc.controller;

import com.sj.tc.model.TalkingClockResponse;
import com.sj.tc.service.TalkingClockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TalkingClockControllerTests {
  @Mock
  TalkingClockService talkingClockService;

  @InjectMocks
  TalkingClockController controller;

  @Test
  public void testControllerSuccessResponse() {
    when(talkingClockService.process("12:30")).thenReturn(new TalkingClockResponse("12:30", "Half past twelve"));
    ResponseEntity<TalkingClockResponse> response = controller.getHumanReadableTime("12:30");
    TalkingClockResponse tcr = response.getBody();
    assertEquals("12:30", tcr.getInputTime());
    assertEquals("Half past twelve", tcr.getReadableTime());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testControllerErrorResponse() {
    when(talkingClockService.process("12:3000")).thenThrow(new RuntimeException("Input data is invalid: 12:3000"));
    ResponseEntity<TalkingClockResponse> response = controller.getHumanReadableTime("12:3000");
    TalkingClockResponse tcr = response.getBody();
    assertEquals("12:3000", tcr.getInputTime());
    assertEquals("Input data is invalid: 12:3000", tcr.getReadableTime());
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }
}
