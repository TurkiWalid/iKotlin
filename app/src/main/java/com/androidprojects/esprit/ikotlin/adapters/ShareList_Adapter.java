package com.androidprojects.esprit.ikotlin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.models.ForumPost;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Amal on 24/11/2017.
 */

public class ShareList_Adapter extends BaseAdapter implements Filterable{

    private final Context context;
    private final ForumPost[] posts;
    private  ValueFilter valueFilter;

    public ShareList_Adapter(Context context, ForumPost[] posts){
        this.context=context;
        this.posts=posts;
    }


    @Override
    public int getCount() {
        return posts.length;
    }

    @Override
    public Object getItem(int position) {
        return posts[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView= ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                      .inflate(R.layout.share_list_item, parent, false);
        ((TextView) rowView.findViewById(R.id.postTile)).setText(posts[position].getTitle());
        Picasso.with(context).load(posts[position].getPostedBy_picUrl()).into((ImageView)rowView.findViewById(R.id.postedBy_img));
        ((TextView) rowView.findViewById(R.id.nbVotes)).setText("+" + String.valueOf(posts[position].getNbvotes()));
        ((TextView) rowView.findViewById(R.id.nbComments)).setText(String.valueOf(posts[position].getNbComments()));
        return rowView;
    }


    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<ForumPost> filterList = new ArrayList<ForumPost>();
                for (int i = 0; i < posts.length; i++) {
                    if ( (posts[i].getTitle().toUpperCase() )
                            .contains(constraint.toString().toUpperCase())) {
                        ForumPost res = posts[i];
                        filterList.add(res);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = posts.length;
                results.values = posts;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            //filterList = (ArrayList<Country>) results.values;
            notifyDataSetChanged();
        }

    }
}
