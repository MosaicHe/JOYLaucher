package laucher.joy.com.JOYLauncher.adpter;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AllPagesAdapter
  extends PagerAdapter
{
  private List<View> views;
  
  public AllPagesAdapter(List<View> paramList)
  {
    this.views = paramList;
  }
  
  public void dataChanged(List<View> paramList)
  {
    this.views = paramList;
    notifyDataSetChanged();
  }
  
  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
  {
    paramViewGroup.removeView((View)this.views.get(paramInt));
  }
  
  public int getCount()
  {
    return this.views.size();
  }
  
  public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
  {
    View localView = (View)this.views.get(paramInt);
    paramViewGroup.addView(localView);
    return localView;
  }
  
  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return paramView == paramObject;
  }
  
  public Parcelable saveState()
  {
    return null;
  }
  
  public void setPrimaryItem(ViewGroup paramViewGroup, int paramInt, Object paramObject) {}
}
