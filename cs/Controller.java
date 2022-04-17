package prj5;

public class Controller {
    private LinkedList<StateCase> allStatesCovidCases;
    private boolean sortByAlpha = true;
    private StateCase currentStateCase;

    public Controller(LinkedList<StateCase> allStatesCovidCases) {
        this.allStatesCovidCases = allStatesCovidCases;
    }

    public LinkedList<CovidCase> getStateCovidCases(StateEnum stateEnum) {
        if (stateEnum == null) {
            if (currentStateCase == null) {
                currentStateCase = allStatesCovidCases.get(0);
            }
        } else {
            currentStateCase = null;
            for (int i = 0; i < allStatesCovidCases.size(); i++) {
                StateCase tmp = allStatesCovidCases.get(i);
                if (tmp.getState().equals(stateEnum)) {
                    currentStateCase = tmp;
                    break;
                }
            }
            if (currentStateCase == null) {
                throw new IllegalArgumentException();
            }
        }

        if (sortByAlpha) {
            return sortAlpha();
        } else {
            return sortCFR();
        }
    }

    public StateCase getCurrentStateCase() {
        return currentStateCase;
    }

    public double getCurrentStateMaxCFR() {
        return currentStateCase.getMaxCFR();
    }

    public void setSortByAlpha(boolean sortByAlpha) {
        this.sortByAlpha = sortByAlpha;
    }

    public LinkedList<StateCase> getAllStatesCovidCases() {
        return allStatesCovidCases;
    }

    private LinkedList<CovidCase> sortAlpha() {
        return RaceSort.sort(currentStateCase.getRaceList(), new CompareByRace());
    }

    private LinkedList<CovidCase> sortCFR() {
        return RaceSort.sort(currentStateCase.getRaceList(), new CompareByCFR());
    }
}