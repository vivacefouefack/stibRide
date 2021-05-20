package g54490.atl4.sortingRace.model;

import g54490.atl4.sortingRace.observers.Observer;
import g54490.atl4.sortingRace.view.Viewcontroller;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vivac
 */
public class CreateAllThread implements Observer {

    private int nbThread;
    private String sortChoice;
    private int size;
    private List<Threade> listThread;
    private int[] array;
    private Viewcontroller control;
    private int arraySizeToSort;
    private int onePortion;
    private int value = 0;

    public CreateAllThread(Viewcontroller control, int nbThread, String sortChoice, int size) {
        this.nbThread = nbThread;
        this.sortChoice = sortChoice;
        this.size = size;
        this.control = control;
        this.listThread = new ArrayList<>();
        this.array = new Sort().generateArray(size);
        this.onePortion = 0;
        value = 0;
        startAll();
    }

    private synchronized void startAll() {
        onePortion = (size / 10);
        arraySizeToSort = onePortion;
        boolean beginSort = false;
        if (!beginSort) {
            this.listThread.add(new Threade(control, value, sortChoice));
            value += onePortion;
            beginSort = true;
        }

        for (int i = 1; i < nbThread; i++) {
            listThread.add(new Threade(control, value, sortChoice));
            value += onePortion;
        }
        for (Thread thread : listThread) {
            thread.start();
        }

    }

    @Override
    public void update(Threade arg) {
        if (size >= arraySizeToSort) {
            for (Threade thread : listThread) {
                if (thread.getName().equals(arg.getName())) {
                    thread.setSize(arraySizeToSort);
                    Thread t = new Thread(thread);
                    t.start();
                    arraySizeToSort += onePortion;
                }
            }
        }
    }

}
