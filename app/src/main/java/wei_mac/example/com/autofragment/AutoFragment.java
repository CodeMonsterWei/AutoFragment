package wei_mac.example.com.autofragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wei-mac on 2017/8/11.
 */

public class AutoFragment {

    private FragmentActivity activity;
    private Fragment[] fragmentArr;
    private LinearLayout bottomView, topBarView, topBarTagView;
    private HorizontalScrollView topBarTagScrollView;
    private TextView[] tagTexts;
    private ImageView sliderImageView;
    private View partitionView;
    private ViewPager contentView;

    private int count;
    private int screenWidth;
    private int tagWidth;
    private int selectTextColor = Color.BLUE, notSelectTextColor = Color.BLACK;
    private int selectBackgroundColor = 0, notSelectBackgroundColor = 0;
    private int sliderHeight = 10;
    private int sliderColorColor = Color.BLUE;
    private int currentIndex = 0;
    private String[] tagNameArr;
    private int[] iconIds = new int[]{0};

    public AutoFragment(FragmentActivity activity){
        this.activity = activity;
        initObject();
    }

    public AutoFragment(FragmentActivity activity, LinearLayout relyLayout, String[] textArray, Fragment[] v4AppFragment){
        this.activity = activity;
        bottomView = relyLayout;
        tagNameArr = textArray;
        fragmentArr = v4AppFragment;
        initObject();
        startUp();
    }

    private void initObject() {
        topBarView = new LinearLayout(activity);
        topBarTagView = new LinearLayout(activity);
        topBarTagScrollView = new HorizontalScrollView(activity);
        sliderImageView = new ImageView(activity);
        partitionView = new View(activity);
        contentView = new ViewPager(activity);
    }

    private void initDefault() {
        initCount();
        initWidth();
    }

    private void initCount() {
        if (tagNameArr != null){
            count = tagNameArr.length;
            tagTexts = new TextView[tagNameArr.length];
        }else {
            count = iconIds.length;
            tagTexts = new TextView[iconIds.length];
        }
    }

