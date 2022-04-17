package prj5;

import java.util.Comparator;

public class RaceSort {

    public static LinkedList<CovidCase> sort(LinkedList<CovidCase> linkedList, Comparator<CovidCase> comparator) {
        Object[] array = linkedList.toArray();
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (comparator.compare((CovidCase)array[min],(CovidCase) array[j]) > 0) {
                    min = j;
                }
            }
            if (min != i) {
                Object tmp = array[min];
                array[min] = array[i];
                array[i] = tmp;
            }
        }

        LinkedList<CovidCase> raceList = new LinkedList<CovidCase>();
        for (int i = 0; i < array.length; i++) {
            raceList.add((CovidCase)array[i]);
        }

        return raceList;
    }
}
