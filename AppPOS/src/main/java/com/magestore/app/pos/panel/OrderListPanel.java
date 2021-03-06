package com.magestore.app.pos.panel;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.magestore.app.lib.model.sales.Order;
import com.magestore.app.lib.panel.AbstractListPanel;
import com.magestore.app.pos.R;
import com.magestore.app.pos.databinding.CardOrderHistoryListContentBinding;

import java.util.List;

/**
 * Panel hiển thị danh sách đơn hàng chi tiết
 * Created by Mike on 1/9/2017.
 * Magestore
 * mike@trueplus.vn
 */

public class OrderListPanel extends AbstractListPanel<Order> {
    // Danh sách đơn hàng
    List<Order> mListOrder;

    /**
     * Khởi tạo
     *
     * @param context
     */
    public OrderListPanel(Context context) {
        super(context);
    }

    /**
     * Khởi tạo
     *
     * @param context
     * @param attrs
     */
    public OrderListPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Khởi tạo
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public OrderListPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void initLayout() {
        // Load layout view danh sách đơn hàng
        View v = inflate(getContext(), R.layout.panel_order_list, null);
        addView(v);

        // Xử lý sự kiện floating action bar
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Chuẩn bị layout từng order trong danh sách khách hàng
        setLayoutItem(R.layout.card_order_history_list_content);

        // Chuẩn bị list danh sách đơn hàng
        initRecycleView(R.id.order_list, new GridLayoutManager(this.getContext(), 1));
    }

    @Override
    protected void bindItem(View view, Order item, int position) {
        CardOrderHistoryListContentBinding biding = DataBindingUtil.bind(view);
        biding.setOrder(item);

        ImageView im_status = (ImageView) view.findViewById(R.id.im_status);
        im_status.setImageResource(R.drawable.ic_order_status);

        String status = item.getStatus().toLowerCase();
        changeColorStatusOrder(status, im_status);
    }

    private void changeColorStatusOrder(String status, ImageView im_status) {
        switch (status) {
            case "pending":
                im_status.setColorFilter(ContextCompat.getColor(getContext(), R.color.order_status_pending));
                break;
            case "processing":
                im_status.setColorFilter(ContextCompat.getColor(getContext(), R.color.order_status_processing));
                break;
            case "complete":
                im_status.setColorFilter(ContextCompat.getColor(getContext(), R.color.order_status_complete));
                break;
            case "cancelled":
                im_status.setColorFilter(ContextCompat.getColor(getContext(), R.color.order_status_cancelled));
                break;
            case "closed":
                im_status.setColorFilter(ContextCompat.getColor(getContext(), R.color.order_status_closed));
                break;
            case "not_sync":
                im_status.setColorFilter(ContextCompat.getColor(getContext(), R.color.order_status_notsync));
                break;
        }
    }

    /**
     * Chuẩn bị layout giá trị
     */
    @Override
    public void initValue() {

    }

    @Override
    public void initModel() {

    }
}