    private void initWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        activity.getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        tagWidth = (count < 3)? count : 3;
    }

    private void initBottomView() {
        bottomView.setOrientation(LinearLayout.VERTICAL);
        initTopBar();
    }

    private void initTopBar() {
        topBarView.setOrientation(LinearLayout.VERTICAL);

        initTopBarTagScroll();

        initSliderImage();
        initPartition();
        initContent();

        bottomView.addView(topBarView,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void initTopBarTagScroll() {
        topBarTagScrollView.setHorizontalScrollBarEnabled(false);
        topBarView.addView(topBarTagScrollView,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        initTopBarTag();
    }

    private void initTopBarTag() {
        topBarTagView.setOrientation(LinearLayout.HORIZONTAL);
        topBarTagView.setGravity(Gravity.CENTER);

        for (int i = 0; i < count ; i++){
            initTopBarTag(i);
        }

        topBarTagScrollView.addView(topBarTagView,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
    }

    private void initTopBarTag(int position) {
        LinearLayout tagView = new LinearLayout(activity);
        tagView.setGravity(Gravity.CENTER);
        topBarTagView.addView(tagView,screenWidth / tagWidth,LinearLayout.LayoutParams.MATCH_PARENT);
        tagTexts[position] = new TextView(activity);
        if(position == 0){
            if(selectBackgroundColor != 0){
                tagTexts[position].setBackgroundColor(selectBackgroundColor);
            }
            tagTexts[position].setTextColor(selectTextColor);
        }else {
            if(selectBackgroundColor != 0){
                tagTexts[position].setBackgroundColor(notSelectBackgroundColor);
            }
            tagTexts[position].setTextColor(notSelectTextColor);
        }
        if (iconIds[0] != 0){
            Drawable drawable;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                drawable = activity.getResources().getDrawable(iconIds[position],null);
            }else {
                drawable = activity.getResources().getDrawable(iconIds[position]);
            }
            drawable.setBounds(0,0,100,100);
            tagTexts[position].setCompoundDrawables(null, drawable, null, null);
        }
        if (tagNameArr == null) tagTexts[position].setTextSize(0);
        else tagTexts[position].setText(tagNameArr[position]);
        tagTexts[position].setPadding(0,32,0,32);
        tagTexts[position].setTag(2468100 + position);
        tagTexts[position].setGravity(Gravity.CENTER);
        tagTexts[position].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = ((Integer)v.getTag()) - 2468100;
                goView(position);
            }
        });
        tagView.addView(tagTexts[position], LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    private void initSliderImage() {
        sliderImageView.setBackgroundColor(sliderColorColor);
        sliderImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        topBarView.addView(sliderImageView,screenWidth / count,sliderHeight);
    }

    private void initPartition() {
        partitionView.setBackgroundColor(Color.argb(255,0,0,0));
        topBarView.addView(partitionView,LinearLayout.LayoutParams.MATCH_PARENT,1);
    }

    private void initContent(){
        contentView.setId(R.id.viewPager);
        contentView.setAdapter(new FragmentPagerAdapter(activity.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentArr[position];
            }

            @Override
            public int getCount() {
                return count;
            }
        });
        contentView.setCurrentItem(0);

        contentView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {
                // 1：正在滑動 2：滑完了 0：沒做事時。
            }

            @Override
            public void onPageScrolled(int position, float offset, int offsetPixels) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) sliderImageView.getLayoutParams();
                layoutParams.leftMargin = (int) ((screenWidth / count) * (position + offset));
                sliderImageView.setLayoutParams(layoutParams);

                if (position != 0 && position != count - 1 && offsetPixels != 0){
                    topBarTagScrollView.smoothScrollTo((int) ((screenWidth / tagWidth) * ((position - 1) + offset)),0);
                }else if (position == 0) {
                    topBarTagScrollView.smoothScrollTo(0,0);
                }
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0 ; i < tagTexts.length ; i++){
                    if (i == position) {
                        if(selectBackgroundColor != 0){
                            tagTexts[i].setBackgroundColor(selectBackgroundColor);
                        }
                        tagTexts[i].setTextColor(selectTextColor);
                    }else {
                        if(selectBackgroundColor != 0){
                            tagTexts[i].setBackgroundColor(notSelectBackgroundColor);
                        }
                        tagTexts[i].setTextColor(notSelectTextColor);
                    }
                }
            }
        });
        topBarView.addView(contentView,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
    }


    private void goView(int position) {
        if (position < 0 || position >= count) {
            return;
        }
        contentView.setCurrentItem(position);
        if (position < 0 || position > count - 1 || currentIndex == position) {
            return;
        }
        currentIndex = position;
    }

    private String reError(String erroe){
        String str = "-------------------------------------------------------------------------------------------------------------------";
        return "\n" + str + "\n" + erroe + "\n" + str ;
    }

    public AutoFragment setFocusBackgroundColor(int colorId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            selectBackgroundColor = activity.getResources().getColor(colorId,null);
        }else {
            selectBackgroundColor = activity.getResources().getColor(colorId);
        }
        return this;
    }

    public AutoFragment setFocusTextColor(int colorId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            selectTextColor = activity.getResources().getColor(colorId,null);
        }else {
            selectTextColor = activity.getResources().getColor(colorId);
        }
        return this;
    }

    public AutoFragment setNotFocusBackgroundColor(int colorId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notSelectBackgroundColor = activity.getResources().getColor(colorId,null);
        }else {
            notSelectBackgroundColor = activity.getResources().getColor(colorId);
        }
        return this;
    }

    public AutoFragment setNotFocusTextColor(int colorId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notSelectTextColor = activity.getResources().getColor(colorId,null);
        }else {
            notSelectTextColor = activity.getResources().getColor(colorId);
        }
        return this;
    }

    public AutoFragment setSliderHeight(int default10){
        sliderHeight = default10;
        return this;
    }

    public AutoFragment setSliderColorID(int colorId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sliderColorColor = activity.getResources().getColor(colorId,null);
        }else {
            sliderColorColor = activity.getResources().getColor(colorId);
        }
        return this;
    }

    public AutoFragment setRelyLayout(LinearLayout relyLayout){
        bottomView = relyLayout;
        return this;
    }

    public AutoFragment setTagTextArray(String[] textArray){
        tagNameArr = textArray;
        return this;
    }

    public AutoFragment setTagIconArray(int[] iconIdArray){
        iconIds = iconIdArray;
        return this;
    }

    public AutoFragment setFragmentView(Fragment[] v4AppFragment){
        fragmentArr = v4AppFragment;
        return this;
    }

    public void startUp(){
        if (bottomView == null) throw new NullPointerException(reError("AutoFragment Error : Please use .setRelyLayout(LinearLayout relyLayout) add to rely layout"));
        if (tagNameArr == null && iconIds[0] == 0) throw new NullPointerException(reError("AutoFragment Error : Please use .setTagTextArray(String[] textArray) or .setTagIconArray(int[] iconIdArray) add to tag"));
        if (fragmentArr == null) throw new NullPointerException(reError("AutoFragment Error : Please use .setFragmentView(Fragment[] v4AppFragment) add to fragment"));
        if (tagNameArr != null && tagNameArr.length != fragmentArr.length) throw new IllegalArgumentException(reError("AutoFragment Error : View and Tag the number is not equal ... View.length = " + fragmentArr.length + " / Tag.length = " + tagNameArr.length));
        if (iconIds[0] != 0 && iconIds.length != fragmentArr.length) throw new IllegalArgumentException(reError("AutoFragment Error : Icon and View the number is not equal ... Icon.length = " + iconIds.length + " / View.length = " + fragmentArr.length));

        if (bottomView != null && fragmentArr.length > 0){
            initDefault();
            initBottomView();
        }
    }

}
