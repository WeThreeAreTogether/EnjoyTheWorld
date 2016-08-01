package uri;

/**
 * Created by admin on 2016/7/26.
 */
public class FindUri {
    //主题页面:

  public static String FindMain="http://baobab.wandoujia.com/api/v3/discovery?udid=ac15f72cf3cc413d955d873cf26c13db27abd380&vc=121&vn=2.3.5&deviceModel=VPhone&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=19";
//  其中ViewPager的第一个页卡的点击的事件是:
//  http://baobab.wandoujia.com/api/v1/video/7910   7910 7912 7914 7916 7920 7922
  public static String ViewPager_One="http://baobab.wandoujia.com/api/v1/video/";

//  ViewPager的第二个页卡的点击事件是:
//
//  http://baobab.wandoujia.com/api/v3/recommend?udid=ac15f72cf3cc413d955d873cf26c13db27abd380&vc=121&vn=2.3.5&deviceModel=VPhone&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=19

  public static String ViewPager_Two="http://baobab.wandoujia.com/api/v3/recommend?udid=ac15f72cf3cc413d955d873cf26c13db27abd380&vc=121&vn=2.3.5&deviceModel=VPhone&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=19";
//  ViewPager下第一个item的点击事件:  其中需要替换的字段是:strategy=weekly或是monthly或是historical
//
//  周排行: http://baobab.wandoujia.com/api/v3/ranklist?num=10&strategy=weekly&udid=ac15f72cf3cc413d955d873cf26c13db27abd380&vc=121&vn=2.3.5&deviceModel=VPhone&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=19
//
//  月排行:http://baobab.wandoujia.com/api/v3/ranklist?num=10&strategy=monthly&udid=ac15f72cf3cc413d955d873cf26c13db27abd380&vc=121&vn=2.3.5&deviceModel=VPhone&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=19
//
//  总排行:http://baobab.wandoujia.com/api/v3/ranklist?num=10&strategy=historical&udid=ac15f72cf3cc413d955d873cf26c13db27abd380&vc=121&vn=2.3.5&deviceModel=VPhone&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=19

  public static String PopularBase="http://baobab.wandoujia.com/api/v3/ranklist?num=10&strategy=%s&udid=ac15f72cf3cc413d955d873cf26c13db27abd380&vc=121&vn=2.3.5&deviceModel=VPhone&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=19";

//  viewPager下第二个item的点击事件:专题页面
//
//  http://baobab.wandoujia.com/api/v3/specialTopics?udid=ac15f72cf3cc413d955d873cf26c13db27abd380&vc=121&vn=2.3.5&deviceModel=VPhone&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=19

  public static String ZhuanTi="http://baobab.wandoujia.com/api/v3/specialTopics?udid=ac15f72cf3cc413d955d873cf26c13db27abd380&vc=121&vn=2.3.5&deviceModel=VPhone&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=19";

//  360度全景:需要替换的字段是:strategy=date或是strategy=shareCount
//
//  按时间排序:http://baobab.wandoujia.com/api/v3/tag/videos?tagId=658&strategy=date&udid=ac15f72cf3cc413d955d873cf26c13db27abd380&vc=121&vn=2.3.5&deviceModel=VPhone&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=19
//
//  按分享排序:http://baobab.wandoujia.com/api/v3/tag/videos?tagId=658&strategy=shareCount&udid=ac15f72cf3cc413d955d873cf26c13db27abd380&vc=121&vn=2.3.5&deviceModel=VPhone&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=19
  public static String QuanJing="http://baobab.wandoujia.com/api/v3/tag/videos?tagId=658&strategy=%s&udid=ac15f72cf3cc413d955d873cf26c13db27abd380&vc=121&vn=2.3.5&deviceModel=VPhone&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=19";

//  网格的点击事件:
//
//  其中需要替换的字段是:
//
//  categoryId=14 是被点击的item的id号
//
//          strategy=date或是shareCount
//
//  http://baobab.wandoujia.com/api/v3/videos?categoryId=14&strategy=date&udid=ac15f72cf3cc413d955d873cf26c13db27abd380&vc=121&vn=2.3.5&deviceModel=VPhone&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=19
//
//  http://baobab.wandoujia.com/api/v3/videos?categoryId=14&strategy=shareCount&udid=ac15f72cf3cc413d955d873cf26c13db27abd380&vc=121&vn=2.3.5&deviceModel=VPhone&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=19

  public static String WangGe="http://baobab.wandoujia.com/api/v3/videos?categoryId=%s&strategy=%s&udid=ac15f72cf3cc413d955d873cf26c13db27abd380&vc=121&vn=2.3.5&deviceModel=VPhone&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=19";

//  item的点击事件: 将8196替换为所点击条目的id  播放的详情页
//
//  http://baobab.wandoujia.com/api/v2/video/8196?udid=d5c5ee1d70a64c7093dd616bda3a557ab483f172&vc=121&vn=2.3.5&deviceModel=Custom%20Phone%20-%205.0.0%20-%20API%2021%20-%20768x1280&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=21
 public static String item_click_base="http://baobab.wandoujia.com/api/v2/video/";
  public static String item_click_end="?udid=d5c5ee1d70a64c7093dd616bda3a557ab483f172&vc=121&vn=2.3.5&deviceModel=Custom%20Phone%20-%205.0.0%20-%20API%2021%20-%20768x1280&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=21";

}
