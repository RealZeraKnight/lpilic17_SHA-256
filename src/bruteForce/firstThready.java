package bruteForce;

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
        for(int i = 97; i < 124; ++i)
        {
            if(i == 123)
            {
                i = 97;
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
                cPassword[3] = (char) 96;
            }

            if(cPassword[2] == 'z')
            {
                cPassword[1] += 1;
                cPassword[2] = (char) 96;
            }

            if(cPassword[1] == 'z')
            {
                cPassword[0] += 1;
                cPassword[1] = (char) 96;
            }


            crackedPassword = cPassword[0] +""+ cPassword[1] +""+ cPassword[2] +""+ cPassword[3];



        }
        return null;
    }
}
