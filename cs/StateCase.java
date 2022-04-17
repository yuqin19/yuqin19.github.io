package prj5;

import java.util.Iterator;

public class StateCase {
    private StateEnum state;
    private LinkedList<CovidCase> raceList;

    public StateCase(StateEnum state, LinkedList<CovidCase> rce) {
        this.state = state;
        this.raceList = rce;
    }

    public StateEnum getState() {
        return state;
    }

    public CovidCase getRace(String raceName) {
        for (Iterator<CovidCase> iterator = raceList.iterator(); iterator.hasNext(); ) {
            CovidCase race = iterator.next();
            if (raceName.equalsIgnoreCase(race.getRace().getName())) {
                return race;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder build = new StringBuilder();
        build.append(this.getState() + "\n");
        build.append(raceList.toString());
        return build.toString();
    }

    public LinkedList<CovidCase> getRaceList() {
        return raceList;
    }

    public double getMaxCFR() {
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < raceList.size(); i++) {
            if (max < raceList.get(i).getCFR()) {
                max = raceList.get(i).getCFR();
            }
        }
        return max;
    }


}