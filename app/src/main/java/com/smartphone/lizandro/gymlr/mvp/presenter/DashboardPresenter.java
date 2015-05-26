package com.smartphone.lizandro.gymlr.mvp.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.smartphone.lizandro.gymlr.ApplicationClass;
import com.smartphone.lizandro.gymlr.GymActivity;
import com.smartphone.lizandro.gymlr.mvp.model.Equipo;
import com.smartphone.lizandro.gymlr.mvp.view.IDashboardView;
import com.smartphone.lizandro.gymlr.rest.MarcadoresInterface;
import com.smartphone.lizandro.gymlr.rest.RestClient;
import com.smartphone.lizandro.gymlr.rest.model.Partido;
import dagger.Module;
import dagger.ObjectGraph;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

import javax.inject.Inject;
import java.util.List;


public class DashboardPresenter {

    @Inject
    MarcadoresInterface restClient;

    private IDashboardView view;

    public void setView(IDashboardView dashboardView) {
        this.view = dashboardView;
    }

    public void loadPartidos() {
        loadPartidoLiga(87);
        //loadPartidoLiga(140);
    }

    private void loadPartidoLiga(int ligaId) {
        restClient.getPartidos(ligaId)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<List<Partido>, Observable<Partido>>() {
                    @Override
                    public Observable<Partido> call(final List<Partido> partidos) {
                        return Observable.create(new Observable.OnSubscribe<Partido>() {
                            @Override
                            public void call(Subscriber<? super Partido> subscriber) {
                                for (Partido partido : partidos)
                                    subscriber.onNext(partido);
                                subscriber.onCompleted();

                            }
                        });
                    }
                })
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Partido>>() {
                    @Override
                    public Observable<? extends Partido> call(Throwable throwable) {
                        return Observable.error(new Throwable("Error connection"));
                    }
                })
                .subscribe(new Action1<Partido>() {
                               @Override
                               public void call(Partido partido) {
                                   //Equipo local = new Equipo(partido.getParticipantes().get1(), partido.getIdEHome());
                                   //Equipo visitante = new Equipo(partido.getParticipantes().get2(), partido.getIdEAway());
                                   //com.eui.futbol.mvp.model.Partido p = new com.eui.futbol.mvp.model.Partido(local, visitante);
                                   //p.setGolesLocal(partido.getCurrentL());
                                   //p.setGolesVisitante(partido.getCurrentV());
                                   com.smartphone.lizandro.gymlr.mvp.model.Partido p = new com.smartphone.lizandro.gymlr.mvp.model.Partido(partido);
                                   view.refreshUI(p);
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.e("....", "error " + throwable.getMessage());
                                view.refreshUI(null);
                            }
                        });
    }


}
