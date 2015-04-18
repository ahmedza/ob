package com.onebridge.adapter;

import java.util.List;

import com.onebridge.model.ProductCategory;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ListAdapterImageAndTextField extends ArrayAdapter<ProductCategory> {

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return super.getView(position, convertView, parent);
	}
	
	public ListAdapterImageAndTextField(Context context, int resource,
			ProductCategory[] objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

	public ListAdapterImageAndTextField(Context context, int resource,
			List<ProductCategory> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

	public ListAdapterImageAndTextField(Context context, int resource,
			int textViewResourceId, ProductCategory[] objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	public ListAdapterImageAndTextField(Context context, int resource,
			int textViewResourceId, List<ProductCategory> objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	public ListAdapterImageAndTextField(Context context, int resource,
			int textViewResourceId) {
		super(context, resource, textViewResourceId);
		// TODO Auto-generated constructor stub
	}

	public ListAdapterImageAndTextField(Context context, int resource) {
		super(context, resource);
		// TODO Auto-generated constructor stub
	}

}
