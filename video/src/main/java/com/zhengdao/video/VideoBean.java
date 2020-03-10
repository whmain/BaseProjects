package com.zhengdao.video;

/**
 * @author WangHao on 2020/3/10.
 * E-Mail: wh_main@163.com
 * Description:
 */
public class VideoBean {
    private String name;
    private long size;
    private long duration;  //毫秒
    private String url;   //文件路径
    private String album; //缩略图

    public VideoBean(String name, long size, long duration, String url, String album) {
        this.name = name;
        this.size = size;
        this.duration = duration;
        this.url = url;
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "VideoBean{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", duration=" + duration +
                ", url='" + url + '\'' +
                ", album='" + album + '\'' +
                '}';
    }
}
