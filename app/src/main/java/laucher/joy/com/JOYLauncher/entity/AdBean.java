package laucher.joy.com.JOYLauncher.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class AdBean
  implements Parcelable
{
  public static final Parcelable.Creator<AdBean> CREATOR = new Parcelable.Creator()
  {
    public AdBean createFromParcel(Parcel paramAnonymousParcel)
    {
      AdBean localAdBean = new AdBean();
      localAdBean.id = paramAnonymousParcel.readInt();
      localAdBean.picUrl = paramAnonymousParcel.readString();
      localAdBean.webUrl = paramAnonymousParcel.readString();
      localAdBean.type = paramAnonymousParcel.readInt();
      return localAdBean;
    }
    
    public AdBean[] newArray(int paramAnonymousInt)
    {
      return new AdBean[paramAnonymousInt];
    }
  };
  private int id;
  private String picUrl;
  private int type;
  private String webUrl;
  
  public AdBean() {}
  
  public AdBean(int paramInt1, String paramString1, String paramString2, int paramInt2)
  {
    this.id = paramInt1;
    this.picUrl = paramString1;
    this.webUrl = paramString2;
    this.type = paramInt2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getPicUrl()
  {
    return this.picUrl;
  }
  
  public int getType()
  {
    return this.type;
  }
  
  public String getWebUrl()
  {
    return this.webUrl;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setPicUrl(String paramString)
  {
    this.picUrl = paramString;
  }
  
  public void setType(int paramInt)
  {
    this.type = paramInt;
  }
  
  public void setWebUrl(String paramString)
  {
    this.webUrl = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.id);
    paramParcel.writeString(this.picUrl);
    paramParcel.writeString(this.webUrl);
    paramParcel.writeInt(this.type);
  }
}
