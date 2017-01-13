import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Aishwarya Srivastava
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @ensures <pre>
     * {@code [this.view has been updated to be consistent with this.model]}
     * </pre>
     */
    private void updateViewToMatchModel() {

        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        this.view.updateTopDisplay(top);
        this.view.updateBottomDisplay(bottom);

    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        this.updateViewToMatchModel();
        view.updateSubtractAllowed(true);
        view.updateDivideAllowed(false);
        view.updateRootAllowed(false);
        view.updatePowerAllowed(true);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        this.updateViewToMatchModel();
        this.view.updateSubtractAllowed(true);
        this.view.updateDivideAllowed(false);
        this.view.updateRootAllowed(false);
        this.view.updatePowerAllowed(true);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        this.updateViewToMatchModel();
        this.view.updateSubtractAllowed(top.compareTo(bottom) >= 0);
        this.view.updateDivideAllowed(!bottom.isZero());
        this.view.updateRootAllowed(bottom.compareTo(TWO) >= 0);
        this.view.updatePowerAllowed(true);
    }

    @Override
    public void processEnterEvent() {

        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */

        top.copyFrom(bottom);

        /*
         * Update view to reflect changes in model
         */
        this.updateViewToMatchModel();
        this.view.updateSubtractAllowed(true);
        this.view.updateDivideAllowed(!bottom.isZero());
        this.view.updateRootAllowed(bottom.compareTo(TWO) >= 0);
        this.view.updatePowerAllowed(true);
    }

    @Override
    public void processAddEvent() {

        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.add(top);
        top.clear();

        /*
         * Update view to reflect changes in model
         */
        this.updateViewToMatchModel();
        this.view.updateSubtractAllowed(false);
        this.view.updateDivideAllowed(!bottom.isZero());
        this.view.updateRootAllowed(bottom.compareTo(TWO) >= 0);
        this.view.updatePowerAllowed(true);

    }

    @Override
    public void processSubtractEvent() {

        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        top.subtract(bottom);
        bottom.transferFrom(top);

        /*
         * Update view to reflect changes in model
         */
        this.updateViewToMatchModel();
        this.view.updateSubtractAllowed(false);
        this.view.updateDivideAllowed(!bottom.isZero());
        this.view.updateRootAllowed(bottom.compareTo(TWO) >= 0);
        this.view.updatePowerAllowed(true);

    }

    @Override
    public void processDivideEvent() {

        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber k = top.divide(bottom);
        bottom.transferFrom(top);
        top.transferFrom(k);

        /*
         * Update view to reflect changes in model
         */

        this.updateViewToMatchModel();

        this.view.updateSubtractAllowed(false);
        this.view.updateDivideAllowed(!bottom.isZero());
        this.view.updateRootAllowed(bottom.compareTo(TWO) >= 0);
        this.view.updatePowerAllowed(true);

    }

    @Override
    public void processMultiplyEvent() {

        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.multiply(top);
        top.clear();
        /*
         * Update view to reflect changes in model
         */
        this.updateViewToMatchModel();
        this.view.updateSubtractAllowed(false);
        this.view.updateDivideAllowed(!bottom.isZero());
        this.view.updateRootAllowed(bottom.compareTo(TWO) >= 0);
        this.view.updatePowerAllowed(true);

    }

    @Override
    public void processRootEvent() {

        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */

        top.root(bottom.toInt());
        bottom.transferFrom(top);

        /*
         * Update view to reflect changes in model
         */

        this.updateViewToMatchModel();

        this.view.updateSubtractAllowed(false);
        this.view.updateDivideAllowed(!bottom.isZero());
        this.view.updateRootAllowed(bottom.compareTo(TWO) >= 0);
        this.view.updatePowerAllowed(true);

    }

    @Override
    public void processPowerEvent() {

        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */

        top.power(bottom.toInt());
        bottom.transferFrom(top);

        /*
         * Update view to reflect changes in model
         */

        this.updateViewToMatchModel();

        this.view.updateSubtractAllowed(false);
        this.view.updateDivideAllowed(!bottom.isZero());
        this.view.updateRootAllowed(bottom.compareTo(TWO) >= 0);
        this.view.updatePowerAllowed(true);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {
        NaturalNumber bottom = this.model.bottom();
        NaturalNumber top = this.model.top();
        bottom.multiplyBy10(digit);

        this.updateViewToMatchModel();
        this.view.updateSubtractAllowed(top.compareTo(bottom) >= 0);
        this.view.updateDivideAllowed(!bottom.isZero());
        this.view.updateRootAllowed(bottom.compareTo(TWO) >= 0);
        this.view.updatePowerAllowed(true);

    }

}
