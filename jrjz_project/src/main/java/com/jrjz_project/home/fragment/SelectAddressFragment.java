package com.jrjz_project.home.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BasePullFragment;
import com.jrjz_project.common.widget.FullyLinearLayoutManager;
import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;

import butterknife.Bind;

/**
 * Created by John_Libo on 2016/8/18.
 */
public class SelectAddressFragment extends BasePullFragment {
    @Bind(R.id.select_list)
    RecyclerView mSelectList;
    BaseSimpleRecyclerAdapter mSelectListAdapter;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_selectaddress;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mSelectList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
//        mSelectListAdapter=new BaseSimpleRecyclerAdapter<SelectEntity>() {
//            @Override
//            public int getItemViewLayoutId() {
//                return R.layout.item_select_address;
//            }
//
//            @Override
//            public void bindData(BaseRecyclerViewHolder holder, SelectEntity selectEntity, int position) {
//
//                holder.setText(R.id.send_province,selectEntity.getProvinCity());
//
//
//            }
//
//
//        };
        mSelectList.setAdapter(mSelectListAdapter);
        mSelectListAdapter.setOnItemClickListener(new BaseSimpleRecyclerAdapter.OnRecyclerViewItemClickListener(){

            @Override
            public void onItemClick(View itemView, Object itemBean, int position) {

            }
        });


    }

    @Override
    public void initData() {
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
//            case R.id.select_address:
//                break;

        }
    }
}
