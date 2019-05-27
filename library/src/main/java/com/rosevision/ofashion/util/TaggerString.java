package com.rosevision.ofashion.util;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;

import androidx.annotation.StringRes;


public class TaggerString {

    private static final String PLACEHOLDER_BRACKET_OPEN = "\\{";
    private static final String PLACEHOLDER_BRACKET_CLOSE = "\\}";

    private final String format;
    private final Map<String, Object> tags;

    private TaggerString(String format) {
        this.format = format;
        this.tags = new LinkedHashMap<>();
    }

    public static TaggerString from(String format) {
        return new TaggerString(format);
    }


    public static TaggerString from(@StringRes int strResId, Context context) {
        return from(context.getString(strResId));
    }

    public TaggerString with(Map<String, Object> tags) {
        for (Map.Entry<String, Object> tag : tags.entrySet()) {
            with(tag.getKey(), tag.getValue());
        }
        return this;
    }

    public TaggerString with(String key, Object value) {
        StringBuilder builder = new StringBuilder(PLACEHOLDER_BRACKET_OPEN);
        builder.append(key);
        builder.append(PLACEHOLDER_BRACKET_CLOSE);
        tags.put(builder.toString(), value);
        return this;
    }

    public TaggerString with(String key, Object value, TaggerStyleType taggerStyleType) {
        StringBuilder builder = new StringBuilder(PLACEHOLDER_BRACKET_OPEN);
        builder.append(key);
        builder.append(PLACEHOLDER_BRACKET_CLOSE);
        tags.put(builder.toString(), taggerStyleType.getTagStart() + value + taggerStyleType.getTagEnd());
        return this;
    }

    public String format() {
        String result = format;
        try {
            for (Map.Entry<String, Object> tag : tags.entrySet()) {
                result = doReplace(result, tag.getKey(), tag.getValue().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String doReplace(String result, String key, String value) {
        return result.replaceAll(key, Matcher.quoteReplacement(value));
    }

    public Spanned formatCustom() {
        String result = format;

        try {
            for (Map.Entry<String, Object> tag : tags.entrySet()) {
                result = doReplace(result, tag.getKey(), tag.getValue().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Html.fromHtml(result);
    }

    public enum TaggerStyleType {

        BOLD("<b>", "</b>"),
        UNDERLINE("<u>", "</u>"),
        ITALIC("<i>", "</i>");

        private String tagStart;
        private String tagEnd;

        TaggerStyleType(String tagStart, String tagEnd) {
            this.tagStart = tagStart;
            this.tagEnd = tagEnd;
        }

        public String getTagStart() {
            return tagStart;
        }

        public String getTagEnd() {
            return tagEnd;
        }
    }
}
