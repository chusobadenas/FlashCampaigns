package com.flashcampaigns.app.presentation.base;

import com.flashcampaigns.app.common.exception.ErrorBundle;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

  private T mvpView;

  @Override
  public void attachView(T mvpView) {
    this.mvpView = mvpView;
  }

  @Override
  public void detachView() {
    mvpView = null;
  }

  private boolean isViewAttached() {
    return mvpView != null;
  }

  public T getMvpView() {
    return mvpView;
  }

  public void checkViewAttached() {
    if (!isViewAttached()) {
      throw new MvpViewNotAttachedException();
    }
  }

  public void showErrorMessage(ErrorBundle errorBundle) {
    mvpView.showError(errorBundle.getErrorMessage());
  }

  private static class MvpViewNotAttachedException extends RuntimeException {

    MvpViewNotAttachedException() {
      super("Please call Presenter.attachView(MvpView) before requesting data to the Presenter");
    }
  }
}
