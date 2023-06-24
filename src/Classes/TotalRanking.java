package Classes;

public class TotalRanking {
    static int lastLevel=1;
    static int totalSavedLives=0;
    static String totalTime="0:0:0";

    public static void setNewLevel(int l) {
        lastLevel=l;
    }

    public static void addSavedLives(int savedLives) {
        totalSavedLives += savedLives;
    }
    public static void addTime(String time) {
        String[] separatedAddedTimeValues=time.split(":");
        String[] separatedTotalTimeValues=totalTime.split(":");
        int seconds=(Integer.valueOf(separatedAddedTimeValues[2])+Integer.valueOf(separatedTotalTimeValues[2]))%60;
        int minutes=(Integer.valueOf(separatedAddedTimeValues[2])+Integer.valueOf(separatedTotalTimeValues[2]))/60 +
                (Integer.valueOf(separatedAddedTimeValues[1])+Integer.valueOf(separatedTotalTimeValues[1]))%60;
        int hours=(Integer.valueOf(separatedAddedTimeValues[1])+Integer.valueOf(separatedTotalTimeValues[1]))/60 +
                (Integer.valueOf(separatedAddedTimeValues[0])+Integer.valueOf(separatedTotalTimeValues[0]));
        totalTime=hours+":"+minutes+":"+seconds;
    }

    public static int getLastLevel() {
        return lastLevel;
    }

    public static int getTotalSavedLives() {
        return totalSavedLives;
    }

    public static String getTotalTime() {
        return totalTime;
    }
}
