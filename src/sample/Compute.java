package sample;

import java.util.Random;

/**
 * Created by Amineh.ahm on 6/4/2016 AD.
 */
public class Compute
{
    public static boolean isProbable(double p)
    {
        Random rand = new Random();
        double dot = rand.nextDouble();
        return (dot <= p);
    }

    public static double Poisson(double landa)
    {
        double p0 = Math.exp(0. - landa);
        return (1 - p0);
    }
}
