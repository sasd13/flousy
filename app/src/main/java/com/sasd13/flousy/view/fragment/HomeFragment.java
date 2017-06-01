package com.sasd13.flousy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.EnumRecyclerType;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.grid.EnumGridItemType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.R;
import com.sasd13.flousy.activity.MainActivity;
import com.sasd13.flousy.scope.HomeScope;
import com.sasd13.flousy.view.IBrowsable;
import com.sasd13.flousy.view.IHomeController;
import com.sasd13.flousy.view.gui.browser.Browser;
import com.sasd13.flousy.view.gui.browser.BrowserItemModel;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class HomeFragment extends Fragment implements Observer {

    private IHomeController controller;
    private HomeScope scope;
    private Recycler recycler;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IHomeController) ((MainActivity) getActivity()).lookup(IHomeController.class);
        scope = (HomeScope) controller.getScope();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        scope.addObserver(this);

        View view = inflater.inflate(R.layout.layout_home, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildGrid(view);
        bindGrid();
        run();
    }

    private void buildGrid(View view) {
        recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.GRID).build((RecyclerView) view.findViewById(R.id.home_recyclerview));
    }

    private void bindGrid() {
        List<BrowserItemModel> browserItemModels = new Browser(getContext()).getNavItems();
        RecyclerHolder recyclerHolder = new RecyclerHolder();
        RecyclerHolderPair pair;

        for (final BrowserItemModel browserItemModel : browserItemModels) {
            pair = new RecyclerHolderPair(browserItemModel);

            browserItemModel.setItemType(EnumGridItemType.GRID);
            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    ((IBrowsable) ((MainActivity) getActivity()).lookup(browserItemModel.getTarget())).browse();
                }
            });

            recyclerHolder.add(pair);
        }

        RecyclerHelper.addAll(recycler, recyclerHolder);
    }

    private void run() {
        controller.run();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (scope.isWelcomed()) {
            showWelcome();
        }
    }

    private void showWelcome() {
        StringBuilder builder = new StringBuilder();
        builder.append(getString(R.string.home_alertdialog_message_welcome));
        builder.append(" ");
        builder.append(scope.getCustomer().getFirstName());
        builder.append(" !");

        OptionDialog.showOkDialog(getContext(), getString(R.string.home_alertdialog_title_welcome), builder.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        scope.deleteObserver(this);
    }
}