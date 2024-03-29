package kr.hs.dion_kotlin

import android.content.Context
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.*

import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kr.hs.dion_kotlin.R

class MapsFragment : Fragment() {

    //lateinit var promotionDetailsActivity : PromotionDetailsActivity

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        //val myLoc = promotionDetailsActivity.getCurrentLoc()
        //googleMap.addMarker(MarkerOptions().position(myLoc).title("기본값"))
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(myLoc))
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLoc, 15.0F))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //promotionDetailsActivity = context as PromotionDetailsActivity
    }

}