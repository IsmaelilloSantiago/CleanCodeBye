package es.ulpgc.eite.cleancode.helloworld.Bye;

import java.lang.ref.WeakReference;

public interface byeContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(byeViewModel viewModel);

        void navigateToNextScreen();


        void displayByeMessage(byeViewModel byeViewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void onBackPressed();

        void onPause();

        void onDestroy();

        void goHelloBotonClicked();

        void sayByeBotonClicked();
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);
    }

}