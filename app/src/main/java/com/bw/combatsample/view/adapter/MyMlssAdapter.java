package com.bw.combatsample.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.combatsample.R;
import com.bw.combatsample.model.bean.Lawyer;
import com.bw.combatsample.util.NetUtil;

import java.util.List;

public class MyMlssAdapter extends RecyclerView.Adapter<MyMlssAdapter.MyViewHolder> {


    private List<Lawyer.ResultBean.MlssBean.CommodityListBeanXX> commodityList;

    public MyMlssAdapter(List<Lawyer.ResultBean.MlssBean.CommodityListBeanXX> commodityList) {

        this.commodityList = commodityList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // TODO: 2019/12/6 加载布局、创建适配器
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mlss, viewGroup, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        // TODO: 2019/12/6 绑定数据
        Lawyer.ResultBean.MlssBean.CommodityListBeanXX commodityListBeanXX = commodityList.get(i);

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
