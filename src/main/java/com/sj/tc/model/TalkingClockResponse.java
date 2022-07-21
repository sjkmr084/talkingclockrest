package com.sj.tc.model;

public class TalkingClockResponse {

  private final String inputTime;
  private final String readableTime;

  public TalkingClockResponse(String inputTime, String readableTime) {
    this.inputTime = inputTime;
    this.readableTime = readableTime;
  }


  public String getInputTime() {
    return inputTime;
  }

  public String getReadableTime() {
    return readableTime;
  }
}
