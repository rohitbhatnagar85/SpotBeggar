package in.codeniti;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.*;
import android.widget.*;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private boolean isfetched = false;
    private LayoutInflater inflater;
    public ImageAdapter(Context c) {
        mContext = c;
        inflater = LayoutInflater.from(c);
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
//    public View getView(int position, View convertView, ViewGroup parent) {
//    	View v = convertView;
//        ImageView picture;
//        TextView name;
//
//       
//        if (convertView == null) {  // if it's not recycled, initialize some attributes
//        	picture = new ImageView(mContext);
//            name = new TextView(mContext);
//          
//        } else {
//        	picture = (ImageView) convertView.findViewById(R.id.picture);
//        	name = (TextView) convertView.findViewById(R.id.text);
//        }
//        
//        if(position < DisplayActivity.beggars.size()){
//          byte[] imgbitmap = (byte[]) DisplayActivity.beggars.get(position).get("image");
//            //imageView.setImageResource(mThumbIds[position]);
//          picture.setImageBitmap(BitmapFactory.decodeByteArray(imgbitmap, 0, imgbitmap.length));
//          name.setText("Hello darling");
//        }
//        
//        if(v == null) {
//            v = LayoutInflater.from(mContext).inflate(R.layout.activity_disp, parent, false);
//            v.setTag(R.id.picture, v.findViewById(R.id.picture));
//            v.setTag(R.id.text, v.findViewById(R.id.text));
//        }
//
//       
//        //name = (TextView)v.getTag(R.id.text);
//
//        //Item item = (Item)getItem(i);
//
//       // picture.setImageResource(item.drawableId);
//      //  name.setText("Hello darling");
//
//    	
//       
//        return v;
//    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if(v == null) {
            v = inflater.inflate(R.layout.img, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }else{
        	//return inflater.inflate(R.layout.img, viewGroup, false);
        }

        picture = (ImageView)v.getTag(R.id.picture);
        name = (TextView)v.getTag(R.id.text);
       
        //Item item = (Item)getItem(i);

        
        if(i < DisplayActivity.beggars.size()){
          final byte[] imgbitmap = (byte[]) DisplayActivity.beggars.get(i).get("image");
            //imageView.setImageResource(mThumbIds[position]);
          picture.setImageBitmap(BitmapFactory.decodeByteArray(imgbitmap, 0, imgbitmap.length));
          Integer gencnt = (Integer)DisplayActivity.beggars.get(i).get("genuine");
          Integer nonsense = (Integer)DisplayActivity.beggars.get(i).get("nonSense");
          name.setText("Yes:"+gencnt+" No:"+nonsense);
          picture.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(mContext);
				dialog.setContentView(R.layout.customdiag);
				dialog.setTitle("Record your opinion...");
	 
				// set the custom dialog components - text, image and button
				
				ImageView image = (ImageView) dialog.findViewById(R.id.image);
				image.setImageBitmap(BitmapFactory.decodeByteArray(imgbitmap, 0, imgbitmap.length));
	 
				Button dialogButton = (Button) dialog.findViewById(R.id.but_save);
				// if button is clicked, close the custom dialog
				dialogButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						DataStore ds= new DataStore();
						CheckBox cb = (CheckBox)dialog.findViewById(R.id.cb_isgen);
						ds.updateTag((String)DisplayActivity.beggars.get(i).get("id"),cb.isChecked() );
						//ds.save();
						dialog.dismiss();
						
					}
				});
	 
				dialog.show();
				
			}
		});
        }
        	
        	
//        picture.setImageResource(item.drawableId);
//        name.setText(item.name);

        return v;
    }
    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.ic_launcher, R.drawable.fb,
            R.drawable.ic_launcher, R.drawable.fb,
            R.drawable.ic_launcher, R.drawable.fb,
            R.drawable.ic_launcher, R.drawable.fb,
            R.drawable.ic_launcher, R.drawable.fb,
            R.drawable.ic_launcher, R.drawable.fb,
            R.drawable.ic_launcher, R.drawable.fb,
            R.drawable.ic_launcher, R.drawable.fb,
            R.drawable.ic_launcher, R.drawable.fb,
            R.drawable.ic_launcher, R.drawable.fb,
          
    };
}
