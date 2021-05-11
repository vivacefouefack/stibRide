package g54490.atlg4.stib.handler;

import g54490.atlg4.stib.presenter.Presenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class Handler implements EventHandler<ActionEvent> {

    private Presenter presenter;

    /**
     * constructor of handler.
     *
     * @param presenter she is the one who will ask the model to do something.
     */
    public Handler(Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * manage the different button actions, capture an event and ask the
     * presentation to do something.
     *
     * @param t event of any button on which we clicked.
     */
    @Override
    public void handle(ActionEvent t) {
        presenter.doSomething();
    }

}
