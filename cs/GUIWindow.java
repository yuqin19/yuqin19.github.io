package prj5;

import cs2.Button;
import cs2.Shape;
import cs2.Window;
import cs2.*;

import java.awt.*;
import java.util.Iterator;


public class GUIWindow {
    private Window window;

    private Controller controller;

    private TextShape stateText;
    private LinkedList<Shape> shapeList = new LinkedList<>();

    public GUIWindow(LinkedList<StateCase> allStatesCovidCases) {
        this.controller = new Controller(allStatesCovidCases);
        this.window = new Window();
        this.initButton();
        this.stateText = new TextShape(window.getWidth() / 2 - 120, 20, "state", Color.BLACK, 12);
        this.window.addShape(stateText);
    }

    private void initButton() {
        Button sortByAlphaBtn = new Button("Sort by Alpha");
        sortByAlphaBtn.onClick(this, "clickedSortAlpha");
        window.addButton(sortByAlphaBtn, WindowSide.NORTH);

        Button quitButton = new Button("Quit");
        quitButton.onClick(this, "quit");
        window.addButton(quitButton, WindowSide.NORTH);

        Button sortByCRFBtn = new Button("Sort by CRF");
        sortByCRFBtn.onClick(this, "clickedSortCFR");
        window.addButton(sortByCRFBtn, WindowSide.NORTH);

        for (int i = 0; i < controller.getAllStatesCovidCases().size(); i++) {
            StateCase state = controller.getAllStatesCovidCases().get(i);
            Button stateButton = new Button("Repersent " + state.getState().name());
            stateButton.onClick(this, "showState");
            window.addButton(stateButton, WindowSide.SOUTH);
        }

    }

    public void displayStateChart(StateEnum stateEnum) {
        clearShape();

        stateText.setText(stateEnum.name() + " Case Fatality Ratios by Race");
        LinkedList<CovidCase> races = controller.getStateCovidCases(stateEnum);
        double maxCRF = controller.getCurrentStateMaxCFR();
        if (maxCRF <= 0) {
            maxCRF = 1;
        }

        int prefixX = 80;
        int suffixX = 40;
        int widthTmp = (window.getWidth() - prefixX - suffixX) / races.size();
        for (int i = 0; i < races.size(); i++) {
            CovidCase race = races.get(i);
            int raceStartX = prefixX + i * widthTmp;
            int raceStartY = window.getHeight() - 150;
            double crf = race.getCFR();
            if (crf < 0) {
                crf = 0;
            }
            int height = (int) (crf / maxCRF * 200 * 0.75);
            shapeList.add(new Shape(raceStartX, raceStartY - height - 30, 50, height, Color.BLUE));
            shapeList.add(new TextShape(raceStartX + 5, raceStartY, race.getCFPPercentage(), Color.BLACK, 12));
            shapeList.add(new TextShape(raceStartX + 5, raceStartY - 20, race.getRace().getName()));
        }

        Iterator<Shape> iterator = shapeList.iterator();
        while (iterator.hasNext()) {
            window.addShape(iterator.next());
        }

    }

    private void clearShape() {
        for (int i = 0; i < shapeList.size(); i++) {
            window.removeShape(shapeList.get(i));
        }
        shapeList.clear();
    }

    public void showState(Button stateBtn) {
        String name = stateBtn.getTitle();
        displayStateChart(StateEnum.valueOf(name.split(" ")[1]));
    }

    public void clickedSortAlpha(Button button) {
        controller.setSortByAlpha(true);
        displayStateChart(controller.getCurrentStateCase().getState());
    }

    public void clickedSortCFR(Button button) {
        controller.setSortByAlpha(false);
        displayStateChart(controller.getCurrentStateCase().getState());
    }

    public void quit(Button button) {
        System.exit(0);
    }

}
