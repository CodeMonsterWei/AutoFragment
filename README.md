
[點擊下載 aar 檔](https://drive.google.com/file/d/0B5zgfkIF-qiIUFBnb2k0dS1haUU/view?usp=sharing)

[ xuite 部落格](http://blog.xuite.net/kalian/code/527847236)

#### 自動生成 Fragment 畫面
#### 使用 android.support.v4.app.Fragment

# 快速導引：

 * [主要功能](#主要功能)
 * [畫面堆疊](#畫面堆疊)
 * [方法介紹](#方法介紹)
> * [顯示類型](#顯示類型)
> * [修改類型](#修改類型)
 * [使用畫面](#使用畫面)
> * [仰賴LinearLayout](#仰賴LinearLayout)
> * [基本使用](#基本使用)
> * [基本使用2](#基本使用2)
> * [快速使用](#快速使用)

# 主要功能：

```
    仰賴於 LinearLayout 上
    丟入 Tap 與 Fragment 陣列 即可在 LinearLayout 自動生成
    Tap 可選用文字或圖片亦可同時使用
    可點擊 Tap 快速跳頁
    Fragment 為 android.support.v4.app.Fragment
    Activity 為 FragmentActivity
```

# 畫面堆疊：

```
    LinearLayout 
        LinearLayout - topBarView
            LinearLayout - topBarTagView
                HorizontalScrollView - topBarTagScrollView
                    TextView - tagTexts
                ImageView - sliderImageView
            View - partitionView
        ViewPager - contentView
```

# 方法介紹

## 顯示類型：

```
    添加依賴的Layout： setRelyLayout(LinearLayout relyLayout)
                      必續添加 否則無畫面
    新增Tap文字： setTagTextArray(String[] textArray)
                 與 setTagIconArray 可擇一或同時使用
    新增Tap圖片： setTagIconArray(int[] iconIdArray)
                 與 setTagTextArray 可擇一或同時使用
    新增Fragment畫面： setFragmentView(Fragment[] v4AppFragment)
                      需與 Tab 或 Icon 數量相同
    建立Fragment畫面： startUp()
                      完成設定後建立 Fragment
```

## 修改類型：

```
    修改滾軸顏色： setSliderColorID(int colorId)
    修改滾軸高度： setSliderHeight(int default10)
                 預設為 10
    修改非選中的Tap顏色： setNotFocusTextColor(int colorId)
                       預設為 黑色
    修改非選中的Tap背景顏色： setNotFocusBackgroundColor(int colorId)
                          預設為 自然色
    修改選中的Tap顏色： setFocusTextColor(int colorId)
                      預設為 藍色
    修改選中的Tap背景顏色： setFocusBackgroundColor(int colorId)
                         預設為 自然色
```

# 使用畫面：

## 仰賴LinearLayout：

![使用圖片](http://a.share.photo.xuite.net/kalian/1a8ed27/20244996/1169421603_x.jpg)

## 基本使用：

![使用圖片](http://a.share.photo.xuite.net/kalian/1a8eded/20244996/1169420265_x.jpg)
![使用圖片](http://a.share.photo.xuite.net/kalian/1a8ed28/20244996/1169421604_x.jpg)

## 基本使用2：

![使用圖片](http://a.share.photo.xuite.net/kalian/1a8ed8b/20244996/1169420167_x.jpg)
![使用圖片](http://a.share.photo.xuite.net/kalian/1a8edc7/20244996/1169421507_x.jpg)

## 快速使用：

![使用圖片](http://a.share.photo.xuite.net/kalian/1a8edc8/20244996/1169421508_x.jpg)
![使用圖片](http://a.share.photo.xuite.net/kalian/1a8ed9c/20244996/1169421208_x.jpg)
