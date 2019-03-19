package com.shaurya.lal10catalogue;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.SearchProductAdapterViewHolder> implements Filterable {

    Context mCntx;
    public ArrayList<Product> arrayList;
    public ArrayList<Product> arrayListFiltered;
    private DatabaseReference mDatabaseRef;

    //mDatabaseRef= FirebaseDatabase.getInstance().getReference().child("Products");

    public SearchProductAdapter(Context mCntx, ArrayList<Product> arrayList,String text)
    {
        this.mCntx = mCntx;
        this.arrayList = arrayList;
        this.arrayListFiltered = new ArrayList<>(arrayList);
        Query mQuerySearch = mDatabaseRef.orderByChild("name").equalTo(text);

        FirebaseRecyclerAdapter s=new FirebaseRecyclerAdapter(
                Product.class,
                R.layout.product_row,
                GetAllProductsCardLayout.ProductViewHolder.class,
                mQuerySearch
        ) {
            @Override
            protected void populateViewHolder(RecyclerView.ViewHolder viewHolder, Object model, int position) {

            }
        };
    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public SearchProductAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_get_all_products_card_layout, parent, false);

        SearchProductAdapterViewHolder viewHolder = new SearchProductAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchProductAdapterViewHolder holder, final int position)
    {
        final Product place = arrayList.get(position);

        holder.product_name.setText(arrayList.get(position).getName());

        //Picasso.with(mCntx).load(image).into();//using picasso to load image

        holder.mview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                /*Intent intent = new Intent(mCntx, CardviewSearch.class);
                intent.putExtra("placeId", String.valueOf(place.getPlaceId()));
                intent.putExtra("building", String.valueOf(place.getBuilding()));
                intent.putExtra("street", String.valueOf(place.getStreet()));
                intent.putExtra("imgurl", String.valueOf(place.getPlaceImg()));
                mCntx.startActivity(intent);*/

               // Toast.makeText(this,"In onclick",Toast.LENGTH_LONG).show();

            }
        });
    }


    public class SearchProductAdapterViewHolder extends RecyclerView.ViewHolder
    {
        View mview;

        TextView product_name;

        Switch addtopdfswitch;

        public SearchProductAdapterViewHolder(View itemView) {
            super(itemView);
            mview=itemView;

            addtopdfswitch=mview.findViewById(R.id.addtopdf);

            product_name=mview.findViewById(R.id.product_name);

            product_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("ProductsCardLayout","Some Text");
                }
            });
        }
    }

    public Filter getFilter()
    {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Product> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(arrayListFiltered);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Product item : arrayListFiltered) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            arrayList.clear();
            arrayList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}