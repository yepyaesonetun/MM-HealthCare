package com.yammobots.mm_healthcare.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.customtabs.CustomTabsIntent
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.yammobots.mm_healthcare.R
import com.yammobots.mm_healthcare.adapters.HCInfoRVAdapter
import com.yammobots.mm_healthcare.adapters.ImagePagerAdapter
import com.yammobots.mm_healthcare.components.SimpleDividerItemDecoration
import com.yammobots.mm_healthcare.data.vo.HealthCareInfoVO
import com.yammobots.mm_healthcare.mvp.presenters.MainPresenter
import com.yammobots.mm_healthcare.mvp.views.MainView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.empty_view_pod.view.*
import kotlinx.android.synthetic.main.layout_item_pager.*
import saschpe.android.customtabs.CustomTabsHelper
import saschpe.android.customtabs.WebViewFallback
import java.util.*

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var mPresenter: MainPresenter
    private lateinit var imagePagerAdapter: ImagePagerAdapter
    private lateinit var infoAdapter: HCInfoRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter = ViewModelProviders.of(this).get(MainPresenter::class.java)
        mPresenter.initPresenter(this)

        infoAdapter = HCInfoRVAdapter(this, mPresenter)

        var dividerItemDecoration = SimpleDividerItemDecoration(rvHCInfo.context)

        rvHCInfo.setEmptyView(vpEmptyNews)
        rvHCInfo.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvHCInfo.hasFixedSize()
        rvHCInfo.addItemDecoration(dividerItemDecoration)
        rvHCInfo.isNestedScrollingEnabled = false
        rvHCInfo.adapter = infoAdapter

        mPresenter.healthCareInfoListLD.observe(this,
                Observer<List<HealthCareInfoVO>> { healthCareInfoVOS: List<HealthCareInfoVO>? ->
                    displayHCInfoData(healthCareInfoVOS as MutableList<HealthCareInfoVO>)
                })
        mPresenter.errorLD.observe(this, Observer<String> { errorMsg ->
            Log.d("ERROR:: ", errorMsg)
        })


        vpEmptyNews.btn_refresh_empty.setOnClickListener({
            mPresenter.forceReLoad()
        })
    }

    override fun onStart() {
        super.onStart()
        val dummyImgList = ArrayList<String>()
        dummyImgList.add("https://i01.appmifile.com/webfile/globalimg/Iris/redmis2banner.jpg")
        dummyImgList.add("https://i01.appmifile.com/webfile/globalimg/Iris/redminote5banner.jpg")
        dummyImgList.add("https://i01.appmifile.com/webfile/globalimg/2018/02141/overall-self-img.png")
        dummyImgList.add("https://i01.appmifile.com/webfile/globalimg/2018/02141/overall-cpu-bg.png")

        imagePagerAdapter = ImagePagerAdapter(dummyImgList, this)
        pager.adapter = imagePagerAdapter
        indicator.setViewPager(pager)
    }

    /**
     * Navigate webUrl to Chrome Custom Tab
     */
    override fun launchChromeCustomTabView(webUrl: String) {
        startLoadWebsiteWithCustomTab(webUrl)
    }

    /**
     * Display HealthCareInfo Data
     */
    override fun displayHCInfoData(healthCareInfoVOS: List<HealthCareInfoVO>) {
        infoAdapter.setNewData(healthCareInfoVOS as MutableList<HealthCareInfoVO>)
    }


    /**
     * @param webUrl : parse down website url to load with chrome custom tab
     */
    private fun startLoadWebsiteWithCustomTab(webUrl: String) {
        // Apply some fancy animation to show off
        val customTabsIntent = getDefaultCustomTabsIntentBuilder()
                .setExitAnimations(this, R.anim.slide_in_left, R.anim.slide_out_right)
                .build()

        CustomTabsHelper.addKeepAliveExtra(this, customTabsIntent.intent)

        CustomTabsHelper.openCustomTab(
                this, customTabsIntent,
                Uri.parse(webUrl),
                WebViewFallback())
    }

    /**
     * Apply some sane defaults across a single app.
     *
     * Not strictly necessary but simplifies code when having many different
     * custom tab intents in one app.
     *
     * @return [CustomTabsIntent.Builder] with defaults already applied
     */
    private fun getDefaultCustomTabsIntentBuilder(): CustomTabsIntent.Builder {
        val backArrow = getBitmapFromVectorDrawable(R.drawable.ic_arrow_back)
        return CustomTabsIntent.Builder()
                .addDefaultShareMenuItem()
                .setToolbarColor(this.resources.getColor(R.color.colorPrimary))
                .setShowTitle(true)
                .setCloseButtonIcon(backArrow!!)
    }

    /**
     * Converts a vector asset to a bitmap as required by [CustomTabsIntent.Builder.setCloseButtonIcon]
     *
     * @param drawableId The drawable ID
     * @return Bitmap equivalent
     */
    private fun getBitmapFromVectorDrawable(@DrawableRes drawableId: Int): Bitmap? {
        var drawable: Drawable? = AppCompatResources.getDrawable(this, drawableId) ?: return null
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = DrawableCompat.wrap(drawable!!).mutate()
        }

        val bitmap = Bitmap.createBitmap(drawable!!.intrinsicWidth,
                drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }
}
