package weixi.example.com.myquickindex.bean;

import weixi.example.com.myquickindex.utils.PinyinUtils;

/**
 * 好汉类，拼音通过utils传进来
 * Created by weixi on 2016/7/14.
 */
public class Haohan implements Comparable<Haohan>{
    private String name;
    private String pinyin;

    public Haohan(String name) {
        this.name = name;
        pinyin= PinyinUtils.getPinyin(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public int compareTo(Haohan another) {
        return this.pinyin.compareTo(another.pinyin);
    }
}
