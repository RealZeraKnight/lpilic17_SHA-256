package ue1;

import java.util.List;
import java.util.concurrent.Callable;

public class fourthThready implements Callable<String>
{
    private List<String> fabelwesen;
    private String password;
    private int von;
    private int bis;

    public fourthThready(List<String> fabelwesen, String password, int von, int bis) {
        this.fabelwesen = fabelwesen;
        this.password = password;
        this.von = von;
        this.bis = bis;
    }


    @Override
    public String call()
    {
        for(int i = von; i < bis; ++i)
        {
            if(StringUtil.applySha256(fabelwesen.get(i)).equals(password))
            {
                return fabelwesen.get(i);
            }
        }
        return null;
    }
}