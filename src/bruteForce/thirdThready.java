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
            if(i <= 57 || (i >= 65 && i <= 90) || i >= 97)
            {
                if(i == 123)
                {
                    i = 48;
                }

                if(StringUtil.applySha256(crackedPassword).equals(password))
                {
                    return crackedPassword;
                }

                if(!(cPassword[4] == 'z'))
                {
                    if(cPassword[4] == '9')
                    {
                        cPassword[4] = 65;
                    }
                    else if(cPassword[4] == 'Z')
                    {
                        cPassword[4] = 97;
                    }
                    else
                    {
                        cPassword[4] = (char) i;
                    }
                }
                else
                {
                    if(cPassword[3] == '9')
                    {
                        cPassword[3] = 65;
                    }
                    else if(cPassword[3] == 'Z')
                    {
                        cPassword[3] = 97;
                    }
                    else
                    {
                        cPassword[3] += 1;
                    }
                    cPassword[4] = (char) 48;
                }

                if(cPassword[3] == 'z')
                {
                    if(cPassword[2] == '9')
                    {
                        cPassword[2] = 65;
                    }
                    else if(cPassword[2] == 'Z')
                    {
                        cPassword[2] = 97;
                    }
                    else
                    {
                        cPassword[2] += 1;
                    }
                    cPassword[3] = (char) 48;
                }

                if(cPassword[2] == 'z')
                {
                    if(cPassword[1] == '9')
                    {
                        cPassword[1] = 65;
                    }
                    else if(cPassword[1] == 'Z')
                    {
                        cPassword[1] = 97;
                    }
                    else
                    {
                        cPassword[1] += 1;
                    }
                    cPassword[2] = (char) 48;
                }

                if(cPassword[1] == 'z')
                {
                    if(cPassword[0] == '9')
                    {
                        cPassword[0] = 65;
                    }
                    else if(cPassword[0] == 'Z')
                    {
                        cPassword[0] = 97;
                    }
                    else
                    {
                        cPassword[0] += 1;
                    }
                    cPassword[1] = (char) 48;
                }



                crackedPassword = cPassword[0] +""+ cPassword[1] +""+ cPassword[2] +""+ cPassword[3] +""+ cPassword[4];
            }



        }
        return null;
    }
}