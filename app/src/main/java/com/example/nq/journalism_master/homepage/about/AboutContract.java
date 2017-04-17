package com.example.nq.journalism_master.homepage.about;

/**
 * Created by Administrator on 2017/4/13.
 */
public interface AboutContract {

    interface View extends  BaseView<Presenter> {

        void showRateError();

        void showFeedbackError();

        void showBrowserNotFoundError();
    }

    interface Presenter extends BasePresenter {

        void rate();

        void openLicense();

        void followOnGithub();

        void followOnZhihu();

        void feedback();

        void donate();

        void showEasterEgg();
    }
}
