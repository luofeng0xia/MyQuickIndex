package weixi.example.com.myquickindex.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 把中文转化成拼音
 * Created by weixi on 2016/7/14.
 */
public class PinyinUtils {

    public static String getPinyin (String chinese){
          char[] chars=chinese.toCharArray();
          StringBuilder sb=new StringBuilder();
          HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
          format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
          format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
          String s="";
          for (char c:chars) {
              //如果是空格直接跳过
              if (Character.isWhitespace(c)){
                  continue;
              }
              if (c>=-127&&c<128){
                  //肯定不是汉字
                  sb.append(c);
              }else {
                  try {
                      //StringArray 一个字的多个读音 例如 单（dan shan）
                      String[] strings = PinyinHelper.toHanyuPinyinStringArray(c, format);
                      s = strings[0];
                      sb.append(s);
                  } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                      badHanyuPinyinOutputFormatCombination.printStackTrace();
                      sb.append(s);
                  }
              }
          }
          return sb.toString();
      }
}
