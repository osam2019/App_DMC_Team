/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.milcam.deep.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.milcam.deep.R;
import com.milcam.deep.model.Message;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {

	private static String roomID;
	private static String realname;
	private static ViewPager viewPager;
	private static ArrayList<Message> imgList = new ArrayList<>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		roomID = getIntent().getStringExtra("roomID");
		realname = getIntent().getStringExtra("realname");

		viewPager = findViewById(R.id.view_pager);
		viewPager.setAdapter(new SamplePagerAdapter());

        ActionBar actionBar = getSupportActionBar();
        //actionBar.setIcon(R.drawable.back);
        actionBar.setTitle("PhotoView");
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				onBackPressed();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	static class SamplePagerAdapter extends PagerAdapter {
		private StorageReference storageReference;
		private int inx = -1;

		public SamplePagerAdapter() {
			storageReference  = FirebaseStorage.getInstance().getReference();

			FirebaseFirestore.getInstance().collection("rooms").document(roomID).collection("messages").whereEqualTo("msgtype", "1")
					.get()
					.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
						@Override
						public void onComplete(@NonNull Task<QuerySnapshot> task) {
							if (!task.isSuccessful()) { return;}

							for (QueryDocumentSnapshot document : task.getResult()) {
								Message message = document.toObject(Message.class);
								imgList.add(message);
								if (realname.equals(message.getMsg())) {inx = imgList.size()-1; }
							}
							notifyDataSetChanged();
							if (inx>-1) {
								viewPager.setCurrentItem(inx);
							}
						}
					});
		}

		@Override
		public int getCount() {
			return imgList.size();
		}

		@Override
		public View instantiateItem(final ViewGroup container, final int position) {
			final PhotoView photoView = new PhotoView(container.getContext());
            photoView.setId(R.id.photoView);

			Glide.with(container.getContext())
					.load(storageReference.child("filesmall/"+imgList.get(position).getMsg()))
					.into(photoView);

			container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

			return photoView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

	}
}
