package com.magestore.app.pos;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.magestore.app.lib.context.MagestoreContext;
import com.magestore.app.lib.service.ServiceFactory;
import com.magestore.app.lib.service.catalog.ProductService;
import com.magestore.app.lib.service.checkout.CartService;
import com.magestore.app.pos.controller.CartItemListController;
import com.magestore.app.pos.controller.ProductListController;
import com.magestore.app.pos.panel.CartItemListPanel;
import com.magestore.app.pos.panel.ProductListPanel;
import com.magestore.app.pos.ui.AbstractActivity;
import com.magestore.app.view.ui.PosUI;

/**
 * Quản lý các hoạt động salé
 * Tạo sales mới, hold và check out thanh toán
 */
public class SalesActivity extends AbstractActivity
        implements
        PosUI {
    // 2 pane
    private boolean mTwoPane;

    // Panel chứa danh sách mặt hàng và đơn hàng
    private ProductListPanel mProductListPanel;
    private CartItemListPanel mCartItemListPanel;

    // controller cho danh sách mặt hàng và đơn hàng
    private ProductListController mProductListController;
    private CartItemListController mCartItemListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_menu);
        // chuẩn bị control layout
        initLayout();

        // chuẩn bị các model
        initModel();

        // chuản bị các value trong layout
        initValue();
    }

    @Override
    protected void initLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_category);
        if (toolbar == null) toolbar = (Toolbar) findViewById(R.id.toolbar_order);
        initToolbarMenu(toolbar);

        // Nút tab để tạo đơn hàng mới
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Xác định là 2 pane hay 1 pane
        if (findViewById(R.id.toolbar_category) != null) {
            mTwoPane = true;
        }

        mProductListPanel = (ProductListPanel) findViewById(R.id.product_list_panel);
        mProductListPanel.setColumn(mTwoPane ? 4 : 1);

        mCartItemListPanel = (CartItemListPanel) findViewById(R.id.order_item_panel);
        if (mCartItemListPanel == null) return;
    }

    protected void initModel() {
        // Context sử dụng xuyên suốt hệ thống
        MagestoreContext magestoreContext = new MagestoreContext();
        magestoreContext.setActivity(this);

        // chuẩn bị service
        ServiceFactory factory;
        ProductService productService = null;
        CartService cartService = null;

        try {
            factory = ServiceFactory.getFactory(magestoreContext);
            productService = factory.generateProductService();
            cartService = factory.generateCartService();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        // controller quản lý đơn hàng
        mCartItemListController = new CartItemListController();
        mCartItemListController.setMagestoreContext(magestoreContext);
        mCartItemListController.setCartService(cartService);
        mCartItemListController.setListPanel(mCartItemListPanel);

        // controller quản lý danh sách khách hàng
        mProductListController = new ProductListController();
        mProductListController.setMagestoreContext(magestoreContext);
        mProductListController.setProdcutService(productService);
        mProductListController.setListPanel(mProductListPanel);
        mProductListController.setOrderItemListController(mCartItemListController);
    }

    @Override
    protected void initValue() {
        // Load danh sách sản phẩm, không tự động chọn sản phẩm đầu tiên
        mProductListController.doLoadData(false);

        // TODO: Tạm thời new activity thì tạo checkout mới luôn
        mCartItemListController.newSales();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        boolean navMenu = super.onNavigationItemSelected(item);

        // close drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return navMenu;
    }

    /**
     * Hiển thị progress bar lúc load danh sách lần đầu
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//                int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
//
//                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//                mLoginFormView.animate().setDuration(shortAnimTime).alpha(
//                        show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//                    }
//                });
//
//                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//                mProgressView.animate().setDuration(shortAnimTime).alpha(
//                        show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//                    }
//                });
//            } else {
//                // The ViewPropertyAnimator APIs are not available, so simply show
//                // and hide the relevant UI components.
//                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//            }
    }
}
