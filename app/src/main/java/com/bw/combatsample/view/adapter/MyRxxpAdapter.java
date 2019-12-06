package com.bw.combatsample.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.combatsample.R;
import com.bw.combatsample.model.bean.Lawyer;
import com.bw.combatsample.util.NetUtil;

import java.util.List;

public class MyRxxpAdapter extends RecyclerView.Adapter<MyRxxpAdapter.MyViewHolder> {


    private List<Lawyer.ResultBean.RxxpBean.CommodityListBean> commodityList;

    public MyRxxpAdapter(List<Lawyer.ResultBean.RxxpBean.CommodityListBean> commodityList) {
        this.commodityList = commodityList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // TODO: 2019/12/6 加载布局、创建适配器
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rxxp, viewGroup, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "点击了第" +i +"条", Toast.LENGTH_SHORT).show();
            }
        });

        // TODO: 2019/12/6 绑定数据
        Lawyer.ResultBean.RxxpBean.CommodityListBean commodityListBeanXX = commodityList.get(i);

        myViewHolder.name.setText(commodityListBeanXX.getCommodityName());
        myViewHolder.price.setText(commodityListBeanXX.getPrice() + "");
        NetUtil.getInstance().getPhoto(commodityListBeanXX.getMasterPic(), myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.tv_name);
            price = itemView.findViewById(R.id.tv_price);
        }
    }
}
