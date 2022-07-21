package com.sj.tc.service;

import com.sj.tc.exception.BadDataError;
import com.sj.tc.model.TalkingClock;
import com.sj.tc.model.TalkingClockResponse;
import com.sj.tc.service.TalkingClockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TalkingClockServiceTests {

  @ParameterizedTest
  @ValueSource(strings = {"0:00", "1:00", "2:00", "13:00", "13:05", "13:30", "13:35"})
  public void testCreateTalkingClock(String time) {
    String[] params = time.split(":");
    TalkingClockService tcs = new TalkingClockService();
    TalkingClock tc = tcs.createTakingClock(time);
    assertEquals(Integer.valueOf(params[0]), tc.getHour());
    assertEquals(Integer.valueOf(params[1]), tc.getMinutes());
  }

  @ParameterizedTest
  @ValueSource(strings = {"0,0", "24:00", "23:60", "1-00", "two:00", "2.00", "3:ZeroFive", "1:30.0", "13:35:30"})
  public void testCreateTalkingClockThrowsExceptionForInvalidData(String time) {
    TalkingClockService tcs = new TalkingClockService();
    BadDataError error = assertThrows(BadDataError.class, () -> tcs.createTakingClock(time));
    assertEquals("Input data is invalid: " + time, error.getMessage());

  }

  @ParameterizedTest
  @ValueSource(strings = {"0:00,Midnight", "1:00,One o'clock", "2:00,Two o'clock", "13:00,One o'clock", "13:05,Five past one", "13:10,Ten past one", "13:25,Twenty five past one", "13:30,Half past one", "13:35,Twenty five to two", "13:55,Five to two"})
  public void testTalkingClockPrintHumanReadableTime(String time) {
    String[] timeData = time.split(",");
    TalkingClockService tcs = new TalkingClockService();
    TalkingClock tc = tcs.createTakingClock(timeData[0]);
    assertEquals(timeData[1], tc.getHumanReadableTime());

  }
  @ParameterizedTest
  @ValueSource(strings = {"0:00,Midnight", "1:00,One o'clock", "2:00,Two o'clock", "13:00,One o'clock", "13:05,Five past one", "13:10,Ten past one", "13:25,Twenty five past one", "13:30,Half past one", "13:35,Twenty five to two", "13:55,Five to two"})
  public void testTalkingClockServiceResponseForGivenTime(String time) {
    String[] timeData = time.split(",");
    TalkingClockService tcs = new TalkingClockService();
    TalkingClockResponse tcr = tcs.process(timeData[0]);
    assertEquals(timeData[0], tcr.getInputTime());
    assertEquals(timeData[1], tcr.getReadableTime());

  }

  @Test
  public void testTalkingClockServiceResponseForCurrentTime() {
    TalkingClockService tcs = new TalkingClockService();
    TalkingClockResponse tcr = tcs.process("");
    TalkingClock tc = tcs.createTakingClock(tcr.getInputTime());
    assertEquals(tcr.getReadableTime(), tc.getHumanReadableTime());

  }
}
