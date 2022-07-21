package com.sj.tc.service;

import com.sj.tc.exception.BadDataError;
import com.sj.tc.model.TalkingClock;
import com.sj.tc.model.TalkingClockResponse;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Objects;

@Service
public class TalkingClockService {
  private static final int MAX_HOURS = 23;
  private static final int MAX_MINUTE = 59;
  String timeRegex = "^\\d{1,2}:\\d{1,2}$";

  public TalkingClock createTakingClock(String time) {
    String[] timeData = validate(time.trim());
    TalkingClock tc = new TalkingClock(timeData[0], timeData[1]);
    return tc;
  }

  public TalkingClockResponse process(String time) {
    if (Objects.isNull(time) || time.isEmpty()) {
      return this.getTalkingClockResponseOfCurrentTime();
    } else {
      return this.getTalkingClockResponseOfGivenTime(time);
    }
  }

  private TalkingClockResponse getTalkingClockResponseOfCurrentTime() {
    LocalTime ldt = LocalTime.now();
    String time = ldt.getHour() + ":" + ldt.getMinute();

    return getTalkingClockResponseOfGivenTime(time);
  }

  private TalkingClockResponse getTalkingClockResponseOfGivenTime(String time) {

    TalkingClock talkingClock = this.createTakingClock(time);
    return new TalkingClockResponse(time, talkingClock.getHumanReadableTime());


  }

  private String[] validate(String time) {
    if (!time.matches(timeRegex)) {
      throw new BadDataError("Input data is invalid: " + time);
    }

    String[] timeData = time.split(":");

    if (isHourOutOfRange(timeData[0]) || isMinutesOutOfRange(timeData[1])) {
      throw new BadDataError("Input data is invalid: " + time);
    }
    return timeData;
  }

  private boolean isHourOutOfRange(String hour) {
    return Integer.parseInt(hour) > MAX_HOURS;
  }

  private boolean isMinutesOutOfRange(String minute) {
    return Integer.parseInt(minute) > MAX_MINUTE;
  }


}
