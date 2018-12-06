package com.ezyplanet.thousandhands.driver.ui.fragment.accountkit;

import android.app.Fragment;
import android.os.Parcel;
import android.os.Parcelable;

import com.ezyplanet.thousandhands.driver.R;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.ui.AdvancedUIManager;
import com.facebook.accountkit.ui.ButtonType;
import com.facebook.accountkit.ui.LoginFlowState;
import com.facebook.accountkit.ui.TextPosition;

import androidx.annotation.Nullable;

import static com.facebook.accountkit.ui.LoginFlowState.CODE_INPUT;

public class AccountKitAdvancedUIManager implements AdvancedUIManager, Parcelable {

    private static final int HEADER_HEIGHT = 80;


    private AccountKitError error;

    private AdvancedUIManagerListener listener;


    @Override
    @Nullable
    public Fragment getActionBarFragment(final LoginFlowState state) {

        return null;
    }

    @Override
    @Nullable
    public Fragment getBodyFragment(final LoginFlowState state) {
        return null;
    }

    @Override
    @Nullable
    public ButtonType getButtonType(final LoginFlowState state) {
        return null;
    }

    @Override
    @Nullable
    public Fragment getFooterFragment(final LoginFlowState state) {
        return null;
    }

    @Override
    @Nullable
    public Fragment getHeaderFragment(final LoginFlowState state) {
        if (state != LoginFlowState.ERROR) {
            return getPlaceholderFragment(state, HEADER_HEIGHT, "Header");
        }
        final String errorMessage = getErrorMessage();
        if (errorMessage == null) {
            return PlaceholderFragment.create(HEADER_HEIGHT, R.string.enter_the_verification_code);
        } else {
            return PlaceholderFragment.create(HEADER_HEIGHT, errorMessage);
        }
    }

    @Override
    @Nullable
    public TextPosition getTextPosition(final LoginFlowState state) {
        return null;
    }

    @Override
    public void setAdvancedUIManagerListener(final AdvancedUIManagerListener listener) {
        this.listener = listener;
    }

    @Override
    public void onError(final AccountKitError error) {
        this.error = error;
    }

    private String getErrorMessage() {
        if (error == null) {
            return null;
        }

        final String message = error.getUserFacingMessage();
        if (message == null) {
            return null;
        }

        return message;
    }

    @Nullable
    private PlaceholderFragment getPlaceholderFragment(
            final LoginFlowState state,
            final int height,
            final String suffix) {

        if(state.equals(CODE_INPUT)) {
            return PlaceholderFragment.create(height, R.string.enter_the_verification_code);
        }else{
            return null;
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {

    }

    public static final Creator<AccountKitAdvancedUIManager> CREATOR
            = new Creator<AccountKitAdvancedUIManager>() {
        @Override
        public AccountKitAdvancedUIManager createFromParcel(final Parcel source) {

            return new AccountKitAdvancedUIManager();

        }

        @Override
        public AccountKitAdvancedUIManager[] newArray(final int size) {
            return new AccountKitAdvancedUIManager[size];
        }
    };
}
