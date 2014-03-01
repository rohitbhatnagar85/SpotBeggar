package in.codeniti;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by chaitanyareddy on 3/1/14.
 */
public class DataStore {
    public interface DataBack {
        void queryResult(List<HashMap<String, Object>> res);
    }


    private ParseObject mData;
    public DataStore() {
        this.mData = new ParseObject("beggars");
    }

    public void put(String key, String val) {
        this.mData.put(key, val);
    }

    public void genuine(boolean genuine) {
        if(genuine){
            this.mData.put("genuine", 1);
            this.mData.put("nonSense", 0);
        } else {
            this.mData.put("genuine", 0);
            this.mData.put("nonSense", 1);
        }
    }

    public void putLocation(double lat, double lang) {
        ParseGeoPoint loc = new ParseGeoPoint(lat, lang);
        this.mData.put("location", loc);
    }

    public void put(String key, byte[] image, String name) {
        ParseFile pFile = new ParseFile(name, image);
        this.mData.put(key, pFile);
    }

    public void save() {
        this.mData.saveInBackground();
    }

    public void updateTag(String id, final boolean genuine) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("beggars");
        // Retrieve the object by id
        query.getInBackground(id, new GetCallback<ParseObject>() {
            public void done(ParseObject pObj, ParseException e) {
                if (e == null) {
                    if(genuine){
                        pObj.increment("genuine");
                    }
                    else {
                        pObj.increment("nonSense");
                    }

                    pObj.saveInBackground();
                }
            }
        });
    }


    public void findNearBy(double lat, double lang, final DataBack callback) {
        ParseGeoPoint uLoc = new ParseGeoPoint(lat, lang);
        ParseQuery<ParseObject> q = ParseQuery.getQuery("beggars");
        q.whereNear("location", uLoc);
        q.setLimit(9);
        q.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                Log.e("Killer", "Data" + parseObjects);
                List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
                for(ParseObject pObj : parseObjects) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    ParseFile file = pObj.getParseFile("image");
                    try {
                        map.put("image", file.getData());
                    } catch (ParseException fileE) {
                        fileE.printStackTrace();
                    }
                    ParseGeoPoint loc = pObj.getParseGeoPoint("location");
                    map.put("lat", loc.getLatitude());
                    map.put("lang", loc.getLongitude());
                    map.put("genuine", pObj.getInt("genuine"));
                    map.put("nonSense", pObj.getInt("nonSense"));
                    map.put("id", pObj.getObjectId());
                    list.add(map);
                }
                callback.queryResult(list);
            }
        });
    }
}
