package com.sasd13.flousy.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.sasd13.androidex.activity.DrawerActivity;
import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.drawer.EnumDrawerItemType;
import com.sasd13.flousy.Configurator;
import com.sasd13.flousy.R;
import com.sasd13.flousy.Router;
import com.sasd13.flousy.bean.user.User;
import com.sasd13.flousy.service.IUserStorageService;
import com.sasd13.flousy.util.Constants;
import com.sasd13.flousy.view.IBrowsable;
import com.sasd13.flousy.view.IController;
import com.sasd13.flousy.view.fragment.HomeFragment;
import com.sasd13.flousy.view.gui.browser.Browser;
import com.sasd13.flousy.view.gui.browser.BrowserItemModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends DrawerActivity {

    private Router router;
    private IPagerHandler pagerHandler;

    public void setPagerHandler(IPagerHandler pagerHandler) {
        this.pagerHandler = pagerHandler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_container);

        init();
        startHomeFragment();
    }

    private void init() {
        Configurator.Config config = Configurator.init(this);
        router = config.getRouter();

        if (getIntent().hasExtra(Constants.USER)) {
            User user = getIntent().getExtras().getParcelable(Constants.USER);

            ((IUserStorageService) config.getProvider().provide(IUserStorageService.class)).write(user);
            getIntent().removeExtra(Constants.USER);
        }
    }

    private void startHomeFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, HomeFragment.newInstance())
                .commit();
    }

    public void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public IController lookup(Class mClass) {
        return router.navigate(mClass, this);
    }

    @Override
    protected RecyclerHolder getDrawerHolder() {
        RecyclerHolder recyclerHolder = new RecyclerHolder();
        Browser browser = new Browser(this);

        addNavItems(recyclerHolder, browser.getNavItems());
        addAccountItems(recyclerHolder, browser.getAccountItems());

        return recyclerHolder;
    }

    private void addNavItems(RecyclerHolder recyclerHolder, List<BrowserItemModel> navItemsModels) {
        List<RecyclerHolderPair> pairs = makeItems(navItemsModels);

        recyclerHolder.addAll(getString(R.string.drawer_header_menu), pairs);
    }

    @NonNull
    private List<RecyclerHolderPair> makeItems(List<BrowserItemModel> browserItemModels) {
        List<RecyclerHolderPair> pairs = new ArrayList<>();
        RecyclerHolderPair pair;

        for (final BrowserItemModel browserItemModel : browserItemModels) {
            pair = new RecyclerHolderPair(browserItemModel);

            browserItemModel.setItemType(EnumDrawerItemType.DRAWER);
            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    ((IBrowsable) lookup(browserItemModel.getTarget())).browse();
                    setDrawerOpened(false);
                }
            });

            pairs.add(pair);
        }

        return pairs;
    }

    private void addAccountItems(RecyclerHolder recyclerHolder, List<BrowserItemModel> accountItemsModels) {
        List<RecyclerHolderPair> pairs = makeItems(accountItemsModels);

        recyclerHolder.addAll(getString(R.string.drawer_header_account), pairs);
    }

    @Override
    public void onBackPressed() {
        if (pagerHandler == null || !pagerHandler.handleBackPress()) {
            super.onBackPressed();
        }
    }

    public void clearHistory() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
