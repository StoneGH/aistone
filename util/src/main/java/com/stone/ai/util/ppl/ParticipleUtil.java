package com.stone.ai.util.ppl;

import com.stone.ai.util.str.StringUtils;

import java.util.*;

/**
 * 字符串分词工具
 * Created by shitao on 2018/7/10.
 */
public class ParticipleUtil {
    private static Set<String> seg_dict;


    /*****************************************双向对打匹配算法 start*****************************************/

    /**
     * 加载初始化词典
     *
     * @param words
     */
    public static void Init(Set<String> words) {
        seg_dict = words;
    }

    /**
     * 前向算法分词
     *
     * @param seg_dict 分词词典
     * @param phrase   待分词句子
     * @return 前向分词结果
     */
    private static Vector<String> FMM2(String phrase) {
        int maxlen = 16;
        Vector<String> fmm_list = new Vector<String>();
        int len_phrase = phrase.length();
        int i = 0, j = 0;

        while (i < len_phrase) {
            int end = i + maxlen;
            if (end >= len_phrase)
                end = len_phrase;
            String phrase_sub = phrase.substring(i, end);
            for (j = phrase_sub.length(); j >= 0; j--) {
                if (j == 1)
                    break;
                String key = phrase_sub.substring(0, j);
                if (seg_dict.contains(key)) {
                    fmm_list.add(key);
                    i += key.length() - 1;
                    break;
                }
            }
            if (j == 1)
                fmm_list.add("" + phrase_sub.charAt(0));
            i += 1;
        }
        return fmm_list;
    }

    /**
     * 后向算法分词
     *
     * @param seg_dict 分词词典
     * @param phrase   待分词句子
     * @return 后向分词结果
     */
    private static Vector<String> BMM2(String phrase) {
        int maxlen = 16;
        Vector<String> bmm_list = new Vector<String>();
        int len_phrase = phrase.length();
        int i = len_phrase, j = 0;

        while (i > 0) {
            int start = i - maxlen;
            if (start < 0)
                start = 0;
            String phrase_sub = phrase.substring(start, i);
            for (j = 0; j < phrase_sub.length(); j++) {
                if (j == phrase_sub.length() - 1)
                    break;
                String key = phrase_sub.substring(j);
                if (seg_dict.contains(key)) {
                    bmm_list.insertElementAt(key, 0);
                    i -= key.length() - 1;
                    break;
                }
            }
            if (j == phrase_sub.length() - 1)
                bmm_list.insertElementAt("" + phrase_sub.charAt(j), 0);
            i -= 1;
        }
        return bmm_list;
    }

    /**
     * 该方法结合正向匹配和逆向匹配的结果，得到分词的最终结果
     *
     * @param FMM2   正向匹配的分词结果
     * @param BMM2   逆向匹配的分词结果
     * @param return 分词的最终结果
     */
    public static Vector<String> segment(String phrase, Set<String> words) {
        Vector<String> fmm_list = FMM2(phrase);
        Vector<String> bmm_list = BMM2(phrase);
        //如果正反向分词结果词数不同，则取分词数量较少的那个
        if (fmm_list.size() != bmm_list.size()) {
            if (fmm_list.size() > bmm_list.size())
                return bmm_list;
            else return fmm_list;
        }
        //如果分词结果词数相同
        else {
            //如果正反向的分词结果相同，就说明没有歧义，可返回任意一个
            int i, FSingle = 0, BSingle = 0;
            boolean isSame = true;
            for (i = 0; i < fmm_list.size(); i++) {
                if (!fmm_list.get(i).equals(bmm_list.get(i)))
                    isSame = false;
                if (fmm_list.get(i).length() == 1)
                    FSingle += 1;
                if (bmm_list.get(i).length() == 1)
                    BSingle += 1;
            }
            if (isSame)
                return fmm_list;
            else {
                //分词结果不同，返回其中单字较少的那个
                if (BSingle > FSingle)
                    return fmm_list;
                else return bmm_list;
            }
        }
    }


    public static List<String> participle(String phrase, Set<String> words) {
        if (StringUtils.isNullOrEmpty(phrase) || null == words || words.isEmpty()) {
            return null;
        }

        Init(words);

        Vector<String> vector = segment(phrase, words);

        Collections.sort(vector, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() > o2.length() ? 1 : -1;
            }
        });
        return vector;
    }

    /*****************************************双向对打匹配算法 end *****************************************/


    public static void main(String[] args) {
        String test = "我是一个学生";
        Set<String> words = new HashSet<>();
        words.add("个学生");
        words.add("我是");
        List<String> vector = ParticipleUtil.participle(test, words);
        System.out.println(vector);
    }
}
