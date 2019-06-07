package Lexer;

import Util.LuaStringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lijin on 5/8/19.
 */

public class CodeSeq implements CharSequence{
    private String code;
    public int offset = 0;
    CodeSeq(String code){
        this.code = code;
    }
    public void next(int count){
        offset+=count;
    }
    public void next(){
        offset+=1;
    }
    public boolean startWith(String prefix){
        return code.startsWith(prefix, offset);
    }
    public int indexOf(String str){
        return code.indexOf(str, offset) - offset;
    }
    public String matchStr(Pattern pattern){
        Matcher matcher = pattern.matcher(this);
        return matcher.find() ? matcher.group(0) : null;
    }

    public boolean isWhiteSpace(){char c = this.getChar(0);return LuaStringUtil.isWhiteSpace(c);}
    public boolean isNewLine(){return LuaStringUtil.isNewLine(this.getChar(0));}
    public char getChar(int idx){return code.charAt(idx+offset);}
    public boolean isDigit(int idx){char c = getChar(idx);return LuaStringUtil.isDigit(c);}
    public boolean isLetter(int idx){char c = getChar(idx); return LuaStringUtil.isLetter(c);}
    public String subString(int bidx, int eidx){
        return code.substring(bidx+offset, eidx+offset);
    }

    @Override
    public int length() {
        return this.code.length() - offset;
    }

    @Override
    public char charAt(int index) {
        return this.code.charAt(index + offset);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return this.code.subSequence(start + offset, end + offset);

    }
}
