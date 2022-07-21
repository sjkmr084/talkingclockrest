package com.sj.tc.model;

public class TalkingClock {
    private static final int ZERO_HOUR = 0;
    private static final int ZERO_MINUTE = 0;
    private static final int MID_DAY_HOUR = 12;
    private static final int HALF_AN_HOUR = 30;
    private static final int INT = 60;
    private static final int HOUR = INT;
    private int hour;
    private int minute;
    private String[] numberNames = {"midnight", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
            "twenty", "twenty one", "twenty two", "twenty three", "twenty four", "twenty five", "twenty six", "twenty seven",
            "twenty eight", "twenty nine", "half"};

    public TalkingClock(String hour, String minute) {
        this.hour = Integer.parseInt(hour);
        this.minute = Integer.parseInt(minute);
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinutes() {
        return this.minute;
    }

    public String getHumanReadableTime() {
        if (isMidnight())
            return "Midnight";
        return getReadableTime();
    }

    private String getReadableTime() {
        String h = getReadableHour();
        String m = getReadableMinute();

        StringBuilder sb = new StringBuilder();
        if (minute == ZERO_MINUTE) {
            sb.append(h.substring(0, 1).toUpperCase())
                    .append(h.substring(1))
                    .append(" o'clock");
            return sb.toString();
        }
        if (minute > HALF_AN_HOUR) {
            sb.append(m.substring(0, 1).toUpperCase())
                    .append(m.substring(1))
                    .append(" to ")
                    .append(getReadableNextHour());
            return sb.toString();
        } else {
            sb.append(m.substring(0, 1).toUpperCase())
                    .append(m.substring(1))
                    .append(" past ")
                    .append(h);
            return sb.toString();
        }
    }

    private String getReadableMinute() {
        String readableMinute = "";
        if (minute > HALF_AN_HOUR) {
            readableMinute = getNumberName(HOUR - minute);
        } else {
            readableMinute = getNumberName(minute);
        }
        return readableMinute;
    }

    private String getReadableHour() {
        String readableHour = "";
        if (hour > MID_DAY_HOUR) {
            readableHour = getNumberName(hour - MID_DAY_HOUR);
        } else {
            readableHour = getNumberName(hour);
        }
        return readableHour;
    }

    private String getReadableNextHour() {
        int nextHour = hour + 1;
        String readableHour = "";
        if (nextHour > MID_DAY_HOUR) {
            readableHour = getNumberName(nextHour - MID_DAY_HOUR);
        } else {
            readableHour = getNumberName(nextHour);
        }
        return readableHour;
    }

    private String getNumberName(int hour) {

        return numberNames[hour];
    }

    private boolean isMidnight() {
        return hour == ZERO_HOUR && minute == ZERO_MINUTE;
    }
}
