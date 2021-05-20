package g54490.atl4.sortingRace.model;

import g54490.atl4.sortingRace.observers.Observable;
import g54490.atl4.sortingRace.observers.Observer;
import g54490.atl4.sortingRace.view.Viewcontroller;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;

/**
 *
 * @author g54490@etu.he2b.be
 */
public class Threade extends Thread implements Observable {

    private List<Observer> observers;
    private int[] array;
    private Duration duration;
    private int nboperation;
    private String sortchoice;
    private int size;

    public Threade(Viewcontroller controller, int arraySize, String sortChoice) {
        this.observers = new ArrayList<>();
        this.array = new int[arraySize];
        this.nboperation = 0;
        this.sortchoice = sortChoice;
        this.size = arraySize;
        this.addObserver(controller);
    }

    public Duration getDuration() {
        return duration;
    }

    public int getNboperation() {
        return nboperation;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 
     * @return 
     */
    public int getSize() {
        return size;
    }

    public String getSortchoice() {
        return sortchoice;
    }

    public int lengthArray() {
        return array.length;
    }

    @Override
    public void run() {
        LocalDateTime beginingTime = LocalDateTime.now();
        Sort sort = new Sort();
        this.array = sort.generateArray(size);
        if (sortchoice == "MERGE") {
            sort.mergeSort(array, array.length);
            this.nboperation = sort.getNbOperation();
        } else {
            sort.bubbleSort(array);
            this.nboperation = sort.getNbOperation();
        }
        LocalDateTime endTime = LocalDateTime.now();
        this.duration = Duration.between(beginingTime, endTime);
        //System.out.println("duration "+duration.toMillis());
        
        Platform.runLater(() -> {
            this.notifyObservers();
        });

    }

    @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(this);
        }
    }

}
