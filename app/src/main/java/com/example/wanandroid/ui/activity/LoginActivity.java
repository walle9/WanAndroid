package com.example.wanandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseMvpActivity;
import com.example.wanandroid.contract.LoginActivityContract;
import com.example.wanandroid.model.bean.LoginBean;
import com.example.wanandroid.model.event.LoginEvent;
import com.example.wanandroid.presenter.LoginActivityPresenter;
import com.example.wanandroid.utils.UIUtils;
import com.google.android.material.textfield.TextInputEditText;

import org.greenrobot.eventbus.EventBus;

public class LoginActivity extends BaseMvpActivity<LoginActivityPresenter> implements LoginActivityContract.IView {

    private TextInputEditText mEtLoginUsername;
    private TextInputEditText mEtLoginPassword;
    private Button mBtnLogin;

    public static void actionStartActivity(ContextThemeWrapper context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        LoginTextWatcher loginTextWatcher = new LoginTextWatcher();

        mBtnLogin = findViewById(R.id.btn_login);
        mEtLoginUsername = findViewById(R.id.et_login_username);
        mEtLoginPassword = findViewById(R.id.et_login_password);

        toolbar.setTitle(getResources().getString(R.string.sign_in));
        mEtLoginUsername.addTextChangedListener(loginTextWatcher);
        mEtLoginPassword.addTextChangedListener(loginTextWatcher);
        mEtLoginPassword.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login();
            }
            return false;
        });
        setOnClick(mBtnLogin);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
            login();
            UIUtils.hideKeyboard(this, mEtLoginUsername);
        }
    }

    private void login() {
        String username = UIUtils.getTextContent(mEtLoginUsername);
        String password = UIUtils.getTextContent(mEtLoginPassword);

        if (TextUtils.isEmpty(username)) {
            mEtLoginUsername.requestFocus();
            UIUtils.showToast(getString(R.string.invalid_username));
        } else if (TextUtils.isEmpty(password)) {
            mEtLoginPassword.requestFocus();
            UIUtils.showToast(getString(R.string.invalid_password));
        } else {
            basePresenter.login(username, password);
        }

    }

    @Override
    public void loginSuccessful(LoginBean loginBean) {
        myFinish();
        EventBus.getDefault().post(new LoginEvent(true));
    }

    @Override
    public boolean isShowToolbar() {
        return true;
    }

    @Override
    public boolean swipeEnable() {
        return true;
    }

    class LoginTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            boolean isEnable = false;
            if (!TextUtils.isEmpty(mEtLoginUsername.getText()) && !TextUtils.isEmpty(mEtLoginPassword.getText())) {
                isEnable = true;
            }
            UIUtils.setViewEnable(mBtnLogin, isEnable);
        }
    }

}
