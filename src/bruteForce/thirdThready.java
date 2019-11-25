package bruteForce;

import java.util.concurrent.Callable;

public class thirdThready implements Callable<String>
{
    private String crackedPassword;
    private char[] cPassword;
    private String password;
    public thirdThready(String password, String crackedPassword)
    {
        this.crackedPassword = crackedPassword;
        this.password = password;
        cPassword = crackedPassword.toCharArray();
    }

    @Override
    public String call()
    {
        for(int i = 48; i < 124; ++i)
        {

                if(i == 123)
                {
                    i = 48;
                }

                if(StringUtil.applySha256(crackedPassword).equals(password))
                {
                    return crackedPassword;
                }

                if(!(cPassword[4] == 'Z'))
                {
                    cPassword[4] = (char) i;
                }
                else
                {
                    cPassword[3] += 1;
                    cPassword[4] = (char) 48;
                }

                if(cPassword[3] == 'Z')
                {
                    cPassword[2] += 1;
                    cPassword[3] = (char) 48;
                }

                if(cPassword[2] == 'Z')
                {
                    cPassword[1] += 1;
                    cPassword[2] = (char) 48;
                }

                if(cPassword[1] == 'Z')
                {
                    cPassword[0] += 1;
                    cPassword[1] = (char) 48;
                }



                crackedPassword = cPassword[0] +""+ cPassword[1] +""+ cPassword[2] +""+ cPassword[3] +""+ cPassword[4];
            System.out.println(crackedPassword);



        }
        return null;
    }
}