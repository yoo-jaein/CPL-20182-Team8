package com.example.onthejourney.Activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onthejourney.Adapter.PhotographerListViewAdapter;
import com.example.onthejourney.Algorithm.CustomClusterRenderer;
import com.example.onthejourney.Algorithm.NonHierarchicalDistanceBasedAlgorithm;
import com.example.onthejourney.Data.MyItem;
import com.example.onthejourney.ETC.PermissionUtils;
import com.example.onthejourney.Module.RequestHttpURLConnection;
import com.example.onthejourney.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements
        GoogleMap.OnMyLocationButtonClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private Geocoder geocoder;
    private Button button;
    MyItem myItem = null;

    private TextView mung,count,thisArea;

    private ArrayList<String> image_path_list;
    private ClusterManager<MyItem> mClusterManager;
    String str;
    PlaceAutocompleteFragment autocompleteFragment;
    private boolean mPermissionDenied = false;

    ArrayList<MyItem> aryList = new ArrayList<MyItem>();

    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        thisArea = findViewById(R.id.thisArea);
        mung = findViewById(R.id.mung);
        count = findViewById(R.id.count);


        button = (Button) findViewById(R.id.button);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment1);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                System.out.println("Place: " + place.getName());
                str = place.getName().toString();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                System.out.println("An error occurred: " + status);
            }
        });
        mapFragment.getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        LatLng DEFAULT_LOCATION = new LatLng(37.56, 126.97);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 15);
        mMap.moveCamera(cameraUpdate);
        setUpClusterer(googleMap);
        geocoder = new Geocoder(this);

        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();


        //클러스터링 이벤트
        mClusterManager.setOnClusterClickListener(
                new ClusterManager.OnClusterClickListener<MyItem>() {
                    @Override
                    public boolean onClusterClick(Cluster<MyItem> cluster) {
                        final ListView listView = (ListView) findViewById(R.id.listView);

                        final ArrayList<MyItem> arr = new ArrayList<MyItem>();

                        thisArea.setVisibility(TextView.VISIBLE);
                        mung.setVisibility(TextView.VISIBLE);
                        count.setVisibility(TextView.VISIBLE);
                        Object[] ary = cluster.getItems().toArray();

                        for(int i=0;i<ary.length;i++){
                            arr.add((MyItem)ary[i]);
                        }

                        count.setText(String.valueOf(ary.length));

                        PhotographerListViewAdapter adapter = new PhotographerListViewAdapter(arr);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                                System.out.println(position);
                                myItem = arr.get(position);
                                System.out.println(myItem.getTitle());


                                NetworkTask networkTask = new NetworkTask("feed_items","image_path");
                                networkTask.execute();
                            }
                        });

                        Toast.makeText(MapsActivity.this, "Cluster click", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
        );
        mClusterManager.setOnClusterItemClickListener(
                new ClusterManager.OnClusterItemClickListener<MyItem>() {
                    @Override
                    public boolean onClusterItemClick(MyItem MyItem) {
                        final ListView listView = (ListView) findViewById(R.id.listView);

                        final ArrayList<MyItem> arr = new ArrayList<MyItem>();

                        thisArea.setCursorVisible(true);
                        mung.setCursorVisible(true);
                        count.setCursorVisible(true);
                        count.setText("1");

                        arr.add(MyItem);

                        final PhotographerListViewAdapter adapter = new PhotographerListViewAdapter(arr);
                        listView.setAdapter(adapter);


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                                System.out.println(position);
                                myItem = arr.get(position);
                                System.out.println(myItem.getTitle());

                                NetworkTask networkTask = new NetworkTask("feed_items","image_path");
                                networkTask.execute();
                            }
                        });

                        Toast.makeText(MapsActivity.this, "Cluster item click", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
        );

        // 버튼 이벤트
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Address> addressList = null;
                try {
                    // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
                    addressList = geocoder.getFromLocationName(
                            str, // 주소
                            10); // 최대 검색 결과 개수

                } catch (IOException e) {
                    e.printStackTrace();
                }


                System.out.println(addressList.get(0).toString());
                // 콤마를 기준으로 split
                String[] splitStr = addressList.get(0).toString().split(",");
                String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length() - 2); // 주소
                System.out.println(address);

                String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
                String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도
                System.out.println(latitude);
                System.out.println(longitude);

                // 좌표(위도, 경도) 생성
                LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));

                // 해당 좌표로 화면 줌
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
            }
        });

        addItems(aryList);
    }


    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    private void setUpClusterer(final GoogleMap map) {
        // Position the map.
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = new ClusterManager<MyItem>(this, map);

        final CustomClusterRenderer render = new CustomClusterRenderer(this, mMap, mClusterManager);
        mClusterManager.setRenderer(render);
        final NonHierarchicalDistanceBasedAlgorithm nonHierarchicalDistanceBasedAlgorithm = new NonHierarchicalDistanceBasedAlgorithm();
        mClusterManager.setAlgorithm(nonHierarchicalDistanceBasedAlgorithm);
        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        map.setOnCameraIdleListener(mClusterManager);
        map.setOnMarkerClickListener(mClusterManager);
    }



    private void addItems(ArrayList<MyItem> aryList) {

//        // Add ten cluster items in close proximity, for purposes of this example.
//        for (int i = 0; i < 10; i++) {
//            double offset = i / 60d;
//            lat = var.latitude + offset;
//            lng = var.longitude + offset;
//            MyItem offsetItem = new MyItem(lat, lng);
//            mClusterManager.addItem(offsetItem);
//        }
        for (int i = 0; i < 100; i++) {
            double lat = 0;
            double lng = 0;
            Random random = new Random();
            LatLng DEFAULT_LOCATION = new LatLng(37.56, 126.97);
            lat = (double) random.nextInt(300) / 5000.0 + DEFAULT_LOCATION.latitude;
            lng = (double) random.nextInt(300) / 5000.0 + DEFAULT_LOCATION.longitude;
            MyItem offsetItem = new MyItem(lat, lng);
            offsetItem.setmTitle(i + "번째 생성");
            aryList.add(offsetItem);

            mClusterManager.addItem(offsetItem);
        }
    }


    public class NetworkTask extends AsyncTask<Void, Void, ArrayList<String>> {

        private String option1, option2;

        public NetworkTask(String option1, String option2) {
            this.option1 = option1;
            this.option2 = option2;
        }

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            ArrayList<String> result;
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.getJsonText(option1, option2);

            return result;
        }

        protected void onPostExecute(ArrayList<String> s) {
            if(option2.equals("image_path")) {
                Log.d("onPostExecute", s.toString());
                image_path_list = s;

                Intent intent = new Intent(MapsActivity.this, Photographer_info.class);
                intent.putExtra("MyItem", myItem);
                intent.putStringArrayListExtra("List", image_path_list);
                startActivity(intent);
            }
        }
    }

}
