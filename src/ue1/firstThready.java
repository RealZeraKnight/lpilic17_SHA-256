package ue1;

import java.util.concurrent.Callable;

public class firstThready implements Callable<String>
{
    private String crackedPassword;
    private char[] cPassword;
    private String password;
    public firstThready(String password, String crackedPassword)
    {
        this.crackedPassword = crackedPassword;
        this.password = password;
        cPassword = crackedPassword.toCharArray();
    }

    @Override
    public String call()
    {
        int z = 122;
        int a = 96;
        for(int i = a; i < 124; ++i)
        {
            if(i == z + 1)
            {
                i = a;
            }

            if(StringUtil.applySha256(crackedPassword).equals(password))
            {
                return crackedPassword;
            }

            if(!(cPassword[3] == 'z'))
            {
                cPassword[3] = (char) i;
            }
            else
            {
                cPassword[2] += 1;
                cPassword[3] = (char) a;
            }

            if(cPassword[2] == 'z')
            {
                cPassword[1] += 1;
                cPassword[2] = (char) a;
            }

            if(cPassword[1] == 'z')
            {
                cPassword[0] += 1;
                cPassword[1] = (char) a;
            }


            crackedPassword = cPassword[0] +""+ cPassword[1] +""+ cPassword[2] +""+ cPassword[3];



        }
        return null;
    }
}
