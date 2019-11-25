package bruteForce;

import java.util.concurrent.Callable;

public class secondThready implements Callable<String>
{
    private String crackedPassword;
    private char[] cPassword;
    private String password;
    public secondThready(String password, String crackedPassword)
    {
        this.crackedPassword = crackedPassword;
        this.password = password;
        cPassword = crackedPassword.toCharArray();
    }

    @Override
    public String call()
    {
        for(int i = 65; i < 92; ++i)
        {
            if(i == 91)
            {
                i = 65;
            }

            if(StringUtil.applySha256(crackedPassword).equals(password))
            {
                return crackedPassword;
            }

            if(!(cPassword[5] == 'Z'))
            {
                cPassword[5] = (char) i;
            }
            else
            {
                cPassword[4] += 1;
                cPassword[5] = (char) 65;
            }

            if(cPassword[4] == 'Z')
            {
                cPassword[3] += 1;
                cPassword[4] = (char) 65;
            }

            if(cPassword[3] == 'Z')
            {
                cPassword[2] += 1;
                cPassword[3] = (char) 65;
            }

            if(cPassword[2] == 'Z')
            {
                cPassword[1] += 1;
                cPassword[2] = (char) 65;
            }

            if(cPassword[1] == 'Z')
            {
                cPassword[0] += 1;
                cPassword[1] = (char) 65;
            }


            crackedPassword = cPassword[0] +""+ cPassword[1] +""+ cPassword[2] +""+ cPassword[3] +""+ cPassword[4] +""+ cPassword[5];



        }
        return null;
    }
}