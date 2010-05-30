/***
	Copyright (c) 2008-2010 CommonsWare, LLC
	
	Licensed under the Apache License, Version 2.0 (the "License"); you may
	not use this file except in compliance with the License. You may obtain
	a copy of the License at
		http://www.apache.org/licenses/LICENSE-2.0
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/

package com.commonsware.android.async;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.ArrayList;

public class AsyncDemo extends ListActivity {
	private static String[] items={"lorem", "ipsum", "dolor",
																	"sit", "amet", "consectetuer",
																	"adipiscing", "elit", "morbi",
																	"vel", "ligula", "vitae",
																	"arcu", "aliquet", "mollis",
																	"etiam", "vel", "erat",
																	"placerat", "ante",
																	"porttitor", "sodales",
																	"pellentesque", "augue",
																	"purus"};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		setListAdapter(new ArrayAdapter<String>(this,
												android.R.layout.simple_list_item_1,
												new ArrayList()));
		
		new AddStringTask().execute();
	}
	
	class AddStringTask extends AsyncTask<Void, String, Void> {
		@Override
		protected Void doInBackground(Void... unused) {
			for (String item : items) {
				publishProgress(item);
				SystemClock.sleep(200);
			}
			
			return(null);
		}
		
		@Override
		protected void onProgressUpdate(String... item) {
			((ArrayAdapter)getListAdapter()).add(item[0]);
		}
		
		@Override
		protected void onPostExecute(Void unused) {
			Toast
				.makeText(AsyncDemo.this, "Done!", Toast.LENGTH_SHORT)
				.show();
		}
	}
}
