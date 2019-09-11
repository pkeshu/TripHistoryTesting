package com.keshar.triphistorytesting.Presenter;

import androidx.annotation.NonNull;

import com.keshar.triphistorytesting.Contract.MainContract;
import com.keshar.triphistorytesting.Model.TripHistoryResponseModel;
import com.keshar.triphistorytesting.Services.ApiInterface;

import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class MainPresenter implements MainContract.Presenter {

    @NonNull
    private ApiInterface apiInterface;

    @NonNull
    private Scheduler backgroundScheduler;
    @NonNull
    private Scheduler mainScheduler;

    @NonNull
    private CompositeSubscription subscription;

    private MainContract.View view;

    public MainPresenter(@NonNull ApiInterface apiInterface, @NonNull Scheduler backgroundScheduler, @NonNull Scheduler mainScheduler, MainContract.View view) {
        this.apiInterface = apiInterface;
        this.backgroundScheduler = backgroundScheduler;
        this.mainScheduler = mainScheduler;
        this.view = view;
        subscription=new CompositeSubscription();
    }

    @Override
    public void loadData() {

        view.onFetchDataStarted();
        subscription.clear();

        Subscription subscription1 = apiInterface.insertTripHistory(0l, null)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<TripHistoryResponseModel>() {
                    @Override
                    public void onCompleted() {
                        view.onFetchDataCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFetchDataError(e);

                    }

                    @Override
                    public void onNext(TripHistoryResponseModel tripHistoryResponseModel) {
                        view.onFetchDataSuccess(tripHistoryResponseModel);
                    }
                });

        subscription.add(subscription1);

    }

    @Override
    public void subscribe() {
        loadData();

    }

    @Override
    public void unsubscribe() {
        subscription.clear();

    }

    @Override
    public void onDestroy() {
        this.view = null;

    }
}
