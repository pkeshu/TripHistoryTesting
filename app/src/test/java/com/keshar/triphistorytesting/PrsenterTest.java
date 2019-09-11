package com.keshar.triphistorytesting;

import com.keshar.triphistorytesting.Contract.MainContract;
import com.keshar.triphistorytesting.Model.TripHistoryResponseModel;
import com.keshar.triphistorytesting.Presenter.MainPresenter;
import com.keshar.triphistorytesting.Services.ApiInterface;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.InOrderImpl;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static retrofit2.adapter.rxjava.Result.error;

public class PrsenterTest {

    @Mock
    private ApiInterface apiInterface;

    @Mock
    private MainContract.View view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fetchValidDataShouldIntoView() {

        TripHistoryResponseModel tripHistoryResponseModel = new TripHistoryResponseModel(false, null);
        when(apiInterface.insertTripHistory(0l, null))
                .thenReturn(Observable.just(tripHistoryResponseModel));

        MainPresenter mainPresenter = new MainPresenter(
                this.apiInterface,
                Schedulers.immediate(),
                Schedulers.immediate(),
                this.view
        );
        mainPresenter.loadData();

        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).onFetchDataStarted();
        inOrder.verify(view, times(1)).onFetchDataSuccess(tripHistoryResponseModel);
        inOrder.verify(view, times(1)).onFetchDataCompleted();
    }

    @Test
    public void fetchErrorShouldReturnErrorToView() {
        Exception exception = new Exception();
        when(apiInterface.insertTripHistory(0l, null))
                .thenReturn(Observable.<TripHistoryResponseModel>error(exception));
        MainPresenter mainPresenter = new MainPresenter(
                this.apiInterface,
                Schedulers.immediate(),
                Schedulers.immediate(),
                this.view
        );
        mainPresenter.loadData();
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).onFetchDataStarted();
        inOrder.verify(view, times(1)).onFetchDataError(exception);
        verify(view,never()).onFetchDataCompleted();
    }
}
