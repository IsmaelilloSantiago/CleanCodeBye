package es.ulpgc.eite.cleancode.helloworld.Bye;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.helloworld.app.AppMediator;
import es.ulpgc.eite.cleancode.helloworld.app.ByeToHelloState;
import es.ulpgc.eite.cleancode.helloworld.app.HelloToByeState;

public class byePresenter implements byeContract.Presenter {

    public static String TAG = byePresenter.class.getSimpleName();

    private WeakReference<byeContract.View> view;
    private byeState state;
    private byeContract.Model model;
    private AppMediator mediator;

    public byePresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getbyeState();
    }

    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");
        //Log.w("llegos","67");

        // initialize the state if is necessary
        if (state == null) {
            state = new byeState();
        }

        // call the model and update the state view.get().displayCheatData(state);
        state.byeMessage = model.getStoredData();

        // use passed state if is necessary
        HelloToByeState savedState = getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromPreviousScreen(savedState.message);

            // update the state if is necessary
            state.byeMessage = savedState.message;
        }
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");

        // update the model if is necessary
        model.onRestartScreen(state.byeMessage);
    }

    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary
        //-NextTobyeState savedState = getStateFromNextScreen();
        ByeToHelloState savedState = getStateFromNextScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromNextScreen(savedState.message);

            // update the state if is necessary
            state.byeMessage = savedState.message;
        }

        // call the model and update the state
        //state.byeMessage = model.getStoredData();

        // update the view
        view.get().onDataUpdated(state);

    }

    @Override
    public void onBackPressed() {
        // Log.e(TAG, "onBackPressed()");
        getStateFromPreviousScreen();

    }

    @Override
    public void onPause() {
        // Log.e(TAG, "onPause()");
    }

    @Override
    public void onDestroy() {
        // Log.e(TAG, "onDestroy()");
    }

    @Override
    public void goHelloBotonClicked() {
        ByeToHelloState newState = new ByeToHelloState(state.byeMessage);
        passDataToHelloScreen(newState);
        navigateToHelloScreen();


    }

    @Override
    public void sayByeBotonClicked() {
        state.byeMessage = "?";
        view.get().onDataUpdated(state);
        startByeMessageAsyncTask();
    }

    private void startByeMessageAsyncTask() {
        state.byeMessage = model.getStoredData();
        Log.w("conejoo",state.byeMessage);
        view.get().onDataUpdated(state);
    }

    private void navigateToHelloScreen() {
        view.get().navigateToNextScreen();
    }

    private void passDataToHelloScreen(ByeToHelloState newState) {
        mediator.setByeToHelloState(newState);
    }

    private ByeToHelloState getStateFromNextScreen() {

        return mediator.getByeToHelloState();
    }

    private void passStateToNextScreen(ByeToHelloState state) {
        mediator.setByeToHelloState(state);
    }

    private void passStateToPreviousScreen(HelloToByeState state) {
        mediator.setHelloToByeState(state);
    }

    private HelloToByeState getStateFromPreviousScreen() {
        return mediator.getHelloToByeState();
    }

    @Override
    public void injectView(WeakReference<byeContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(byeContract.Model model) {
        this.model = model;
    }

}