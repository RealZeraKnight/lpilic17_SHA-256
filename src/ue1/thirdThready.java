package ue1;

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
        int nul = 48;
        int neun = 57;
        int A = 65;
        int Z = 90;
        int z = 122;
        int a = 96;
        for(int i = nul; i < 124; ++i)
        {
            if(i <= neun || (i >= A && i <= Z) || i >= a)
            {
                if(i == z + 1)
                {
                    i = nul;
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
                    cPassword[4] = (char) nul;
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
                    cPassword[3] = (char) nul;
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
                    cPassword[2] = (char) nul;
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
                    cPassword[1] = (char) nul;
                }



                crackedPassword = cPassword[0] +""+ cPassword[1] +""+ cPassword[2] +""+ cPassword[3] +""+ cPassword[4];
            }



        }
        return null;
    }
}