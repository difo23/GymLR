package com.smartphone.lizandro.gymlr.mvp.view;

import com.smartphone.lizandro.gymlr.mvp.model.Partido;
import com.smartphone.lizandro.gymlr.mvp.presenter.DashboardPresenter;


public interface IDashboardView {
    public void load_Partidos(int id);

    void refreshUI(Partido partido);
}
