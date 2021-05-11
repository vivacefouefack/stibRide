package g54490.atlg4.stib.handler;


import g54490.atlg4.stib.presenter.Presenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author 
 */
public class Handler implements EventHandler<ActionEvent> {

    private Presenter presenter;

    public Handler(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handle(ActionEvent t) {
        System.out.println("name "+t.getSource().equals(t));
        presenter.doSomething();
    }

}
