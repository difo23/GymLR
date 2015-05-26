package com.smartphone.lizandro.gymlr.contexts;

import com.smartphone.lizandro.gymlr.ApplicationClass;
import com.smartphone.lizandro.gymlr.GymActivity;
import com.smartphone.lizandro.gymlr.mvp.presenter.DashboardPresenter;
import dagger.Module;

@Module(
        injects = { ApplicationClass.class,
                GymActivity.class,
                DashboardPresenter.class},
        library = true,
        complete = false
)
public class GlobalModule {
}
