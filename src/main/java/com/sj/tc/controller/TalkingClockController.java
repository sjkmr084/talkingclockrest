package com.sj.tc.controller;

import com.sj.tc.model.TalkingClockResponse;
import com.sj.tc.service.TalkingClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/talkingclock")
public class TalkingClockController {

  @Autowired
  TalkingClockService talkingClockService;

  @GetMapping(value = {"/", "/{time}"})
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<TalkingClockResponse> getHumanReadableTime(@PathVariable(name = "time", required = false) String time) {
    TalkingClockResponse talkingClockResponse;
    try {
      talkingClockResponse = talkingClockService.process(time);
    } catch (RuntimeException re) {
      talkingClockResponse = new TalkingClockResponse(time, re.getMessage());
      return ResponseEntity.badRequest().body(talkingClockResponse);
    }
    return ResponseEntity.ok(talkingClockResponse);
  }
}
